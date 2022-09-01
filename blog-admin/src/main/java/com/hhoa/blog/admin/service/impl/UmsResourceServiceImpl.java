package com.hhoa.blog.admin.service.impl;


import cn.hutool.core.bean.BeanUtil;
import com.github.pagehelper.PageHelper;

import com.hhoa.blog.admin.bean.PageInfo;
import com.hhoa.blog.admin.bean.UmsResourceParam;
import com.hhoa.blog.admin.service.UmsResourceService;
import com.hhoa.blog.admin.service.UmsRoleResourceRelationService;
import com.hhoa.blog.admin.service.UmsRoleService;
import com.hhoa.blog.mgb.mapper.UmsResourceMapper;
import com.hhoa.blog.mgb.model.UmsResource;
import com.hhoa.blog.mgb.model.UmsResourceExample;
import com.hhoa.blog.mgb.model.UmsRole;
import com.hhoa.blog.common.exception.Asserts;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author hhoa
 * @date 2022/5/5
 **/
@Service
@RequiredArgsConstructor
public class UmsResourceServiceImpl implements UmsResourceService {
    private final UmsResourceMapper resourceMapper;
    private UmsRoleResourceRelationService roleResourceRelationService;
    private UmsRoleService roleService;

    @Autowired
    @Lazy
    public void setRoleResourceRelationService(UmsRoleResourceRelationService roleResourceRelationService) {
        this.roleResourceRelationService = roleResourceRelationService;
    }

    @Autowired
    @Lazy
    public void setRoleService(UmsRoleService roleService) {
        this.roleService = roleService;
    }

    @Override
    public UmsResource getResource(String resourceName) {
        UmsResourceExample resourceExample = new UmsResourceExample();
        resourceExample.createCriteria().andNameEqualTo(resourceName);
        List<UmsResource> resources = resourceMapper.selectByExample(resourceExample);
        if (resources != null && resources.size() > 0){
            return resources.get(0);
        }
        return null;
    }

    @Override
    public List<UmsResource> getAllResources() {
        return resourceMapper.selectByExample(new UmsResourceExample());
    }


    private UmsResourceExample getResourceExample(UmsResource resource){
        UmsResourceExample resourceExample = new UmsResourceExample();
        if (resource != null) {
            UmsResourceExample.Criteria criteria = resourceExample.createCriteria();
            if (resource.getId() != null) {
                criteria.andIdEqualTo(resource.getId());
            }
            if (resource.getName() != null) {
                criteria.andNameEqualTo(resource.getName());
            }
            if (resource.getDescription() != null) {
                criteria.andDescriptionLike(resource.getDescription());
            }
            if (resource.getMethod() != null) {
                criteria.andMethodEqualTo(resource.getMethod());
            }
            if (resource.getUrl() != null) {
                criteria.andUrlEqualTo(resource.getUrl());
            }
        }
        return resourceExample;
    }

    @Override
    public List<UmsResource> list(PageInfo pageInfo, UmsResource resource) {
        PageHelper.startPage(pageInfo.getPageNum(), pageInfo.getPageSize());
        UmsResourceExample resourceExample = getResourceExample(resource);
        return resourceMapper.selectByExample(resourceExample);
    }

    @Override
    public UmsResource getResource(Long resourceId) {
        UmsResource retResource = resourceMapper.selectByPrimaryKey(resourceId);
        if (retResource == null){
            Asserts.fail("没有该资源");
        }
        return retResource;
    }

    @Override
    public void addResource(UmsResourceParam resourceParam) {
        UmsResource resource = new UmsResource();
        BeanUtil.copyProperties(resourceParam, resource);
        int insert = resourceMapper.insert(resource);
        if (insert == 0){
            Asserts.fail("插入资源失败");
        }
    }

    @Override
    public void updateResource(String resourceName, UmsResourceParam resourceParam) {
        UmsResource resourceByName = getResource(resourceName);
        //获取影响的角色
        List<UmsRole> rolesByResourceId = roleResourceRelationService.getRoles(resourceByName.getId());
        BeanUtils.copyProperties(resourceParam, resourceByName);
        int successCount = resourceMapper.updateByPrimaryKeySelective(resourceByName);
        if (successCount == 0){
            Asserts.fail("更新失败");
        }
        //刷新缓存
        for (UmsRole role : rolesByResourceId){
            roleService.refreshCache(role);
        }
    }
    @Override
    public void deleteResource(Long resourceId) {
        //获取影响的角色
        List<UmsRole> roles = roleResourceRelationService.getRoles(resourceId);

        roleResourceRelationService.deleteRoleResource(resourceId);
        int i = resourceMapper.deleteByPrimaryKey(resourceId);
        if (i == 0){
            Asserts.fail("删除资源失败");
        }
        //刷新缓存
        for (UmsRole role : roles){
            roleService.refreshCache(role);
        }
    }

    @Override
    public void deleteResource(String resourceName) {
        //获取影响的角色
        List<UmsRole> roles = roleResourceRelationService.getRoles(resourceName);
        UmsResource resource = getResource(resourceName);
        roleResourceRelationService.deleteRoleResource(resource.getId());
        int i = resourceMapper.deleteByPrimaryKey(resource.getId());
        if (i == 0){
            Asserts.fail("删除资源失败");
        }
        //刷新缓存
        for (UmsRole role : roles){
            roleService.refreshCache(role);
        }
    }
}
