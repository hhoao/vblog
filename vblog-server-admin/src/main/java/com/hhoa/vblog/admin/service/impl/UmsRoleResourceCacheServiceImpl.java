package com.hhoa.vblog.admin.service.impl;


import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.hhoa.vblog.admin.service.UmsRoleResourceCacheService;
import com.hhoa.vblog.common.service.RedisService;
import com.hhoa.vblog.mgb.model.UmsResource;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Lazy;
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

    private ObjectMapper objectMapper;

    @Lazy
    @Autowired
    public void setObjectMapper(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

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
    public Boolean deleteByRoleName(String roleName) {
        return redisService.del(getRoleNameKey(roleName));
    }

    @Override
    @SneakyThrows
    public List<UmsResource> getByRoleName(String role) {
        String o = (String) redisService.get(getRoleNameKey(role));
        if (o == null) {
            return null;
        }
        return this.objectMapper.readValue(o, new TypeReference<>() {});
    }


    @SneakyThrows
    @Override
    public void setByRoleName(List<UmsResource> retResources, String role) {
        String s = this.objectMapper.writeValueAsString(retResources);
        redisService.set(getRoleNameKey(role), s);
    }

}
