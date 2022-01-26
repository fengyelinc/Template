package com.example.demo.service;

public interface RedisService {
    public boolean secKilllock(String key,String value);

    public void unlock(String key,String value);
}
