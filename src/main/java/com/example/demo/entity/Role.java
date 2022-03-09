package com.example.demo.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.example.demo.base.BaseEntity;
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
public class Role extends BaseEntity<Role> {


    /**
     * 角色名
     */
    @TableField(value = "name")
    private String name;

    /**
     * 说明
     */

    @TableField(value = "remark")
    private String remark;


}