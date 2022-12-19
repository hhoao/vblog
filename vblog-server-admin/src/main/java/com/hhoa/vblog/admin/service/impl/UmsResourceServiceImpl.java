package com.hhoa.vblog.admin.service.impl;


import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.github.pagehelper.PageHelper;
import com.hhoa.vblog.admin.bean.PageInfo;
import com.hhoa.vblog.admin.bean.UmsResourceParam;
import com.hhoa.vblog.admin.dao.UmsResourceDao;
import com.hhoa.vblog.admin.service.UmsResourceService;
import com.hhoa.vblog.admin.service.UmsRoleResourceRelationService;
import com.hhoa.vblog.admin.service.UmsRoleService;
import com.hhoa.vblog.common.exception.Asserts;
import com.hhoa.vblog.mgb.model.UmsResource;
import com.hhoa.vblog.mgb.model.UmsRole;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * The type Ums resource service.
 *
 * @author hhoa
 * @since 2022 /5/5
 */
@Service
@RequiredArgsConstructor
public class UmsResourceServiceImpl implements UmsResourceService {
    private final UmsResourceDao resourceDao;
    private UmsRoleResourceRelationService roleResourceRelationService;
    private UmsRoleService roleService;

    /**
     * Sets role resource relation service.
     *
     * @param roleResourceRelationService the role resource relation service
     */
    @Autowired
    @Lazy
    public void setRoleResourceRelationService(
            UmsRoleResourceRelationService roleResourceRelationService) {
        this.roleResourceRelationService = roleResourceRelationService;
    }

    /**
     * Sets role service.
     *
     * @param roleService the role service
     */
    @Autowired
    @Lazy
    public void setRoleService(UmsRoleService roleService) {
        this.roleService = roleService;
    }

    @Override
    public UmsResource getResource(Long resourceId) {
        UmsResource retResource = resourceDao.selectById(resourceId);
        if (retResource == null) {
            Asserts.fail("没有该资源");
        }
        return retResource;
    }

    @Override
    public UmsResource getResource(String resourceName) {
        UmsResource resource = new UmsResource();
        resource.setName(resourceName);
        List<UmsResource> resources = resourceDao.selectList(new QueryWrapper<>(resource));
        if (resources != null && resources.size() > 0) {
            return resources.get(0);
        }
        return null;
    }

    @Override
    public List<UmsResource> getAllResources() {
        return resourceDao.selectList(new QueryWrapper<>());
    }


    @Override
    public List<UmsResource> list(PageInfo pageInfo, UmsResource resource) {
        PageHelper.startPage(pageInfo.getPageNum(), pageInfo.getPageSize());
        return resourceDao.selectList(new QueryWrapper<>(resource));
    }



    @Override
    public void addResource(UmsResourceParam resourceParam) {
        UmsResource resource = new UmsResource();
        BeanUtil.copyProperties(resourceParam, resource);
        int insert = resourceDao.insert(resource);
        if (insert == 0) {
            Asserts.fail("插入资源失败");
        }
    }


    @Override
    public void updateResource(String resourceName, UmsResourceParam resourceParam) {
        UmsResource resourceByName = getResource(resourceName);
        //获取影响的角色
        List<UmsRole> rolesByResourceId =
                roleResourceRelationService.getRoles(resourceByName.getId());
        BeanUtils.copyProperties(resourceParam, resourceByName);
        int successCount = resourceDao.updateById(resourceByName);
        if (successCount == 0) {
            Asserts.fail("更新失败");
        }
        //刷新缓存
        for (UmsRole role : rolesByResourceId) {
            roleService.refreshCache(role);
        }
    }

    @Override
    public void deleteResource(Long resourceId) {
        //获取影响的角色
        List<UmsRole> roles = roleResourceRelationService.getRoles(resourceId);

        roleResourceRelationService.deleteRoleResource(resourceId);
        int i = resourceDao.deleteById(resourceId);
        if (i == 0) {
            Asserts.fail("删除资源失败");
        }
        //刷新缓存
        for (UmsRole role : roles) {
            roleService.refreshCache(role);
        }
    }

    @Override
    public void deleteResource(String resourceName) {
        //获取影响的角色
        List<UmsRole> roles = roleResourceRelationService.getRoles(resourceName);
        UmsResource resource = getResource(resourceName);
        roleResourceRelationService.deleteRoleResource(resource.getId());
        int i = resourceDao.deleteById(resource.getId());
        if (i == 0) {
            Asserts.fail("删除资源失败");
        }
        //刷新缓存
        for (UmsRole role : roles) {
            roleService.refreshCache(role);
        }
    }
}
