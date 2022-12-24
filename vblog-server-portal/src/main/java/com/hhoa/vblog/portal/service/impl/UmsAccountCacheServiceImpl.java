package com.hhoa.vblog.portal.service.impl;


import com.hhoa.vblog.common.service.RedisService;
import com.hhoa.vblog.portal.bean.UmsAccountDetails;
import com.hhoa.vblog.portal.service.UmsAccountCacheService;
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

    @Autowired
    @Lazy
    public void setRedisService(RedisService redisService) {
        this.redisService = redisService;
    }

    private String getUserNameKey(String username) {
        return redisDatabase + ":" + redisKey + ":" + username;
    }

    @Override
    public void setKey(String username, UmsAccountDetails userDetails) {
        redisService.set(getUserNameKey(username), userDetails, redisExpire);
    }

    @Override
    public boolean hasKey(String username) {
        return redisService.hasKey(getUserNameKey(username));
    }

    @Override
    public UmsAccountDetails getKey(String username) {
        return (UmsAccountDetails) redisService.get(getUserNameKey(username));
    }

    @Override
    public void delKey(String username) {
        redisService.del(getUserNameKey(username));
    }

}
