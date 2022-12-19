package com.hhoa.vblog.admin.config;

import com.hhoa.vblog.common.service.RedisService;
import com.hhoa.vblog.common.service.impl.RedisServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.core.RedisTemplate;

/**
 * The type Admin redis config.
 *
 * @author hhoa
 * @since 2022 /5/10
 */
@Configuration
public class AdminRedisConfig {
    /**
     * Redis service redis service.
     *
     * @param redisTemplate the redis template
     * @return the redis service
     */
    @Bean
    public RedisService redisService(RedisTemplate<String, Object> redisTemplate) {
        return new RedisServiceImpl(redisTemplate);
    }
}
