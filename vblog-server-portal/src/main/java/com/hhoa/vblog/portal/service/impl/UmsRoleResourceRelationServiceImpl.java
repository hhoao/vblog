package com.hhoa.vblog.portal.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.github.pagehelper.PageHelper;
import com.hhoa.vblog.common.exception.Asserts;
import com.hhoa.vblog.mgb.model.UmsResource;
import com.hhoa.vblog.mgb.model.UmsRole;
import com.hhoa.vblog.mgb.model.UmsRoleResourceRelation;
import com.hhoa.vblog.portal.bean.PageInfo;
import com.hhoa.vblog.portal.dao.UmsRoleResourceRelationDao;
import com.hhoa.vblog.portal.service.UmsResourceService;
import com.hhoa.vblog.portal.service.UmsRoleResourceCacheService;
import com.hhoa.vblog.portal.service.UmsRoleResourceRelationService;
import com.hhoa.vblog.portal.service.UmsRoleService;
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
    public List<UmsResource> listRoleResources(String name, PageInfo pageInfo) {
        PageHelper.startPage(pageInfo.getPageNum(), pageInfo.getPageSize());
        UmsRole roleByRoleName = roleService.getRole(name);
        if (roleByRoleName == null) {
            Asserts.fail("没有该角色");
        }
        return getRoleResources(roleByRoleName.getId());
    }
}
