package com.example.demo.entity;

import java.io.Serializable;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.example.demo.base.UBaseEntity;
import lombok.Data;

/**
 * (Role)表实体类
 *
 * @author makejava
 * @since 2021-09-03 16:30:24
 */
@Data
@SuppressWarnings("serial")
@TableName("role")
public class Role extends UBaseEntity<Role> {


    /**
     * 角色名
     */
    @TableField(value = "role_name")
    private String roleName;

    /**
     * 说明
     */

    @TableField(value = "remarks")
    private String remarks;


}