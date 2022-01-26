package com.example.demo.controller.api;

import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.example.demo.base.RestResponse;
import com.example.demo.config.cache.RedisCache;
import com.example.demo.entity.User;
import com.example.demo.entity.VO.UserVO;
import com.example.demo.service.UserService;
import com.example.demo.utils.JWTHelper;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.util.DigestUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.Objects;

@Api
@RestController
@RequestMapping("api/login")
@Slf4j
public class ApiLoginController {

    @Autowired
    private UserService userService;


    @Autowired
    private RedisTemplate redisTemplate;

    /**
     * 用户注册/登录
     *
     * @param uservo
     * @param response
     * @return
     */
    @ApiOperation(value = "用户注册/登录")
    @PostMapping("regOrLogin")
    public RestResponse register(@RequestBody UserVO uservo, HttpServletResponse response, HttpServletRequest request) {
        RestResponse res = new RestResponse();
        //校验用户信息
        if (Objects.isNull(uservo.getAccount())) {
            return RestResponse.failure("请输入账号");
        }
        if (Objects.isNull(uservo.getPassword())) {
            return RestResponse.failure("请输入密码");
        }
        //判断用户是否注册
        User user = userService.selectUserByAccount(uservo.getAccount());
        //注册
        if (Objects.isNull(user)) {
            user = new User();
            user.setAccount(uservo.getAccount());
            user.setPassword(DigestUtils.md5DigestAsHex(uservo.getPassword().getBytes()));
            boolean flag = userService.save(user);
            if (!flag) {
                return RestResponse.failure("系统异常");
            }
        } else {  //登录
            if (!DigestUtils.md5DigestAsHex(uservo.getPassword().getBytes()).equals(user.getPassword())) {
                return RestResponse.failure("密码错误");
            }
        }
        //组装返回值
        UserVO result = new UserVO();
        result.setName(user.getName());
        result.setAccount(user.getAccount());
        //token生成
        try {
            String token = JWTHelper.generTokenByRS256(result);
            response.setHeader("token",token);
            //TODO 用户信息放入缓存  redis缓存配置待完善
            RedisCache<String, String> redisCache = new RedisCache<>();
        } catch (Exception e) {
            log.error("系统异常", e);
        }
        return res.setData(result).setMessage("登陆成功");
    }



}
