package com.example.demo.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.demo.entity.SysMenu;
import com.example.demo.entity.VO.MenuTree;
import com.example.demo.mapper.SysMenuMapper;
import com.example.demo.service.SysMenuService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.CollectionUtils;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
public class SysMenuServiceImpl extends ServiceImpl<SysMenuMapper, SysMenu> implements SysMenuService {
    @Autowired
    private SysMenuMapper sysMenuMapper;

    /**
     * 获取菜单的树形结构
     * @return
     */
    @Override
    public List<MenuTree> getDateList() {
        List<SysMenu> all = sysMenuMapper.selectAll();
        List<MenuTree> menuList = new ArrayList<>();
        for (SysMenu sysMenu : all) {
            if(sysMenu.getParentId().equals("0")){
                MenuTree menuTree = new MenuTree();
                menuTree.setId(sysMenu.getId());
                menuTree.setName(sysMenu.getName());
                menuTree.setLevel(sysMenu.getLevel());
                menuTree.setChildern(selectChildren(sysMenu));
                menuList.add(menuTree);
            }
        }
        return menuList;
    }

    /**
     * 递归获取所有下级部门
     * @param sysMenu
     * @return
     */
    private List<MenuTree> selectChildren(SysMenu sysMenu) {
        List<SysMenu> menu = this.baseMapper.selectByPid(sysMenu.getId());
        if (menu.size() == 0) {
            return new ArrayList<>();
        }
        List<MenuTree> menuList = new ArrayList<>();
        for (SysMenu s : menu) {
            MenuTree menuTree = new MenuTree();
            menuTree.setId(s.getId());
            menuTree.setName(s.getName());
            menuTree.setLevel(s.getLevel());
            menuTree.setChildern(selectChildren(s));
            menuList.add(menuTree);
        }
        return menuList;
    }





}

