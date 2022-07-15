package com.example.demo.utils;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import java.util.concurrent.TimeUnit;

@Component
@Slf4j(topic = "RedisUtil")
public final class RedisUtil {
    @Autowired
    private RedisTemplate<String, Object> redisTemplate;
    // =============================common============================
    /**
     * 指定缓存失效时间
     * @param key 键
     * @param time 时间(秒)
     * @return
     */
    public boolean expire(String key, long time) {
        try {
            if (time > 0) {
                redisTemplate.expire(key, time, TimeUnit.SECONDS);
            }
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * 判断key是否存在
     * @param key 键
     * @return true 存在 false不存在
     */
    public boolean hasKey(String key) {
        try {
            return redisTemplate.hasKey(key);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * 删除缓存
     * @param key 可以传一个值 或多个
     */
    @SuppressWarnings("unchecked")
    public void del(String... key) {
        if (key != null && key.length > 0) {
            if (key.length == 1) {
                redisTemplate.delete(key[0]);
            } else {
                redisTemplate.delete(CollectionUtils.arrayToList(key));
            }
        }
    }

    /**
     * 普通缓存获取
     * @param key 键
     * @return 值
     */
    public Object get(String key) {
        return key == null ? null : redisTemplate.opsForValue().get(key);
    }

    /**
     * 普通缓存放入
     * @param key 键
     * @param value 值
     * @return true成功 false失败
     */
    public boolean set(String key, Object value) {
        try {
            redisTemplate.opsForValue().set(key, value);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
    /**
     * 普通缓存放入并设置时间
     * @param key 键
     * @param value 值
     * @param time 时间(秒) time要大于0 如果time小于等于0 将设置无限期
     * @return true成功 false 失败
     */
    public boolean set(String key, Object value, long time) {
        try {
            if (time > 0) {
                redisTemplate.opsForValue().set(key, value, time, TimeUnit.SECONDS);
            } else {
                set(key, value);
            }
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }


    /**
     * 分布式加锁
     * @param key
     * @param value
     * @return
     * */
    public boolean secKilllock(String key,String value){
        /**
         * setIfAbsent就是setnx
         * 将key设置值为value，如果key不存在，这种情况下等同SET命令。
         * 当key存在时，什么也不做。SETNX是”SET if Not eXists”的简写
         * */
        if(redisTemplate.opsForValue().setIfAbsent(key,value)){
            //加锁成功返回true
            return true;
        }
        //避免死锁，且只让一个线程拿到锁
        String currentValue = (String) redisTemplate.opsForValue().get(key);
        /**
         * 下面这几行代码的作用：
         * 1、防止死锁
         * 2、防止多线程抢锁
         * */
        if(! StringUtils.isEmpty(currentValue)
                && Long.parseLong(currentValue) < System.currentTimeMillis()){
            //如果锁过期了,获取上一个锁的时间
            String oldValue = (String) redisTemplate.opsForValue().getAndSet(key,value);
            //只会让一个线程拿到锁
            //如果旧的value和currentValue相等，只会有一个线程达成条件，因为第二个线程拿到的oldValue已经和currentValue不一样了
            if(! StringUtils.isEmpty(oldValue) && oldValue.equals(currentValue)){
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
