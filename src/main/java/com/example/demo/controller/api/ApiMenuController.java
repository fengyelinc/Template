package com.example.demo.controller.api;

import com.example.demo.base.ResultData;
import com.example.demo.entity.VO.MenuTree;
import com.example.demo.service.SysMenuService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
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


}
