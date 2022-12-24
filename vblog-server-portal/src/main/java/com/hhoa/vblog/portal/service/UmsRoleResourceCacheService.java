package com.hhoa.vblog.portal.service;


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
