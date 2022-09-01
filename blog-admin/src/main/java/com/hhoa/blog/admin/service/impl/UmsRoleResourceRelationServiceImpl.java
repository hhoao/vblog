package com.hhoa.blog.admin.service.impl;

import com.github.pagehelper.PageHelper;

import com.hhoa.blog.admin.bean.PageInfo;
import com.hhoa.blog.admin.service.UmsResourceService;
import com.hhoa.blog.admin.service.UmsRoleResourceCacheService;
import com.hhoa.blog.admin.service.UmsRoleResourceRelationService;
import com.hhoa.blog.admin.service.UmsRoleService;
import com.hhoa.blog.mgb.mapper.UmsRoleResourceRelationMapper;
import com.hhoa.blog.mgb.model.UmsResource;
import com.hhoa.blog.mgb.model.UmsRole;
import com.hhoa.blog.mgb.model.UmsRoleResourceRelation;
import com.hhoa.blog.mgb.model.UmsRoleResourceRelationExample;
import com.hhoa.blog.common.exception.Asserts;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @author hhoa
 * @date 2022/5/15
 **/
@Service
@RequiredArgsConstructor
public class UmsRoleResourceRelationServiceImpl implements UmsRoleResourceRelationService {
    private final UmsRoleResourceRelationMapper roleResourceRelationMapper;
    private UmsResourceService resourceService;
    private UmsRoleService roleService;
    private UmsRoleResourceCacheService roleResourceCacheService;

    @Autowired
    @Lazy
    public void setResourceService(UmsResourceService resourceService) {
        this.resourceService = resourceService;
    }

    @Autowired
    @Lazy
    public void setRoleService(UmsRoleService roleService) {
        this.roleService = roleService;
    }

    @Autowired
    @Lazy
    public void setRoleResourceCacheService(UmsRoleResourceCacheService roleResourceCacheService) {
        this.roleResourceCacheService = roleResourceCacheService;
    }

    @Override
    public List<UmsRole> getRoles(Long resourceId) {
        UmsRoleResourceRelationExample roleRelationExample = new UmsRoleResourceRelationExample();
        roleRelationExample.createCriteria().andResourceIdEqualTo(resourceId);
        List<UmsRoleResourceRelation> roleResourceRelations = roleResourceRelationMapper.selectByExample(roleRelationExample);
        List<UmsRole> roles = new ArrayList<>();
        for (UmsRoleResourceRelation resourceRoleRelation : roleResourceRelations) {
            Long roleId = resourceRoleRelation.getRoleId();
            UmsRole role = roleService.getRole(roleId);
            roles.add(role);
        }
        return roles;
    }

    @Override
    public List<UmsRole> getRoles(String resourceName) {
        UmsResource resource = resourceService.getResource(resourceName);
        return getRoles(resource.getId());
    }

    @Override
    public void deleteRoleResource(Long roleId, Long resourceId) {
        UmsRoleResourceRelationExample roleResourceRelationExample = new UmsRoleResourceRelationExample();
        roleResourceRelationExample.createCriteria().andRoleIdEqualTo(roleId).andResourceIdEqualTo(resourceId);
        int i = roleResourceRelationMapper.deleteByExample(roleResourceRelationExample);
        if (i == 0) {
            Asserts.fail("删除角色资源失败");
        } else {
            //刷新缓存
            roleService.refreshCache(roleId);
        }
    }

    @Override
    public void deleteRoleResources(Long roleId) {
        UmsRoleResourceRelationExample relationExample = new UmsRoleResourceRelationExample();
        relationExample.createCriteria().andRoleIdEqualTo(roleId);
        int i = roleResourceRelationMapper.deleteByExample(relationExample);
        if (i != 0) {
            //刷新缓存
            roleService.refreshCache(roleId);
        }
    }
    @Override
    public List<UmsResource> getRoleResources(Long roleId) {
        return getRoleResources(roleId, false);
    }
    @Override
    public List<UmsResource> getRoleResources(Long roleId, Boolean disableCache) {
        UmsRole retRole = roleService.getRole(roleId);
        List<UmsResource> byRoleName;
        if (disableCache == null) {
            byRoleName = roleResourceCacheService.getByRoleName(retRole.getName());
        }
        else {
            byRoleName = new ArrayList<>();
            UmsRoleResourceRelationExample roleResourceRelationExample = new UmsRoleResourceRelationExample();
            roleResourceRelationExample.createCriteria().andRoleIdEqualTo(roleId);
            List<UmsRoleResourceRelation> retRoleResourceRelations = roleResourceRelationMapper.selectByExample(roleResourceRelationExample);
            for (UmsRoleResourceRelation roleResourceRelation : retRoleResourceRelations) {
                byRoleName.add(resourceService.getResource(roleResourceRelation.getResourceId()));
            }
        }
        return byRoleName;
    }

    @Override
    public void addRoleResource(Long roleId, Long resourceId) {
        UmsRoleResourceRelation roleResourceRelation = new UmsRoleResourceRelation();
        roleResourceRelation.setRoleId(roleId);
        roleResourceRelation.setResourceId(resourceId);
        int insert = roleResourceRelationMapper.insert(roleResourceRelation);
        if (insert == 0) {
            Asserts.fail("增加角色失败");
        }
        roleService.refreshCache(roleId);
    }

    @Override
    public List<UmsResource> listRoleResources(String name, PageInfo pageInfo) {
        PageHelper.startPage(pageInfo.getPageNum(), pageInfo.getPageSize());
        UmsRole roleByRoleName = roleService.getRole(name);
        if (roleByRoleName == null) {
            Asserts.fail("没有该角色");
        }
        return getRoleResources(roleByRoleName.getId());
    }

    @Override
    public void deleteRoleResource(Long id) {
        UmsRoleResourceRelationExample relationExample = new UmsRoleResourceRelationExample();
        relationExample.createCriteria().andResourceIdEqualTo(id);
        List<UmsRoleResourceRelation> retRoleResourceRelations = roleResourceRelationMapper.selectByExample(relationExample);
        int deleteRelationCount = roleResourceRelationMapper.deleteByExample(relationExample);
        if (deleteRelationCount != 0) {
            //刷新缓存
            for (UmsRoleResourceRelation roleResourceRelation : retRoleResourceRelations) {
                roleService.refreshCache(roleResourceRelation.getRoleId());
            }
        }
    }

    @Override
    public void deleteRoleResource(String roleName, String resourceName) {
        UmsRole role = roleService.getRole(roleName);
        UmsResource resource = resourceService.getResource(resourceName);
        deleteRoleResource(role.getId(), resource.getId());
    }

    @Override
    public void addRoleResource(String roleName, String resourceName) {
        UmsResource resource = resourceService.getResource(resourceName);
        UmsRole role = roleService.getRole(roleName);
        addRoleResource(role.getId(), resource.getId());
    }

}
