package com.hhoa.vblog.admin.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.github.pagehelper.PageHelper;
import com.hhoa.vblog.admin.bean.PageInfo;
import com.hhoa.vblog.admin.dao.UmsRoleResourceRelationDao;
import com.hhoa.vblog.admin.service.UmsResourceService;
import com.hhoa.vblog.admin.service.UmsRoleResourceCacheService;
import com.hhoa.vblog.admin.service.UmsRoleResourceRelationService;
import com.hhoa.vblog.admin.service.UmsRoleService;
import com.hhoa.vblog.common.exception.Asserts;
import com.hhoa.vblog.mgb.model.UmsResource;
import com.hhoa.vblog.mgb.model.UmsRole;
import com.hhoa.vblog.mgb.model.UmsRoleResourceRelation;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * The type Ums role resource relation service.
 *
 * @author hhoa
 */
@Service
@RequiredArgsConstructor
public class UmsRoleResourceRelationServiceImpl implements UmsRoleResourceRelationService {
    private final UmsRoleResourceRelationDao roleResourceRelationDao;
    private UmsResourceService resourceService;
    private UmsRoleService roleService;
    private UmsRoleResourceCacheService roleResourceCacheService;

    /**
     * Sets resource service.
     *
     * @param resourceService the resource service
     */
    @Autowired
    @Lazy
    public void setResourceService(UmsResourceService resourceService) {
        this.resourceService = resourceService;
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

    /**
     * Sets role resource cache service.
     *
     * @param roleResourceCacheService the role resource cache service
     */
    @Autowired
    @Lazy
    public void setRoleResourceCacheService(UmsRoleResourceCacheService roleResourceCacheService) {
        this.roleResourceCacheService = roleResourceCacheService;
    }

    @Override
    public List<UmsRole> getRoles(Long resourceId) {
        UmsRoleResourceRelation roleResourceRelation = new UmsRoleResourceRelation();
        roleResourceRelation.setResourceId(resourceId);
        List<UmsRoleResourceRelation> roleResourceRelations =
                roleResourceRelationDao.selectList(new QueryWrapper<>(roleResourceRelation));
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
    public void deleteRoleResources(Long roleId) {
        UmsRoleResourceRelation roleResourceRelation = new UmsRoleResourceRelation();
        roleResourceRelation.setRoleId(roleId);
        int i = roleResourceRelationDao.delete(new QueryWrapper<>(roleResourceRelation));
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
        } else {
            byRoleName = new ArrayList<>();
            UmsRoleResourceRelation roleResourceRelation = new UmsRoleResourceRelation();
            roleResourceRelation.setRoleId(roleId);
            List<UmsRoleResourceRelation> retRoleResourceRelations =
                    roleResourceRelationDao.selectList(new QueryWrapper<>(roleResourceRelation));
            for (UmsRoleResourceRelation roleResourceRelationItem : retRoleResourceRelations) {
                byRoleName.add(
                        resourceService.getResource(roleResourceRelationItem.getResourceId()));
            }
        }
        return byRoleName;
    }

    @Override
    public void addRoleResource(String roleName, String resourceName) {
        UmsResource resource = resourceService.getResource(resourceName);
        UmsRole role = roleService.getRole(roleName);
        addRoleResource(role.getId(), resource.getId());
    }

    @Override
    public void addRoleResource(Long roleId, Long resourceId) {
        UmsRoleResourceRelation roleResourceRelation = new UmsRoleResourceRelation();
        roleResourceRelation.setRoleId(roleId);
        roleResourceRelation.setResourceId(resourceId);
        int insert = roleResourceRelationDao.insert(roleResourceRelation);
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
        UmsRoleResourceRelation roleResourceRelation = new UmsRoleResourceRelation();
        roleResourceRelation.setResourceId(id);
        List<UmsRoleResourceRelation> retRoleResourceRelations =
                roleResourceRelationDao.selectList(new QueryWrapper<>(roleResourceRelation));
        int deleteRelationCount =
                roleResourceRelationDao.delete(new QueryWrapper<>(roleResourceRelation));
        if (deleteRelationCount != 0) {
            //刷新缓存
            for (UmsRoleResourceRelation roleResourceRelationItem : retRoleResourceRelations) {
                roleService.refreshCache(roleResourceRelationItem.getRoleId());
            }
        }
    }

    @Override
    public void deleteRoleResource(Long roleId, Long resourceId) {
        UmsRoleResourceRelation roleResourceRelation = new UmsRoleResourceRelation();
        roleResourceRelation.setRoleId(roleId);
        roleResourceRelation.setResourceId(resourceId);
        int i = roleResourceRelationDao.delete(new QueryWrapper<>(roleResourceRelation));
        if (i == 0) {
            Asserts.fail("删除角色资源失败");
        } else {
            //刷新缓存
            roleService.refreshCache(roleId);
        }
    }

    @Override
    public void deleteRoleResource(String roleName, String resourceName) {
        UmsRole role = roleService.getRole(roleName);
        UmsResource resource = resourceService.getResource(resourceName);
        deleteRoleResource(role.getId(), resource.getId());
    }



}
