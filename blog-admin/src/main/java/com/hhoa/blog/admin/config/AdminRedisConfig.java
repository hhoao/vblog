package com.hhoa.blog.admin.config;

import com.hhoa.blog.common.service.RedisService;
import com.hhoa.blog.common.service.impl.RedisServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.core.RedisTemplate;

/**
 * @author hhoa
 * @date 2022/5/10
 **/
@Configuration
public class AdminRedisConfig {
    @Bean
    public RedisService redisService(RedisTemplate<String, Object> redisTemplate){
        return new RedisServiceImpl(redisTemplate);
    }
}
