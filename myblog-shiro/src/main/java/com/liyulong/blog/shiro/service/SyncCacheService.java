package com.liyulong.blog.shiro.service;

public interface SyncCacheService {

    Boolean getLock(String lockName, int expireTime);

    Boolean releaseLock(String lockName);

}
