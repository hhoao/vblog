package com.hhoa.vblog.portal.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.github.pagehelper.PageHelper;
import com.hhoa.vblog.common.exception.Asserts;
import com.hhoa.vblog.mgb.model.UmsResource;
import com.hhoa.vblog.mgb.model.UmsRole;
import com.hhoa.vblog.portal.bean.PageInfo;
import com.hhoa.vblog.portal.dao.UmsRoleDao;
import com.hhoa.vblog.portal.service.UmsRoleResourceCacheService;
import com.hhoa.vblog.portal.service.UmsRoleResourceRelationService;
import com.hhoa.vblog.portal.service.UmsRoleService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * The type Ums role service.
 *
 * @author hhoa
 * @since 2022 /5/15
 */
@Service
@RequiredArgsConstructor
public class UmsRoleServiceImpl implements UmsRoleService, ApplicationRunner {
    private final UmsRoleDao roleDao;
    private UmsRoleResourceRelationService roleResourceRelationService;
    private UmsRoleResourceCacheService resourceCacheService;

    /**
     * Sets role resource relation service.
     *
     * @param roleResourceRelationService the role resource relation service
     */
    @Lazy
    @Autowired
    public void setRoleResourceRelationService(
            UmsRoleResourceRelationService roleResourceRelationService) {
        this.roleResourceRelationService = roleResourceRelationService;
    }

    /**
     * Sets resource cache service.
     *
     * @param resourceCacheService the resource cache service
     */
    @Autowired
    @Lazy
    public void setResourceCacheService(UmsRoleResourceCacheService resourceCacheService) {
        this.resourceCacheService = resourceCacheService;
    }

    @Override
    public void refreshCache() {
        List<UmsRole> roles = getAllRoles();
        for (UmsRole role : roles) {
            List<UmsResource> roleResources = getRoleResources(role.getId(), false);
            resourceCacheService.setByRoleName(roleResources, role.getName());
        }
    }

    @Override
    public List<UmsResource> getRoleResources(Long roleId) {
        return roleResourceRelationService.getRoleResources(roleId, false);
    }

    @Override
    public List<UmsResource> getRoleResources(Long roleId, Boolean disableCache) {
        return roleResourceRelationService.getRoleResources(roleId, disableCache);
    }

    @Override
    public UmsRole getRole(String roleName) {
        UmsRole role = new UmsRole();
        role.setName(roleName);
        QueryWrapper<UmsRole> roleQueryWrapper = new QueryWrapper<>(role);
        List<UmsRole> retRoles = roleDao.selectList(roleQueryWrapper);

        if (retRoles.size() == 0) {
            Asserts.fail("没有该角色");
        }
        return retRoles.get(0);
    }

    @Override
    public UmsRole getRole(Long roleId) {
        return roleDao.selectById(roleId);
    }

    @Override
    public List<UmsRole> getAllRoles() {
        return roleDao.selectList(new QueryWrapper<>());
    }


    @Override
    public List<UmsRole> list(PageInfo pageInfo, UmsRole role) {
        PageHelper.startPage(pageInfo.getPageNum(), pageInfo.getPageSize());
        return roleDao.selectList(new QueryWrapper<>(role));
    }

    @Override
    public void run(ApplicationArguments args) {
        //初始化 将所有角色对应资源加载进缓存中
        refreshCache();
    }
}
