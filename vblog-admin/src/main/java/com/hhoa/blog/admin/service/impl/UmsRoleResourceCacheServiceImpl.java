package com.hhoa.blog.admin.service.impl;


import com.hhoa.blog.admin.service.UmsRoleResourceCacheService;
import com.hhoa.blog.common.service.RedisService;
import com.hhoa.blog.mgb.model.UmsResource;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author hhoa
 * @date 2022/5/8
 **/
@Service
@RequiredArgsConstructor
public class UmsRoleResourceCacheServiceImpl implements UmsRoleResourceCacheService {
    private final RedisService redisService;

    @Value("${ret.redis.database}")
    private String redisDatabase;
    @Value("${ret.redis.key.resource-role}")
    private String redisKeyResourceRole;

    public String getRoleNameKey(String role) {
        return redisDatabase + ":" + redisKeyResourceRole + ":" + role;
    }

    @Override
    public Boolean deleteByRoleName(String roleName) {
        return redisService.del(getRoleNameKey(roleName));
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<UmsResource> getByRoleName(String role) {
        return (List<UmsResource>) redisService.get(getRoleNameKey(role));
    }

    @Override
    public void setByRoleName(List<UmsResource> retResources, String role) {
        redisService.set(getRoleNameKey(role), retResources);
    }

}
