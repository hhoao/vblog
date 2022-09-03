package com.hhoa.blog.admin.service.impl;


import com.hhoa.blog.admin.bean.UmsAdministratorDetails;
import com.hhoa.blog.admin.service.UmsAdministratorCacheService;
import com.hhoa.blog.common.service.RedisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

/**
 * token缓存
 *
 * @author hhoa
 * @date 2022/5/14
 **/
@Service
//@RequiredArgsConstructor
public class UmsAdministratorCacheServiceImpl implements UmsAdministratorCacheService {
    @Value("${ret.redis.database}")
    private String redisDatabase;
    @Value("${ret.redis.expire.token}")
    private Long redisExpire;
    @Value("${ret.redis.key.administrator}")
    private String redisKey;
    private RedisService redisService;

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
    public void setKey(String username, UmsAdministratorDetails userDetails) {
        redisService.set(getUserNameKey(username), userDetails, redisExpire);
    }

    @Override
    public boolean hasKey(String username) {
        return redisService.hasKey(getUserNameKey(username));
    }

    @Override
    public UmsAdministratorDetails getKey(String username) {
        return (UmsAdministratorDetails) redisService.get(getUserNameKey(username));
    }

    @Override
    public void delKey(String username) {
        redisService.del(getUserNameKey(username));
    }

}
