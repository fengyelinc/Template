package com.example.demo.controller;


import com.example.demo.service.RoleService;
import org.springframework.web.bind.annotation.*;
import javax.annotation.Resource;


/**
 * (Role)api接口
 *
 * @author makejava
 * @since 2021-09-03 16:30:24
 */
@RestController
@RequestMapping("demo/api/role/")
public class RoleApiController{
   /**
     * 服务对象
     */
    @Resource
    private RoleService roleService;




}