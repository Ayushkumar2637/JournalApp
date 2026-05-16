package com.ayushkumar.journalApp.Repository;

import com.ayushkumar.journalApp.ApiResponse.WeatherResponse;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

import java.util.concurrent.TimeUnit;

@Slf4j
@Repository
public class RedisRepository {

    private RedisTemplate redisTemplate;

    public RedisRepository(RedisTemplate redisTemplate){
        this.redisTemplate=redisTemplate;
    }

    public boolean saveWithTTLInSec(String key,Object value,long sec) {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            String jsonValue = objectMapper.writeValueAsString(value);
            redisTemplate.opsForValue().set(key, jsonValue, sec, TimeUnit.MINUTES);
            return true;
        } catch (Exception e){
            log.error("Error is : ",e);
            return false;
        }
    }

    public boolean save(String key, Object value) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        String jsonValue = objectMapper.writeValueAsString(value);
        redisTemplate.opsForValue().set(key, jsonValue);
        return true;
    }

    public <T> T get(String key,Class<T> entityClass){
        try {
            Object value = redisTemplate.opsForValue().get(key);
            ObjectMapper objectMapper = new ObjectMapper();
            if(value==null){
                return null;
            }
            else {
                T finalValue = objectMapper.readValue(value.toString(), entityClass);
                return finalValue;
            }
        } catch (Exception e){
            log.error("Error is : ",e);
            return null;
        }
    }

}
