package com.example.demo.service;
import com.example.demo.base.IBaseService;
import com.example.demo.entity.User;
import com.example.demo.entity.VO.UserVO;

/**
 * (User)表服务接口
 *
 * @author makejava
 * @since 2021-09-03 16:28:39
 */
public interface UserService extends IBaseService<User,UserVO> {

    User selectUserByAccount(String account);
}