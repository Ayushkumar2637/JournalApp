package com.ayushkumar.journalApp.Services.Impl;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;

import java.util.concurrent.TimeUnit;

@SpringBootTest
public class RedisServiceImplTest {

    @Autowired
    private RedisTemplate redisTemplate;

    @Disabled
    @Test
    public void saveToRedis(){
        redisTemplate.opsForValue().set("Test","Testing");
        redisTemplate.opsForValue().set("Nameing","MYJournalApp",300, TimeUnit.SECONDS);
        System.out.println(redisTemplate.opsForValue().get("Test"));
        System.out.println(redisTemplate.opsForValue().get("Nameing"));
    }

}
