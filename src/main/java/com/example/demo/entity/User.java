package com.example.demo.entity;



import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;


/**
 * (User)表实体类
 *
 * @author cc
 * @since 2021-09-07 16:45:16
 */
@Data
@SuppressWarnings("serial")
@TableName("user")
@Accessors(chain=true)   //链式
@ApiModel(value="user对象",description="用户对象user")
public class User  {

    /**
     * 实体编号（唯一标识）
     */
    @ApiModelProperty(value = "用户ID",example = "1000001",hidden=true)
    @TableId(value = "id",type= IdType.ASSIGN_UUID)
    private String id;

    /**
     * 用户名
     */
    @ApiModelProperty(value = "用户姓名",example = "法外狂徒",required = false)
    @TableField(value = "name")
    private String name;

    /**
     * 密码
     */
    @ApiModelProperty(value = "密码",example = "123456",required=true)
    @TableField(value = "password")
    private String password;

    /**
     * 账号
     */
    @ApiModelProperty(value = "账号",example = "100001",required=true)
    @TableField(value = "account")
    private String account;

    @ApiModelProperty(hidden=true)
    @TableField(value = "salt" )
    private String salt;

    @Override
    public String toString() {
        return "User{" +
                "id='" + id + '\'' +
                ", username='" + name + '\'' +
                ", password='" + password + '\'' +
                ", account='" + account + '\'' +
                ", salt='" + salt + '\'' +
                '}';
    }
}