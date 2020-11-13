package com.example.redis;


import org.springframework.stereotype.Component;

@Component
public class RedisReceiver {
    public void receiveMessage(String message) {
        // TODO 这里是收到通道的消息之后执行的方法
        System.out.println("自定义接受,接收到：" + message);
    }
}
