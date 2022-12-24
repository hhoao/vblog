package com.hhoa.vblog.portal.service;


import com.hhoa.vblog.mgb.model.UmsResource;
import com.hhoa.vblog.portal.bean.PageInfo;

import java.util.List;

/**
 * The interface Ums resource service.
 *
 * @author hhoa
 * @since  2022 /5/6
 */
public interface UmsResourceService {

    /**
     * 通过ResourceId获取Resource.
     *
     * @param resourceId 资源id
     * @return resource resource by resource id
     */
    UmsResource getResource(Long resourceId);

    /**
     * 获取所有资源.
     *
     * @return 所有资源 all resources
     */
    List<UmsResource> getAllResources();

    /**
     * 分页获取资源列表.
     *
     * @param pageInfo 页
     * @param resource 资源
     * @return 分页资源 list
     */
    List<UmsResource> list(PageInfo pageInfo, UmsResource resource);
}
