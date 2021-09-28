package com.example.demo.dao;

import com.example.demo.entity.User;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * (User)表数据库访问层
 *
 * @author makejava
 * @since 2021-09-03 16:28:40
 */
 @Mapper
public interface UserMapper extends BaseMapper<User>{



    User selectUserByAccount(@Param("account") String account);

    int queryAccount(@Param("account")String account);
}