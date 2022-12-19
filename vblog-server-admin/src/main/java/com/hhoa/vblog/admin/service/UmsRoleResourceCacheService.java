package com.hhoa.vblog.admin.service;


import com.hhoa.vblog.mgb.model.UmsResource;

import java.util.List;

/**
 * The interface Ums role resource cache service.
 *
 * @author hhoa
 * @since 2022 /5/8
 */
public interface UmsRoleResourceCacheService {
    /**
     * 通过角色名字删除缓存.
     *
     * @param roleName 角色名字
     * @return 是否删除 boolean
     */
    Boolean deleteByRoleName(String roleName);

    /**
     * 通过用户名获取资源.
     *
     * @param role role
     * @return resources by role name
     */
    List<UmsResource> getByRoleName(String role);

    /**
     * 通过用户名设置资源.
     *
     * @param retResources resources
     * @param role         role
     */
    void setByRoleName(List<UmsResource> retResources, String role);
}
