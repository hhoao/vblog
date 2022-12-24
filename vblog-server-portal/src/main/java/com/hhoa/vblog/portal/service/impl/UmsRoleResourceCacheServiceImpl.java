package com.hhoa.vblog.portal.service.impl;


import com.hhoa.vblog.common.service.RedisService;
import com.hhoa.vblog.mgb.model.UmsResource;
import com.hhoa.vblog.portal.service.UmsRoleResourceCacheService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * The type Ums role resource cache service.
 *
 * @author hhoa
 * @since 2022 /5/8
 */
@Service
@RequiredArgsConstructor
public class UmsRoleResourceCacheServiceImpl implements UmsRoleResourceCacheService {
    private final RedisService redisService;

    @Value("${ret.redis.database}")
    private String redisDatabase;
    @Value("${ret.redis.key.resource-role}")
    private String redisKeyResourceRole;

    /**
     * Gets role name key.
     *
     * @param role the role
     * @return the role name key
     */
    public String getRoleNameKey(String role) {
        return redisDatabase + ":" + redisKeyResourceRole + ":" + role;
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
