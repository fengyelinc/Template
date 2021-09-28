package com.example.demo.service;
import com.example.demo.base.IBaseService;
import com.example.demo.entity.Role;
import com.example.demo.entity.VO.RoleVO;
/**
 * (Role)表服务接口
 *
 * @author makejava
 * @since 2021-09-03 16:30:24
 */
public interface RoleService extends IBaseService<Role,RoleVO> {

    Role getRoleByUserName(String username);

}