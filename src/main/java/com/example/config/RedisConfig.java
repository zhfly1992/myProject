package com.example.config;

import com.example.redis.RedisReceiver;
import com.example.redis.SubscribeListener;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.listener.PatternTopic;
import org.springframework.data.redis.listener.RedisMessageListenerContainer;
import org.springframework.data.redis.listener.Topic;
import org.springframework.data.redis.listener.adapter.MessageListenerAdapter;
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

import java.util.Map;

/**
 * @author Administrator
 */
@Configuration
public class RedisConfig {

    @Bean
    public RedisTemplate<String, Object> redisTemplate(RedisConnectionFactory factory) {
        RedisTemplate<String, Object> template = new RedisTemplate<String, Object>();
        template.setConnectionFactory(factory);
        // key采用String的序列化方式
        template.setKeySerializer(new StringRedisSerializer());
        // hash的key也采用String的序列化方式
        template.setHashKeySerializer(new StringRedisSerializer());
        // value序列化方式采用jackson
        template.setValueSerializer(new GenericJackson2JsonRedisSerializer());
        // hash的value序列化方式采用jackson
        template.setHashValueSerializer(new GenericJackson2JsonRedisSerializer());
        template.afterPropertiesSet();
        return template;
    }

    @Bean
    public RedisMessageListenerContainer redisMessageListenerContainer(RedisConnectionFactory factory,
                                                                       MessageListenerAdapter listenerAdapter) {

        RedisMessageListenerContainer container = new RedisMessageListenerContainer();

        container.setConnectionFactory(factory);

        /**
         * 添加订阅者监听类，数量不限.PatternTopic定义监听主题,这里监听dj主题
         */
        //   container.addMessageListener(new SubscribeListener(), new PatternTopic("zhTest"));
        container.addMessageListener(listenerAdapter, new PatternTopic("zhTest"));
        return container;
    }

    @Bean
    MessageListenerAdapter listenerAdapter(RedisReceiver receiver) {
        System.out.println("消息适配器1");
        return new MessageListenerAdapter(receiver, "receiveMessage");
    }
}

