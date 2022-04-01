package com.example.demo.controller.api;

import com.example.demo.annotation.UserLoginToken;
import com.example.demo.base.ResultData;
import com.example.demo.entity.SysMenu;
import com.example.demo.entity.User;
import com.example.demo.entity.VO.MenuTree;
import com.example.demo.service.SysMenuService;
import com.example.demo.utils.JWTHelper;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.apache.http.HttpRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
@Api
@RequestMapping("api/menu")
@Slf4j
public class ApiMenuController {

    @Autowired
    private SysMenuService menuService;

    @ApiOperation("获取菜单列表")
    @PostMapping("list")
    public ResultData getDateList(){
        List<MenuTree> resultList =menuService.getDateList();
        return ResultData.success(resultList);
    }


    @ApiOperation("添加/修改菜单")
    @PostMapping("addOrUpdataMenu")
    @UserLoginToken
    public ResultData addOrUpdataMenu(@RequestBody SysMenu sysMenu, HttpServletRequest request){
        User user =null;
        try {
             user = JWTHelper.getUserInfo(request.getHeader("token"));
        } catch (Exception e) {
            e.printStackTrace();
            return ResultData.failure("token失效，请重新登陆");
        }
        int b = menuService.addOrUpdataMenu(sysMenu,user);
        return b>0?ResultData.success():ResultData.failure();
    }

}
