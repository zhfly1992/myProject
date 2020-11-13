package com.example.redis;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

@Component
public class PublishService {
    @Autowired
    RedisTemplate<String, Object> redisTemplate;

    public void publish(String channel, Object message) {
        redisTemplate.convertAndSend(channel, message);
    }
}

