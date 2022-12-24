package com.hhoa.vblog.portal.service;


import com.hhoa.vblog.mgb.model.UmsResource;
import com.hhoa.vblog.mgb.model.UmsRole;
import com.hhoa.vblog.portal.bean.PageInfo;

import java.util.List;

/**
 * The interface Ums role service.
 *
 * @author hhoa
 * @since 2022 /5/15
 */
public interface UmsRoleService {
    /**
     * Refresh cache.
     */
    void refreshCache();

    /**
     * Gets role resources.
     *
     * @param roleId the role id
     * @return the role resources
     */
    List<UmsResource> getRoleResources(Long roleId);

    /**
     * Gets role resources.
     *
     * @param roleId       the role id
     * @param disableCache the disable cache
     * @return the role resources
     */
    List<UmsResource> getRoleResources(Long roleId, Boolean disableCache);

    /**
     * Gets role.
     *
     * @param roleName the role name
     * @return the role
     */
    UmsRole getRole(String roleName);

    /**
     * 通过roleId获取角色.
     *
     * @param roleId roleId
     * @return 角色 role by role id
     */
    UmsRole getRole(Long roleId);

    /**
     * 获取所有角色.
     *
     * @return role all roles
     */
    List<UmsRole> getAllRoles();

    /**
     * 分页获取角色列表.
     *
     * @param pageInfo @return 分页角色 list
     * @param role     the role
     * @return the list
     */
    List<UmsRole> list(PageInfo pageInfo, UmsRole role);

}
