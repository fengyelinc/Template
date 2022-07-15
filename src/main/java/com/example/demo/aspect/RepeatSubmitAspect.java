package com.example.demo.aspect;

import com.example.demo.annotation.NoRepeatSubmit;
import com.example.demo.base.ResultData;
import com.example.demo.utils.RedisUtil;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

@Aspect
@Component
/**
 * 注解方式生成日志对象，指定topic生成对象类名
 */
@Slf4j(topic = "RepeatSubmitAspect")
public class RepeatSubmitAspect {

    @Autowired
    RedisUtil redisUtil;

    @Pointcut("@annotation(com.example.demo.annotation.NoRepeatSubmit)")
    public void pointCut() {
    }

    @Around("pointCut()")
    public Object around(ProceedingJoinPoint pjp) throws Throwable {
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();
        Assert.notNull(request, "request can not null");
        // 此处可以用token
        String token = request.getHeader("Authorization");
        String key = token + "-" + request.getServletPath();
        NoRepeatSubmit annotation = ((MethodSignature) pjp.getSignature()).getMethod().getAnnotation(NoRepeatSubmit.class);
        long expire = annotation.value();
        //超时时间：10秒，最好设为常量
        String time=String.valueOf(System.currentTimeMillis() + expire);
        //加锁
        boolean islock = redisUtil.secKilllock(key, time);
        if (islock) {
            Object result;
            try {
                result = pjp.proceed();
            } finally {
                //解锁
                redisUtil.unlock(key,time);
            }
            return result;
        }else {
            return new ResultData();
        }
    }


}
