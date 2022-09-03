package com.hhoa.blog.admin.service.impl;

import cn.hutool.core.bean.BeanUtil;
import com.github.pagehelper.PageHelper;
import com.hhoa.blog.admin.bean.PageInfo;
import com.hhoa.blog.admin.bean.UmsMenuParam;
import com.hhoa.blog.admin.service.UmsMenuService;
import com.hhoa.blog.admin.service.UmsRoleMenuRelationService;
import com.hhoa.blog.common.exception.Asserts;
import com.hhoa.blog.mgb.mapper.UmsMenuMapper;
import com.hhoa.blog.mgb.model.UmsMenu;
import com.hhoa.blog.mgb.model.UmsMenuExample;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author hhoa
 * @date 2022/6/13
 **/

@Service
@RequiredArgsConstructor
public class UmsMenuServiceImpl implements UmsMenuService {
    private final UmsMenuMapper menuMapper;

    private UmsRoleMenuRelationService roleMenuRelationService;

    @Lazy
    @Autowired
    public void setRoleMenuRelationService(UmsRoleMenuRelationService roleMenuRelationService) {
        this.roleMenuRelationService = roleMenuRelationService;
    }


    @Override
    public UmsMenu getMenu(String menuName) {
        UmsMenuExample menuExample = new UmsMenuExample();
        menuExample.createCriteria().andNameEqualTo(menuName);
        List<UmsMenu> retMenus = menuMapper.selectByExample(menuExample);
        if (retMenus.size() == 0) {
            Asserts.fail("没有该菜单");
        }
        return retMenus.get(0);
    }

    @Override
    public UmsMenu getMenu(Long menuId) {
        UmsMenu retMenu = menuMapper.selectByPrimaryKey(menuId);
        if (retMenu == null) {
            Asserts.fail("没有该菜单");
        }
        return retMenu;
    }

    @Override
    public List<UmsMenu> getAllMenus() {
        return menuMapper.selectByExample(new UmsMenuExample());
    }

    private UmsMenuExample getMenuExample(UmsMenu menu) {
        UmsMenuExample menuExample = new UmsMenuExample();
        if (menu != null) {
            if (menu.getName() != null) {
                menuExample.createCriteria().andNameEqualTo(menu.getName());
            }
            if (menu.getId() != null) {
                menuExample.createCriteria().andIdEqualTo(menu.getId());
            }
            if (menu.getHidden() != null) {
                menuExample.createCriteria().andHiddenEqualTo(menu.getHidden());
            }
            if (menu.getTitle() != null) {
                menuExample.createCriteria().andTitleLike(menu.getTitle());
            }
        }
        return menuExample;
    }

    @Override
    public List<UmsMenu> list(PageInfo pageInfo, UmsMenu menuParams) {
        PageHelper.startPage(pageInfo.getPageNum(), pageInfo.getPageSize());
        UmsMenuExample menuExample = getMenuExample(menuParams);
        return menuMapper.selectByExample(menuExample);
    }


    @Override
    public void addMenu(UmsMenuParam menuParam) {
        UmsMenu menu = new UmsMenu();
        BeanUtil.copyProperties(menuParam, menu);
        int insert = menuMapper.insert(menu);
        if (insert == 0) {
            Asserts.fail("插入资源失败");
        }
    }

    @Override
    public void updateMenu(String menuName, UmsMenuParam menuParam) {
        UmsMenu menuByName = getMenu(menuName);

        BeanUtils.copyProperties(menuParam, menuByName);
        int successCount = menuMapper.updateByPrimaryKeySelective(menuByName);
        if (successCount == 0) {
            Asserts.fail("更新失败");
        }
    }

    @Override
    public void deleteMenu(Long menuId) {
        //删除角色菜单关系
        roleMenuRelationService.deleteRoleMenu(menuId);
        //删除菜单
        int i = menuMapper.deleteByPrimaryKey(menuId);
        if (i == 0) {
            Asserts.fail("删除资源失败");
        }
    }

    @Override
    public void deleteMenu(String menuName) {
        //删除角色菜单关系
        UmsMenu menu = getMenu(menuName);
        roleMenuRelationService.deleteRoleMenu(menu.getId());
        //删除菜单
        UmsMenuExample menuExample = new UmsMenuExample();
        menuExample.createCriteria().andNameEqualTo(menuName);
        int i = menuMapper.deleteByExample(menuExample);
        if (i == 0) {
            Asserts.fail("删除资源失败");
        }
    }
}
