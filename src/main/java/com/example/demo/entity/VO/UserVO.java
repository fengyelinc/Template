package com.example.demo.entity.VO;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import io.swagger.annotations.ApiModel;
import lombok.Data;
import com.example.demo.entity.User;
/**
 * (User)VO
 *
 * @author makejava
 * @since 2021-09-07 16:45:17
 */
@Data
@SuppressWarnings("serial")
@ApiModel(value="uservo",description="用户对象uservo")
public class UserVO extends User {

    private String token;

}