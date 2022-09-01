package com.hhoa.blog.admin.service;

import com.hhoa.blog.admin.bean.PageInfo;
import com.hhoa.blog.mgb.model.UmsMenu;
import com.hhoa.blog.mgb.model.UmsRole;

import java.util.List;

/**
 * The interface Ums role menu relation service.
 *
 * @author hhoa
 * @date 2022 /6/13
 */
public interface UmsRoleMenuRelationService {
    /**
     * Gets menus.
     *
     * @param roleId the role id
     * @return the menus
     */
    List<UmsMenu> getMenus(Long roleId);

    /**
     * Delete role menus.
     *
     * @param roleId the id
     */
    void deleteRoleMenus(Long roleId);

    /**
     * Delete role menu.
     *
     * @param roleId the id
     * @param menuId the menu id
     */
    void deleteRoleMenu(Long roleId, Long menuId);

    /**
     * Gets roles.
     *
     * @param menuId the menu id
     * @return the roles
     */
    List<UmsRole> getRoles(Long menuId);

    /**
     * Gets roles.
     *
     * @param menuName the menu name
     * @return the roles
     */
    List<UmsRole> getRoles(String menuName);

    /**
     * Gets role menus.
     *
     * @param roleId the role id
     * @return the role menus
     */
    List<UmsMenu> getRoleMenus(Long roleId);

    /**
     * Add role menu.
     *
     * @param roleId the id
     * @param menuId the menu id
     */
    void addRoleMenu(Long roleId, Long menuId);

    /**
     * List role menus list.
     *
     * @param name     the name
     * @param pageInfo the page info
     * @return the list
     */
    List<UmsMenu> listRoleMenus(String name, PageInfo pageInfo);

    /**
     * Delete role menu.
     *
     * @param menuId the id
     */
    void deleteRoleMenu(Long menuId);

    /**
     * Delete role menu.
     *
     * @param roleName the role name
     * @param menuName the menu name
     */
    void deleteRoleMenu(String roleName, String menuName);

    /**
     * Add role menu.
     *
     * @param roleName the role name
     * @param menuName the menu name
     */
    void addRoleMenu(String roleName, String menuName);

    /**
     * Delete exists menu.
     *
     * @param roleName the role name
     */
    void deleteExistsMenu(String roleName);
}
