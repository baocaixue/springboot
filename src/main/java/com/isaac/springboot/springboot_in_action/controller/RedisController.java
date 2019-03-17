package com.isaac.springboot.springboot_in_action.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;

public class RedisController {
    @Autowired
    private RedisTemplate redisTemplate;


}
