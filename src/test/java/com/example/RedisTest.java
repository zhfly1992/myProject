package com.example;

import com.example.redis.PublishService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.ListOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@SpringBootTest
public class RedisTest {
    @Autowired
    RedisTemplate<String,Object> redisTemplate;
    @Autowired
    PublishService publishService;

    @Test
    public void testSet(){
        HashMap<String,Object> map = new HashMap<>();
        map.put("key","value");
        map.put("key2","value2");
        redisTemplate.opsForHash().putAll("mapTest",map);
        System.out.println(redisTemplate.opsForHash().get("mapTest","key"));
        Map<Object, Object> map1 = redisTemplate.opsForHash().entries("mapTest");

        System.out.println(map1.toString());
        System.out.println("yes");
    }

    @Test
    public void producerTest(){
        for(int i=1; i<=10; i++){

            publishService.publish("zhTest", "like "+i+" 次");
        }
    }

    @Test
    public void consumerTest(){
        ListOperations<String, Object> listRedis = redisTemplate.opsForList();
        for (int i = 0;i <10;i++){
            String storgeTest = listRedis.rightPop("storgeTest").toString();
            System.out.println("已取出" + storgeTest);
        }
    }
}
