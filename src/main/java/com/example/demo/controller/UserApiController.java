package com.example.demo.controller;


import com.example.demo.service.UserService;
import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.*;
import javax.annotation.Resource;


/**
 * (User)api接口
 *
 * @author makejava
 * @since 2021-09-03 16:28:38
 */
@Api(tags = "用户管理")
@RestController
@RequestMapping("user")
public class UserApiController{

   /**
     * 服务对象
     */
    @Resource
    private UserService userService;


}