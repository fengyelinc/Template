package com.example.demo.service.impl;

import cn.hutool.core.collection.CollectionUtil;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.demo.entity.SysMenu;
import com.example.demo.entity.User;
import com.example.demo.entity.VO.MenuTree;
import com.example.demo.mapper.SysMenuMapper;
import com.example.demo.service.SysMenuService;
import com.example.demo.utils.RedisUtil;
import com.example.demo.utils.UuidUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
public class SysMenuServiceImpl extends ServiceImpl<SysMenuMapper, SysMenu> implements SysMenuService {
    @Autowired
    private SysMenuMapper sysMenuMapper;

    @Autowired
    private RedisUtil redisUtil;

    /**
     * 获取菜单的树形结构
     *
     * @return
     */
    @Override
    public List<MenuTree> getDateList() {
        List<MenuTree> menu = (List<MenuTree>) redisUtil.get("menu");
        if (CollectionUtil.isNotEmpty(menu)) {
            return menu;
        } else {
            List<SysMenu> all = sysMenuMapper.selectAll();
            List<MenuTree> menuList = new ArrayList<>();
            for (SysMenu sysMenu : all) {
                //只需要递归根节点
                if (sysMenu.getParentId().equals("0")) {
                    MenuTree menuTree = new MenuTree();
                    menuTree.setId(sysMenu.getId());
                    menuTree.setName(sysMenu.getName());
                    menuTree.setLevel(sysMenu.getLevel());
                    menuTree.setChildern(selectChildren(sysMenu));
                    menuList.add(menuTree);
                }
            }
            redisUtil.set("menu", menuList);
            return menuList;
        }
    }


    /**
     * 递归获取所有下级部门
     *
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


    /**
     * 添加或修改菜单
     * 清空redis缓存
     * @param sysMenu
     * @return
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public int addOrUpdataMenu(SysMenu sysMenu, User user) {
        int b = 0;
        //添加
        if (StringUtils.isEmpty(sysMenu.getId())) {
            //TODO 待处理
            sysMenu.setId("9");
            sysMenu.setCreater(user.getId());
            b = sysMenuMapper.save(sysMenu);
        } else {
            b = sysMenuMapper.updateById(sysMenu);
        }
        if (b > 0) {
            redisUtil.del("menu");
        }
        return b;
    }


}

