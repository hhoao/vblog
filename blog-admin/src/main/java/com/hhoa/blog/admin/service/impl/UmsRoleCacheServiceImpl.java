package com.hhoa.blog.admin.service.impl;


import com.hhoa.blog.admin.service.UmsRoleCacheService;
import com.hhoa.blog.common.service.RedisService;
import com.hhoa.blog.mgb.model.UmsRole;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

/**
 * @author hhoa
 * @date 2022/5/14
 **/
@Service
@RequiredArgsConstructor
public class UmsRoleCacheServiceImpl implements UmsRoleCacheService {
    private final RedisService redisService;
    @Value("${ret.redis.database}")
    private String redisDatabase;
    @Value("${ret.redis.expire.common}")
    private Long redisExpire;
    @Value("${ret.redis.key.role}")
    private String redisKeyRole;

    public String generateKeyByUserName(String userName) {
        return redisDatabase + ":" + redisKeyRole + ":" + userName;
    }

    @Override
    public void delKey(String userName) {
        redisService.del(generateKeyByUserName(userName));
    }

    @Override
    public void setKeyByUserName(String userName, UmsRole role) {
        redisService.set(generateKeyByUserName(userName), role, redisExpire);
    }

    @Override
    public void setKeyByUserName(String userName, UmsRole role, Long expire) {
        redisService.set(generateKeyByUserName(userName), role, redisExpire);
    }

    @Override
    public UmsRole getKeysByUserName(String username) {
        return (UmsRole) redisService.get(generateKeyByUserName(username));
    }
}
