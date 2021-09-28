package com.example.demo.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.demo.dao.RoleMapper;
import com.example.demo.entity.Role;
import com.example.demo.service.RoleService;
import org.springframework.stereotype.Service;

/**
 * (Role)表服务实现类
 *
 * @author makejava
 * @since 2021-09-03 16:30:24
 */
@Service
public class RoleServiceImpl extends ServiceImpl<RoleMapper, Role> implements RoleService{


    @Override
    public Role getRoleByUserName(String username) {
        return this.baseMapper.getRoleByUserName(username);
    }
}