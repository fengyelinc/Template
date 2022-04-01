package com.example.demo.utils;

import cn.hutool.core.lang.UUID;

public class UuidUtils {

    public static String getUUID(){
        String uuid = UUID.randomUUID().toString().replace("-","");
        return uuid;
    }

    public static void main(String[] args) {
        System.out.println(getUUID());
    }
}
