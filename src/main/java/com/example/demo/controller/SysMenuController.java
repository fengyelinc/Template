package com.example.demo.controller;

import com.example.demo.entity.SysMenu;
import com.example.demo.service.SysMenuService;


import io.swagger.annotations.ApiOperation;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.api.R;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.io.Serializable;
import java.util.List;

@Controller
@RequestMapping("/sysMenu")
public class SysMenuController {

    /**
     * 服务对象
     */
    @Autowired
    private SysMenuService sysMenuService;


}


