package com.isaac.springboot.springboot_in_action.conf.cache;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.cache.RedisCacheConfiguration;
import org.springframework.data.redis.cache.RedisCacheWriter;
import org.springframework.data.redis.connection.Message;
import org.springframework.data.redis.connection.MessageListener;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.listener.PatternTopic;
import org.springframework.data.redis.listener.RedisMessageListenerContainer;
import org.springframework.data.redis.listener.adapter.MessageListenerAdapter;
import org.springframework.data.redis.serializer.JdkSerializationRedisSerializer;
import org.springframework.data.redis.serializer.RedisSerializationContext;

//@Configuration
public class CacheConfig {
    @Bean
    MessageListenerAdapter listenerAdapter(final TwoLevelCacheManager cacheManager){
        MessageListenerAdapter adapter = new MessageListenerAdapter(new MessageListener() {
            @Override
            public void onMessage(Message message, byte[] bytes) {
                try {
                    String cacheName = new String(message.getBody(), "UTF-8");
                    cacheManager.receiver(cacheName);
                }catch (Exception e){
                    e.printStackTrace();
                }
            }
        });
        //可改变序列化策略（默认StringRedisSerializer）
//        adapter.setSerializer(new JdkSerializationRedisSerializer());
        return adapter;
    }
    @Bean
    RedisMessageListenerContainer container(RedisConnectionFactory connectionFactory, MessageListenerAdapter listenerAdapter){
        RedisMessageListenerContainer container = new RedisMessageListenerContainer();
        container.setConnectionFactory(connectionFactory);
        container.addMessageListener(listenerAdapter,new PatternTopic("redis.cache.topic"));
        return container;
    }

    @Bean
    public TwoLevelCacheManager cacheManager(StringRedisTemplate redisTemplate){
        //RedisCache需要一个RedisCacheWriter来实现读写Redis
        RedisCacheWriter writer = RedisCacheWriter.lockingRedisCacheWriter(redisTemplate.getConnectionFactory());
        //序列化
        RedisSerializationContext.SerializationPair<Object> pair = RedisSerializationContext.SerializationPair.fromSerializer(new JdkSerializationRedisSerializer());
        //构造一个RedisCache的配置，比如是否使用前缀，比如key value的序列化机制
        RedisCacheConfiguration config = RedisCacheConfiguration.defaultCacheConfig().serializeValuesWith(pair);
        //创建CacheManager,并返回给Spring容器
        TwoLevelCacheManager cacheManager = new TwoLevelCacheManager(redisTemplate, writer, config);
        return cacheManager;
    }
}
