package com.liyulong.blog.main.common.util;

import org.springframework.data.redis.core.*;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.concurrent.TimeUnit;

@Component
public class RedisUtil {

    @Resource
    private RedisTemplate<String,Object> redisTemplate;
    @Resource
    private ValueOperations<String, String> valueOperations;
    @Resource
    private SetOperations<String, String> setOperations;
    @Resource
    private ZSetOperations<String, String> zSetOperations;
    @Resource
    private ListOperations<String, String> listOperations;
    @Resource
    private HashOperations<String, String, Object> hashOperations;

    //设置默认过期时间，单位：秒,两个小时
    public static final long DEFAULT_EXPIRE = 60 * 2 * 60;
    //不设置过期时间
    public static final long NOT_EXPIRE = -1;

    /**
     * 设置值与过期时间
     * @param key 键
     * @param value 值
     * @param expire 过期时间
     */
    public void set(String key,Object value,long expire){
        valueOperations.set(key, JsonUtil.toJson(value));
        if(expire != NOT_EXPIRE){
            redisTemplate.expire(key,expire, TimeUnit.SECONDS);
        }
    }

    /**
     * 设置值与默认的过期时间
     * @param key
     * @param value
     */
    public void set(String key,Object value){
        set(key,value,DEFAULT_EXPIRE);
    }

    /**
     * 获取对象，同时设置过期时间
     * @param key 键
     * @param clazz 转换为那个类的 class
     * @param expire 过期时间
     * @param <T>
     * @return
     */
    public <T> T getObj(String key,Class<T> clazz,long expire){
        String value = valueOperations.get(key);
        if(expire != DEFAULT_EXPIRE){
            redisTemplate.expire(key,expire,TimeUnit.SECONDS);
        }
        return value == null ? null : JsonUtil.toObj(value,clazz);
    }

    /**
     * 获取对象，不设置过期时间
     * @param key
     * @param clazz
     * @param <T>
     * @return
     */
    public <T> T getObj(String key,Class<T> clazz){
        return getObj(key,clazz,NOT_EXPIRE);
    }

    /**
     * 获取值，并设置过期时间
     * @param key
     * @param expire
     * @return
     */
    public String get(String key,long expire){
        String value = valueOperations.get(key);
        if(expire != DEFAULT_EXPIRE){
            redisTemplate.expire(key,expire,TimeUnit.SECONDS);
        }
        return value;
    }

    /**
     * 获取值，不设置过期时间
     * @param key
     * @return
     */
    public String get(String key){
        return valueOperations.get(key);
    }

    /**
     * 删除
     * @param key 键
     */
    public void delete(String key){
        redisTemplate.delete(key);
    }

    /**
     * 更新过期时间
     * @param key 键
     */
    public void updateExpire(String key){
        redisTemplate.expire(key,DEFAULT_EXPIRE,TimeUnit.SECONDS);
    }

}
