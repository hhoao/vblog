package com.hhoa.blog.admin.service;



import com.hhoa.blog.mgb.model.UmsResource;

import java.util.List;

/**
 * @author hhoa
 * @date 2022/5/8
 **/
public interface UmsRoleResourceCacheService {
    /**
     * 通过角色名字删除缓存
     * @param roleName 角色名字
     * @return 是否删除
     */
    Boolean deleteByRoleName(String roleName);
    /**
     * 通过用户名获取资源
     * @param role role
     * @return resources
     */
    List<UmsResource> getByRoleName(String role);

    /**
     * 通过用户名设置资源
     * @param retResources resources
     * @param role role
     */
    void setByRoleName(List<UmsResource> retResources, String role);
}
