package com.example.demo.utils;

import com.example.demo.entity.User;
import com.example.demo.entity.VO.UserVO;
import org.springframework.beans.BeanUtils;

import java.util.Objects;

public class MyBeanUtils {

    public static void bean2VO(Object source, Object target) {
        if (Objects.isNull(source)) {
            return;
        }
        BeanUtils.copyProperties(source, target);
    }


    public static void bean2VO(Object source, Object target, String prefix) throws ClassNotFoundException, InstantiationException, IllegalAccessException {
        if (Objects.isNull(source)) {
            return;
        }
        Class<?> sourceClass = source.getClass();
        Class<?> targetClass = target.getClass();



    }

    public static void main(String[] args) {
        User user = new User();
        user.setName("cc").setAccount("123").setPassword("123");
        UserVO userVO = new UserVO();
        MyBeanUtils.bean2VO(user, userVO);
        System.out.println(userVO.getName() + userVO.getAccount() + userVO.getPassword());
    }

}

