package com.hhoa.blog.admin.service;


import com.hhoa.blog.mgb.model.UmsMenu;
import com.hhoa.blog.mgb.model.UmsResource;
import com.hhoa.blog.mgb.model.UmsRole;
import com.hhoa.blog.admin.bean.PageInfo;
import com.hhoa.blog.admin.bean.UmsRoleParam;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * The interface Ums role service.
 *
 * @author hhoa
 * @date 2022 /5/15
 */
public interface UmsRoleService {
    /**
     * Refresh cache by role id.
     *
     * @param roleId the role id
     */
    void refreshCache(Long roleId);

    /**
     * Refresh cache.
     *
     * @param role the role
     */
    void refreshCache(UmsRole role);

    /**
     * Refresh cache.
     *
     * @param roleName the role name
     */
    void refreshCache(String roleName);

    /**
     * Refresh cache.
     */
    void refreshCache();

    /**
     * 更新角色
     *
     * @param roleName  the role name
     * @param roleParam 角色参数
     */
    @Transactional
    void updateRole(String roleName, UmsRoleParam roleParam);

    List<UmsResource> getRoleResources(Long roleId);

    /**
     * Gets role resources.
     *
     * @param roleId the role id
     * @return the role resources
     */
    List<UmsResource> getRoleResources(Long roleId, Boolean disableCache);

    /**
     * Gets role.
     *
     * @param roleName the role name
     * @return the role
     */
    UmsRole getRole(String roleName);

    /**
     * 通过roleId获取角色
     *
     * @param roleId roleId
     * @return 角色 role by role id
     */
    UmsRole getRole(Long roleId);

    /**
     * 增加角色
     *
     * @param roleParam 角色参数
     */
    @Transactional
    void addRole(UmsRoleParam roleParam);

    /**
     * 获取所有角色
     *
     * @return role all roles
     */
    List<UmsRole> getAllRoles();

    /**
     * 分页获取角色列表
     *
     * @param pageInfo @return 分页角色 list
     * @param role     the role
     * @return the list
     */
    List<UmsRole> list(PageInfo pageInfo, UmsRole role);

    /**
     * 删除角色
     *
     * @param roleName 角色id
     */
    @Transactional
    void deleteRole(String roleName);

    /**
     * Gets menus.
     *
     * @param roleId the role id
     * @return the menus
     */
    List<UmsMenu> getMenus(Long roleId);

    /**
     * Delete role menu.
     *
     * @param roleName the role name
     * @param menuId   the menu id
     */
    @Transactional
    void deleteRoleMenu(String roleName, Long menuId);

    /**
     * Add role menu.
     *
     * @param roleName the role name
     * @param menuId   the menu id
     */
    @Transactional
    void addRoleMenu(String roleName, Long menuId);

    /**
     * List menus list.
     *
     * @param pageInfo the page info
     * @param roleName the role name
     * @return the list
     */
    List<UmsMenu> listMenus(PageInfo pageInfo, String roleName);

    /**
     * Alloc menus.
     *
     * @param roleName the role name
     * @param menuId   the menu id
     */
    void allocMenus(String roleName, List<Long> menuId);

    /**
     * Alloc resources.
     *
     * @param roleName    the role name
     * @param resourceIds the resource ids
     */
    void allocResources(String roleName, List<Long> resourceIds);
}
