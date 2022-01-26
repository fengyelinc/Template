package com.example.demo.service.impl;

import com.example.demo.service.RedisService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

@Service
@Slf4j
public class RedisServiceImpl implements RedisService {

    @Autowired
    private RedisTemplate redisTemplate;
    //超时时间
    private static final long EXPIRE = 1000L;
    //等待时间
    private static final long TIMEOUT = 1000L;

    /**
     * 后端控制---防止重复提交
     * value为锁的过期时间
     */
    @Override
    public boolean secKilllock(String key,String value){
        /**
         * setIfAbsent相当于setNX   仅当key不存在时会进行操作
         */
        if(redisTemplate.opsForValue().setIfAbsent(key, value)){
            //成功获取到锁，返回true
            return true;
        }
        //值为锁的过期时间
        String currentValue = (String) redisTemplate.opsForValue().get(key);
        log.info("当前锁的值为："+currentValue);
        /**
         * 1、判断锁是否超时，防止死锁
         * 2、防止争抢锁
         */
        if(!StringUtils.isEmpty(currentValue) && Long.parseLong(currentValue)<System.currentTimeMillis()){
            //当前锁超时，获取上一个锁的时间
            String oldValue = (String)redisTemplate.opsForValue().getAndSet(key, value);
            /**
             * 处理上一个获取锁的线程没有处理完业务锁就超时的问题
             * 只有上一个获取锁的线程才能再次获取到锁
             */
            if(!StringUtils.isEmpty(oldValue) && oldValue.equals(currentValue) ){
                return true;
            }
        }
        return false;
    }

    /**
     * 解锁
     * @param key
     * @param value
     * */
    @Override
    public void unlock(String key,String value){
        try{
            String currentValue = (String) redisTemplate.opsForValue().get(key);
            if (!StringUtils.isEmpty(currentValue) && currentValue.equals(value)) {
                redisTemplate.opsForValue().getOperations().delete(key);
            }
        }catch (Exception e){
            e.printStackTrace();
            log.error("『redis分布式锁』解锁异常，{}", e);
        }
    }
}
