package com.liyulong.blog.main.common.util;

import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.Objects;
import java.util.concurrent.TimeUnit;

@Component
public class RedisUtil {

    @Resource
    private RedisTemplate<String,Object> redisTemplate;
//    @Resource(name = "redisTemplate")
//    private ValueOperations<String, String> valOpsStr;
//    @Resource(name = "redisTemplate")
//    private SetOperations<String, String> valOpsSet;
//    @Resource(name = "redisTemplate")
//    private ZSetOperations<String, String> valOpsZSet;
//    @Resource(name = "redisTemplate")
//    ListOperations<String, String> valOpsList;
//    @Resource(name = "redisTemplate")
//    private HashOperations<String, String, Object> valOpsHash;

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
        redisTemplate.opsForValue().set(key, Objects.requireNonNull(JsonUtil.toJson(value)));
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
        String value = (String) redisTemplate.opsForValue().get(key);
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
        String value = (String) redisTemplate.opsForValue().get(key);
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
        return get(key,NOT_EXPIRE);
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

    /**
     * 验证key是否存在
     * @param key
     * @return
     */
    public boolean exists(String key){
        return redisTemplate.hasKey(key);
    }

    /**
     * 取得序列值的下一个
     *
     * @param key
     * @return
     */
    public Long getSeqNext(String key) {
        return redisTemplate.execute((RedisCallback<Long>) connection -> {
            return connection.incr(key.getBytes());

        });
    }

    /**
     * 取得序列值的下一个，增加 value
     *
     * @param key
     * @param value
     * @return
     */
    public Long getSeqNext(String key, long value) {
        return redisTemplate.execute((RedisCallback<Long>) connection -> {
            return connection.incrBy(key.getBytes(), value);
        });

    }

    /**
     * 设置超时时间
     *
     * @param key
     * @param seconds
     */
    public void expire(String key, int seconds) {
        redisTemplate.expire(key, seconds, TimeUnit.SECONDS);
    }

    /**
     * 判断是否缓存了数据
     * @param key 数据KEY
     * @return 判断是否缓存了
     */
    public boolean exist(String key) {
        return redisTemplate.execute((RedisCallback<Boolean>) connection -> {
            return connection.exists(key.getBytes());
        });
    }

}
