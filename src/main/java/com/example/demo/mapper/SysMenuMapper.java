package com.example.demo.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import com.example.demo.entity.SysMenu;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface SysMenuMapper extends BaseMapper<SysMenu> {

    List<SysMenu> selectAll();


    List<SysMenu> selectByPid(@Param("parentId") String parentId);

    int save(@Param("sysMenu")SysMenu sysMenu);
}


