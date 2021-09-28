package com.example.demo.entity;



import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.example.demo.base.UBaseEntity;
import lombok.Data;


/**
 * (User)表实体类
 *
 * @author makejava
 * @since 2021-09-07 16:45:16
 */
@Data
@SuppressWarnings("serial")
@TableName("user")
public class User  {

    /**
     * 实体编号（唯一标识）
     */
    @TableId(value = "id",type= IdType.ASSIGN_UUID)
    protected String id;

    /**
     * 用户名
     */

    @TableField(value = "username")
    private String username;

    /**
     * 密码
     */

    @TableField(value = "password")
    private String password;

    /**
     * 账号
     */

    @TableField(value = "account")
    private String account;

    @TableField(value = "salt")
    private String salt;


}