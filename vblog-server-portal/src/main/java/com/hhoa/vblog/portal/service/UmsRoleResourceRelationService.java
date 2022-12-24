package com.hhoa.vblog.portal.service;


import com.hhoa.vblog.mgb.model.UmsResource;
import com.hhoa.vblog.portal.bean.PageInfo;

import java.util.List;

/**
 * The interface Ums role resource relation service.
 *
 * @author hhoa
 * @since 2022/5/15
 */
public interface UmsRoleResourceRelationService {

    List<UmsResource> getRoleResources(Long roleId);

    /**
     * 通过角色id获取资源.
     *
     * @param roleId 角色id
     * @return 资源 resources by role id
     */
    List<UmsResource> getRoleResources(Long roleId, Boolean disableCache);

    /**
     * 分页获取角色资源列表.
     *
     * @param name     角色名
     * @param pageInfo the page info
     * @return 角色资源列表页 list
     */
    List<UmsResource> listRoleResources(String name, PageInfo pageInfo);
}
