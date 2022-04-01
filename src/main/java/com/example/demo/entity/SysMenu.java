package com.example.demo.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import com.example.demo.base.BaseEntity;

import java.io.Serializable;
import java.util.Date;


@Data
@ApiModel("菜单")
public class SysMenu {


    @TableId(type = IdType.AUTO)
    private String id;

    /**
     * 菜单名称
     */
    @ApiModelProperty(value = "菜单名称")
    private String name;

    /**
     * 父菜单
     */
    @ApiModelProperty(value = "父菜单")
    private String parentId;

    /**
     * 菜单层级
     */
    @ApiModelProperty(value = "菜单层级")
    private Long level;

    /**
     * 父菜单联集
     */
    @ApiModelProperty(value = "父菜单联集")
    private String parentIds;

    /**
     * 排序
     */
    @ApiModelProperty(value = "排序")
    private Integer sort;

    /**
     * 链接地址
     */
    @ApiModelProperty(value = "链接地址")
    private String href;

    /**
     * 打开方式
     */
    @ApiModelProperty(value = "打开方式")
    private String target;

    /**
     * 菜单图标
     */
    @ApiModelProperty(value = "菜单图标")
    private String icon;

    /**
     * 显示背景色
     */
    @ApiModelProperty(value = "显示背景色")
    private String bgColor;

    /**
     * 是否显示
     */
    @ApiModelProperty(value = "是否显示")
    private Integer isShow;

    /**
     * 权限标识
     */
    @ApiModelProperty(value = "权限标识")
    private String permission;

    @ApiModelProperty(value = "备注")
    private String remarks;

    /**
     * 1是0否
     */
    @ApiModelProperty(value = "1是0否")
    private Integer isDel;

    /**
     * 类型：0：菜单 1：按钮
     */
    @ApiModelProperty(value = "类型：0：菜单 1：按钮")
    private String type;

    @ApiModelProperty(hidden = true)
    private String creater;

    @ApiModelProperty(hidden = true)
    private Date createtime;

    @ApiModelProperty(hidden = true)
    private String updater;

    @ApiModelProperty(hidden = true)
    private Date updatetime;

}

