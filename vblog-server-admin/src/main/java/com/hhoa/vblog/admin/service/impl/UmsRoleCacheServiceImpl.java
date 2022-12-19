package com.hhoa.vblog.admin.service.impl;


import com.hhoa.vblog.admin.service.UmsRoleCacheService;
import com.hhoa.vblog.common.service.RedisService;
import com.hhoa.vblog.mgb.model.UmsRole;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

/**
 * The type Ums role cache service.
 *
 * @author hhoa
 * @since 2022 /5/14
 */
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

    /**
     * Generate key by user name string.
     *
     * @param userName the user name
     * @return the string
     */
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
