package com.liyulong.blog.shiro.service.imp;

import com.liyulong.blog.main.common.util.RedisUtil;
import com.liyulong.blog.shiro.service.SyncCacheService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

public class SyncCacheServiceImp implements SyncCacheService {

    private static final Logger LOGGER = LoggerFactory.getLogger(SyncCacheServiceImp.class);

    @Autowired
    private RedisUtil redisUtil;

    /**
     * 获取redis中key的锁，乐观锁实现
     * @param lockName 锁名称
     * @param expireTime 锁的失效时间
     * @return
     */
    @Override
    public Boolean getLock(String lockName, int expireTime) {
        Boolean result = Boolean.FALSE;
        try {
            boolean isExist = redisUtil.exists(lockName);
            if(!isExist){
                redisUtil.getSeqNext(lockName,0);
                redisUtil.expire(lockName,expireTime<=0 ? 60 * 60 : expireTime);
            }
            long reVal =  redisUtil.getSeqNext(lockName,1);
            if(1L ==reVal){
                //获取锁
                result = Boolean.TRUE;
                LOGGER.info("获取redis锁:"+lockName+",成功");
            }else {
                LOGGER.info("获取redis锁:"+lockName+",失败"+reVal);
            }
        } catch (Exception e) {
            LOGGER.error("获取redis锁失败:"+lockName, e);
        }
        return result;
    }

    /**
     * 释放锁，直接删除key(直接删除会导致任务重复执行，所以释放锁机制设为超时30s)
     * @param lockName
     * @return
     */
    @Override
    public Boolean releaseLock(String lockName) {
        Boolean result = Boolean.FALSE;
        try {
            redisUtil.expire(lockName, 10);
            LOGGER.info("释放redis锁:"+lockName+",成功");
        } catch (Exception e) {
            LOGGER.error("释放redis锁失败:"+lockName, e);
        }
        return result;
    }
}
