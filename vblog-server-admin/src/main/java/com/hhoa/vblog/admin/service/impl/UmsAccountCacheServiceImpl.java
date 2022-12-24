package com.hhoa.vblog.admin.service.impl;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.hhoa.vblog.admin.bean.UmsAccountDetails;
import com.hhoa.vblog.admin.service.UmsAccountCacheService;
import com.hhoa.vblog.common.service.RedisService;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

/**
 * token缓存.
 *
 * @author hhoa
 * @since 2022/5/14
 **/
@Service
//@RequiredArgsConstructor
public class UmsAccountCacheServiceImpl implements UmsAccountCacheService {
    @Value("${ret.redis.database}")
    private String redisDatabase;
    @Value("${ret.redis.expire.token}")
    private Long redisExpire;
    @Value("${ret.redis.key.administrator}")
    private String redisKey;
    private RedisService redisService;

    private ObjectMapper objectMapper;

    @Lazy
    @Autowired
    public void setObjectMapper(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    @Autowired
    @Lazy
    public void setRedisService(RedisService redisService) {
        this.redisService = redisService;
    }

    private String getUserNameKey(String username) {
        return redisDatabase + ":" + redisKey + ":" + username;
    }

    @Override
    public void expire(String username) {
        redisService.expire(getUserNameKey(username), redisExpire);
    }

    @Override
    public void expire(String username, Long expiration) {
        redisService.expire(getUserNameKey(username), expiration);
    }

    @Override
    public void setKey(String username, UmsAccountDetails userDetails) {
        try {
            redisService.set(getUserNameKey(username),
                    this.objectMapper.writeValueAsString(userDetails),
                    redisExpire);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public boolean hasKey(String username) {
        return redisService.hasKey(getUserNameKey(username));
    }

    @SneakyThrows
    @Override
    public UmsAccountDetails getKey(String username) {
        String o = (String) redisService.get(getUserNameKey(username));
        if (o == null) {
            return null;
        }
        return this.objectMapper.readValue(o, UmsAccountDetails.class);
    }

    @Override
    public void delKey(String username) {
        redisService.del(getUserNameKey(username));
    }

}
