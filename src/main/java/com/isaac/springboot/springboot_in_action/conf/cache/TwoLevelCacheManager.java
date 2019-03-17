package com.isaac.springboot.springboot_in_action.conf.cache;

import org.springframework.cache.Cache;
import org.springframework.data.redis.cache.RedisCache;
import org.springframework.data.redis.cache.RedisCacheConfiguration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.cache.RedisCacheWriter;
import org.springframework.data.redis.core.RedisTemplate;


/**
 * 实现redis两级缓存
 */
public class TwoLevelCacheManager extends RedisCacheManager {
    private RedisTemplate redisTemplate;

    public TwoLevelCacheManager(RedisTemplate redisTemplate, RedisCacheWriter redisCacheWriter, RedisCacheConfiguration defaultCacheConfiguration) {
        super(redisCacheWriter, defaultCacheConfiguration);
        this.redisTemplate = redisTemplate;
    }

    //使用RedisAndLocalCache代替Spring Boot自带的RedisCache

    @Override
    protected Cache decorateCache(Cache cache) {
        return new RedisAndLocalCache(this, ((RedisCache) cache));
    }

    public void publishMessage(String cacheName) {
        this.redisTemplate.convertAndSend("redis.cache.topic", cacheName);
    }

    //接受一个消息清空本地缓存
    public void receiver(String name) {
        RedisAndLocalCache cache = (RedisAndLocalCache) this.getCache(name);
        if (cache != null) {
            cache.clearLocal();
        }
    }
}
