package com.example.demo.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.example.demo.entity.SysMenu;
import com.example.demo.entity.VO.MenuTree;

import java.util.List;

public interface SysMenuService extends IService<SysMenu> {

    List<MenuTree> getDateList();
}

