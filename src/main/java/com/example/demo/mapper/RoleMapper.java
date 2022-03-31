package com.example.demo.mapper;

import com.example.demo.entity.Role;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * (Role)表数据库访问层
 *
 * @author makejava
 * @since 2021-09-03 16:30:24
 */
@Mapper
public interface RoleMapper extends BaseMapper<Role>{

    Role getRoleByUserName(@Param("username") String username);
}