package com.example.demo.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import com.example.demo.base.BaseEntity;

import java.util.Date;


@Data
@ApiModel("角色表")
public class Role extends BaseEntity<Role> {


    @TableId(type = IdType.AUTO)
    private String id;

    /**
     * 角色名
     */
    @ApiModelProperty(value = "角色名")
    private String name;

    /**
     * 备注
     */
    @ApiModelProperty(value = "备注")
    private String remark;

    private String creator;

    private Date createtime;

    private String updater;

    private Date updatetime;

    private Integer isdel;

}

