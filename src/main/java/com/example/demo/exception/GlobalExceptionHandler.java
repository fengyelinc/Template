package com.example.demo.exception;

import com.example.demo.base.ResultData;
import com.example.demo.base.ResultMsgEnum;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authz.AuthorizationException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.security.auth.message.AuthException;

@RestControllerAdvice//springboot 注解 开启全局异常处理
@Slf4j
public class GlobalExceptionHandler {

    @ExceptionHandler(AuthException.class)
    public String ErrorHandler(AuthorizationException e) {
        log.error("没有通过权限验证！", e);
        return "没有通过权限验证！";
    }

    @ExceptionHandler(Exception.class)
    public ResultData Execption(Exception e) {
        log.error("未知异常！", e);
        return ResultData.failure(ResultMsgEnum.SERVER_BUSY.getCode(), ResultMsgEnum.SERVER_BUSY.getMessage());
    }


}
