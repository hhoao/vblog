package com.hhoa.blog.admin.service.impl;

import com.github.pagehelper.PageHelper;

import com.hhoa.blog.admin.bean.PageInfo;
import com.hhoa.blog.admin.service.UmsMenuService;
import com.hhoa.blog.admin.service.UmsRoleMenuRelationService;
import com.hhoa.blog.admin.service.UmsRoleService;
import com.hhoa.blog.mgb.mapper.UmsRoleMenuRelationMapper;
import com.hhoa.blog.mgb.model.UmsMenu;
import com.hhoa.blog.mgb.model.UmsRole;
import com.hhoa.blog.mgb.model.UmsRoleMenuRelation;
import com.hhoa.blog.mgb.model.UmsRoleMenuRelationExample;
import com.hhoa.blog.common.exception.Asserts;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @author hhoa
 * @date 2022/6/13
 **/

@Service
@RequiredArgsConstructor
public class UmsRoleMenuRelationServiceImpl implements UmsRoleMenuRelationService {
    private final UmsRoleMenuRelationMapper roleMenuRelationMapper;
    private UmsMenuService menuService;
    private UmsRoleService roleService;

    @Autowired
    @Lazy
    public void setRoleService(UmsRoleService roleService) {
        this.roleService = roleService;
    }

    @Lazy
    @Autowired
    public void setMenuService(UmsMenuService menuService) {
        this.menuService = menuService;
    }

    @Override
    public List<UmsMenu> getMenus(Long roleId) {
        UmsRoleMenuRelationExample menuRelationExample = new UmsRoleMenuRelationExample();
        menuRelationExample.createCriteria().andRoleIdEqualTo(roleId);
        List<UmsRoleMenuRelation> retRoleMenuRelations = roleMenuRelationMapper.selectByExample(menuRelationExample);
        List<UmsMenu> menus = new ArrayList<>();
        for (UmsRoleMenuRelation roleMenuRelation : retRoleMenuRelations) {
            menus.add(menuService.getMenu(roleMenuRelation.getMenuId()));
        }
        return menus;
    }

    @Override
    public void deleteRoleMenus(Long roleId) {
        UmsRoleMenuRelationExample roleMenuRelationExample = new UmsRoleMenuRelationExample();
        roleMenuRelationExample.createCriteria().andRoleIdEqualTo(roleId);
        roleMenuRelationMapper.deleteByExample(roleMenuRelationExample);
    }

    @Override
    public void deleteRoleMenu(Long roleId, Long menuId) {
        UmsRoleMenuRelationExample roleMenuRelationExample = new UmsRoleMenuRelationExample();
        roleMenuRelationExample.createCriteria().
                andRoleIdEqualTo(roleId).
                andMenuIdEqualTo(menuId);
        int i = roleMenuRelationMapper.deleteByExample(roleMenuRelationExample);
        if (i == 0) {
            Asserts.fail("该角色没有该菜单");
        }
    }

    @Override
    public void addRoleMenu(Long roleId, Long menuId) {
        UmsRoleMenuRelation roleMenuRelation = new UmsRoleMenuRelation();
        roleMenuRelation.setRoleId(roleId);
        roleMenuRelation.setMenuId(menuId);
        roleMenuRelationMapper.insert(roleMenuRelation);
    }

    @Override
    public List<UmsRole> getRoles(Long menuId) {
        UmsRoleMenuRelationExample roleRelationExample = new UmsRoleMenuRelationExample();
        roleRelationExample.createCriteria().andMenuIdEqualTo(menuId);
        List<UmsRoleMenuRelation> roleMenuRelations = roleMenuRelationMapper.selectByExample(roleRelationExample);
        List<UmsRole> roles = new ArrayList<>();
        for (UmsRoleMenuRelation menuRoleRelation : roleMenuRelations) {
            Long roleId = menuRoleRelation.getRoleId();
            UmsRole role = roleService.getRole(roleId);
            roles.add(role);
        }
        return roles;
    }

    @Override
    public List<UmsRole> getRoles(String menuName) {
        UmsMenu menu = menuService.getMenu(menuName);
        return getRoles(menu.getId());
    }


    @Override
    public List<UmsMenu> getRoleMenus(Long roleId) {
        List<UmsMenu> byRoleName = new ArrayList<>();
        UmsRoleMenuRelationExample roleMenuRelationExample = new UmsRoleMenuRelationExample();
        roleMenuRelationExample.createCriteria().andRoleIdEqualTo(roleId);
        List<UmsRoleMenuRelation> retRoleMenuRelations = roleMenuRelationMapper.selectByExample(roleMenuRelationExample);
        for (UmsRoleMenuRelation roleMenuRelation : retRoleMenuRelations) {
            byRoleName.add(menuService.getMenu(roleMenuRelation.getMenuId()));
        }
        return byRoleName;
    }

    @Override
    public List<UmsMenu> listRoleMenus(String name, PageInfo pageInfo) {
        PageHelper.startPage(pageInfo.getPageNum(), pageInfo.getPageSize());
        UmsRole roleByRoleName = roleService.getRole(name);
        if (roleByRoleName == null) {
            Asserts.fail("没有该角色");
        }
        return getRoleMenus(roleByRoleName.getId());
    }

    @Override
    public void deleteRoleMenu(Long menuId) {
        UmsRoleMenuRelationExample relationExample = new UmsRoleMenuRelationExample();
        relationExample.createCriteria().andMenuIdEqualTo(menuId);
        List<UmsRoleMenuRelation> retRoleMenuRelations = roleMenuRelationMapper.selectByExample(relationExample);
        int deleteRelationCount = roleMenuRelationMapper.deleteByExample(relationExample);
        if (deleteRelationCount != 0) {
            //刷新缓存
            for (UmsRoleMenuRelation roleMenuRelation : retRoleMenuRelations) {
                roleService.refreshCache(roleMenuRelation.getRoleId());
            }
        }
    }

    @Override
    public void deleteRoleMenu(String roleName, String menuName) {
        UmsRole role = roleService.getRole(roleName);
        UmsMenu menu = menuService.getMenu(menuName);
        deleteRoleMenu(role.getId(), menu.getId());
    }

    @Override
    public void addRoleMenu(String roleName, String menuName) {
        UmsMenu menu = menuService.getMenu(menuName);
        UmsRole role = roleService.getRole(roleName);
        addRoleMenu(role.getId(), menu.getId());
    }

    @Override
    public void deleteExistsMenu(String roleName) {
        UmsRole role = roleService.getRole(roleName);
        UmsRoleMenuRelationExample roleMenuRelationExample = new UmsRoleMenuRelationExample();
        roleMenuRelationExample.createCriteria().andRoleIdEqualTo(role.getId());
        int i = roleMenuRelationMapper.deleteByExample(roleMenuRelationExample);
        if (i != 0) {
            //刷新缓存
            roleService.refreshCache(role.getId());
        }
    }
}
