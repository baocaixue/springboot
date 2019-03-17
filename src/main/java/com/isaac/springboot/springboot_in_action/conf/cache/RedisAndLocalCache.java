package com.isaac.springboot.springboot_in_action.conf.cache;

import org.springframework.cache.Cache;
import org.springframework.data.redis.cache.RedisCache;

import java.util.concurrent.Callable;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 核心
 */
public class RedisAndLocalCache implements Cache {
    //本地缓存提供
    ConcurrentHashMap<Object, Object> local = new ConcurrentHashMap<>();
    RedisCache redisCache;
    TwoLevelCacheManager cacheManager;

    public RedisAndLocalCache(TwoLevelCacheManager cacheManager, RedisCache redisCache) {
        this.redisCache = redisCache;
        this.cacheManager = cacheManager;
    }

    @Override
    public String getName() {
        return redisCache.getName();
    }

    @Override
    public Object getNativeCache() {
        return redisCache.getNativeCache();
    }

    @Override
    public ValueWrapper get(Object o) {
        //一级缓存先取
        ValueWrapper wrapper = (ValueWrapper) local.get(o);
        if (wrapper == null) {
            //二级缓存
            wrapper = redisCache.get(o);
            if (wrapper != null) {
                local.put(o, wrapper);
            }
        }
        return wrapper;
    }

    @Override
    public <T> T get(Object o, Class<T> aClass) {
        return null;
    }

    @Override
    public <T> T get(Object o, Callable<T> callable) {
        return null;
    }

    @Override
    public void put(Object o, Object o1) {
        redisCache.put(o,o1);
        //通知其他节点缓存更新
        clearOtherJVM();
    }

    @Override
    public ValueWrapper putIfAbsent(Object o, Object o1) {
        return null;
    }

    @Override
    public void evict(Object o) {
        redisCache.evict(o);
        //通知其他节点缓存更新
        clearOtherJVM();
    }

    @Override
    public void clear() {
        local.clear();
    }

    protected void clearOtherJVM(){
        cacheManager.publishMessage(redisCache.getName());
    }

    public void clearLocal(){
        this.clear();
    }
}
