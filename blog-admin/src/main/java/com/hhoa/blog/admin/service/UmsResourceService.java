package com.hhoa.blog.admin.service;


import com.hhoa.blog.admin.bean.PageInfo;
import com.hhoa.blog.admin.bean.UmsResourceParam;
import com.hhoa.blog.mgb.model.UmsResource;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * The interface Ums resource service.
 *
 * @author hhoa
 * @date 2022 /5/6
 */
public interface UmsResourceService {
    /**
     * 通过资源名获取资源
     *
     * @param name the name
     * @return 资源 resource by name
     */
    UmsResource getResource(String name);

    /**
     * 获取所有资源
     *
     * @return 所有资源 all resources
     */
    List<UmsResource> getAllResources();

    /**
     * 分页获取资源列表
     *
     * @param pageInfo @return 分页资源 list
     * @param resource
     */
    List<UmsResource> list(PageInfo pageInfo, UmsResource resource);

    /**
     * 通过ResourceId获取Resource
     *
     * @param resourceId 资源id
     * @return resource resource by resource id
     */
    UmsResource getResource(Long resourceId);

    /**
     * 添加资源
     *
     * @param resourceParam 资源参数
     */
    @Transactional
    void addResource(UmsResourceParam resourceParam);

    /**
     * 修改资源
     *
     * @param resourceName  the resource name
     * @param resourceParam 资源参数
     */
    @Transactional
    void updateResource(String resourceName, UmsResourceParam resourceParam);

    /**
     * 删除资源
     *
     * @param resourceId 资源id
     */
    @Transactional
    void deleteResource(Long resourceId);

    /**
     * Delete resource.
     *
     * @param resourceName the resource name
     */
    @Transactional
    void deleteResource(String resourceName);
}
