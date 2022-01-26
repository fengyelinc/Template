package com.example.demo.config.cache;

import com.example.demo.utils.SpringUtils;
import org.apache.shiro.cache.Cache;
import org.apache.shiro.cache.CacheException;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.StringRedisSerializer;

import java.util.Collection;
import java.util.Set;

/**
 * 自定义redis缓存的实现
 */
public class RedisCache<K,V> implements Cache<K,V> {

    private String cacheName;

    public RedisCache() {
    }

    public RedisCache(String cacheName) {
        this.cacheName = cacheName;
    }

    private RedisTemplate getRedisTemplate(){
        RedisTemplate redisTemplete = (RedisTemplate)SpringUtils.getApplicationContext().getBean("redisTemplete");
        redisTemplete.setKeySerializer(new StringRedisSerializer()); //设置key的序列化方式
        redisTemplete.setHashKeySerializer(new StringRedisSerializer());  //设置hash中小key的序列化方式
        return redisTemplete;
    }




    @Override
    public V get(K k) throws CacheException {
        System.out.println("get key "+k);
        return (V)getRedisTemplate().opsForHash().get(this.cacheName,k.toString());

    }

    @Override
    public V put(K k, V v) throws CacheException {
        System.out.println("put key"+k);
        System.out.println("put value "+v);
        getRedisTemplate().opsForHash().put(this.cacheName,k.toString(),v);
        return null;
    }

    @Override
    public V remove(K k) throws CacheException {
        return (V)getRedisTemplate().opsForHash().delete(this.cacheName,k.toString());
    }

    @Override
    public void clear() throws CacheException {
        getRedisTemplate().delete(this.cacheName);
    }

    @Override
    public int size() {
        return getRedisTemplate().opsForHash().size(this.cacheName).intValue();
    }

    @Override
    public Set<K> keys() {
        return getRedisTemplate().opsForHash().keys(this.cacheName);
    }

    @Override
    public Collection<V> values() {
        return getRedisTemplate().opsForHash().values(this.cacheName);
    }
}
