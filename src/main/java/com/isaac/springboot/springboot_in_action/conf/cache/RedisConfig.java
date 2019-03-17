package com.isaac.springboot.springboot_in_action.conf.cache;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.JdkSerializationRedisSerializer;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

/**
 * 自定义RedisTemplate的序列化策略(默认JDK序列化)——如将key的序列化策略改为StringRedisSerializer更为方便
 */
@Configuration
public class RedisConfig {

    //这里很奇怪的一点 如果bean的名字不命名为redisTemplate序列化策略没有生效,应该是autowire的不是这个bean，但是应该是根据类型匹配的啊?
    //自己的理解：思维停留在根据类型匹配上了。这里因为redisTemplate是一个实例（bean），而不是以前的那种interface。autowire默认匹配redisTemplate，应该是这样,但这样是否违背的spring单例的原则？
    @Bean("redisTemplate")
    public RedisTemplate<Object, Object> strKeyRedisTemplate(RedisConnectionFactory redisConnectionFactory) {
        RedisTemplate<Object, Object> redisTemplate = new RedisTemplate<>();
        redisTemplate.setConnectionFactory(redisConnectionFactory);
        RedisSerializer<String> stringRedisSerializer = new StringRedisSerializer();
        redisTemplate.setKeySerializer(stringRedisSerializer);
        return redisTemplate;
    }

    @Bean("redisTemplate1")
    public RedisTemplate<Object, Object> strKeyRedisTemplate1(RedisConnectionFactory redisConnectionFactory) {
        RedisTemplate<Object, Object> redisTemplate = new RedisTemplate<>();
        redisTemplate.setConnectionFactory(redisConnectionFactory);
        redisTemplate.setDefaultSerializer(new GenericJackson2JsonRedisSerializer());
        return redisTemplate;
    }


    @Bean("redisTemplate2")
    public RedisTemplate<Object, Object> strKeyRedisTemplate2(RedisConnectionFactory redisConnectionFactory) {
        RedisTemplate<Object, Object> redisTemplate = new RedisTemplate<>();
        redisTemplate.setConnectionFactory(redisConnectionFactory);
        redisTemplate.setDefaultSerializer(new JdkSerializationRedisSerializer());
        return redisTemplate;
    }
}
