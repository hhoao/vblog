package com.hhoa.vblog.admin.service.impl;

import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.github.pagehelper.PageHelper;
import com.hhoa.vblog.admin.bean.PageInfo;
import com.hhoa.vblog.admin.bean.UmsRoleParam;
import com.hhoa.vblog.admin.dao.UmsRoleDao;
import com.hhoa.vblog.admin.service.UmsAccountService;
import com.hhoa.vblog.admin.service.UmsRoleResourceCacheService;
import com.hhoa.vblog.admin.service.UmsRoleResourceRelationService;
import com.hhoa.vblog.admin.service.UmsRoleService;
import com.hhoa.vblog.common.exception.Asserts;
import com.hhoa.vblog.mgb.model.UmsAccount;
import com.hhoa.vblog.mgb.model.UmsResource;
import com.hhoa.vblog.mgb.model.UmsRole;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
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
    private UmsAccountService accountService;

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

    /**
     * Sets account service.
     *
     * @param accountService the account service
     */
    @Autowired
    @Lazy
    public void setAccountService(UmsAccountService accountService) {
        this.accountService = accountService;
    }

    @Override
    public void refreshCache(Long roleId) {
        UmsRole role = getRole(roleId);
        List<UmsResource> roleResources = getRoleResources(role.getId(), false);
        resourceCacheService.setByRoleName(roleResources, role.getName());
    }

    @Override
    public void refreshCache(UmsRole role) {
        List<UmsResource> roleResources = getRoleResources(role.getId(), false);
        resourceCacheService.setByRoleName(roleResources, role.getName());
    }

    @Override
    public void refreshCache(String roleName) {
        UmsRole role = getRole(roleName);
        List<UmsResource> roleResources = getRoleResources(role.getId(), false);
        resourceCacheService.setByRoleName(roleResources, role.getName());
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
    public void updateRole(String roleName, UmsRoleParam roleParam) {
        UmsRole role = getRole(roleName);
        UmsRole newRole = new UmsRole();
        BeanUtils.copyProperties(roleParam, newRole);
        newRole.setId(role.getId());
        int i = roleDao.updateById(newRole);
        if (i == 0) {
            Asserts.fail("修改角色失败");
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
    public void addRole(UmsRoleParam roleParam) {
        UmsRole role = new UmsRole();
        BeanUtil.copyProperties(roleParam, role);
        int insert = roleDao.insert(role);
        if (insert == 0) {
            Asserts.fail("插入角色失败");
        }
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

    /**
     * 删除引用.
     *
     * @param roleId 角色id
     */
    private void deleteReference(Long roleId) {
        UmsAccount account = new UmsAccount();
        account.setRoleId(roleId);
        accountService.deleteAccounts(account);
    }

    @Override
    public void deleteRole(String roleName) {
        UmsRole role = getRole(roleName);
        roleResourceRelationService.deleteRoleResources(role.getId());
        deleteReference(role.getId());
        int i = roleDao.deleteById(role.getId());
        if (i == 0) {
            Asserts.fail("删除角色失败");
        }
    }

    @Override
    public void deleteRoleMenu(String roleName, Long menuId) {
        UmsRole role = getRole(roleName);
        refreshCache(role.getId());
    }

    @Override
    public void addRoleMenu(String roleName, Long menuId) {
        UmsRole role = getRole(roleName);
        refreshCache(role.getId());
    }

    @Override
    public void allocResources(String roleName, List<Long> resourceIds) {
        UmsRole role = getRole(roleName);
        roleResourceRelationService.deleteRoleResources(role.getId());
        for (Long resourceId : resourceIds) {
            roleResourceRelationService.addRoleResource(role.getId(), resourceId);
        }
    }

    @Override
    public void run(ApplicationArguments args) {
        //初始化 将所有角色对应资源加载进缓存中
        refreshCache();
    }
}
