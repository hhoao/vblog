package com.hhoa.blog.admin.service;


import com.hhoa.blog.mgb.model.UmsMenu;
import com.hhoa.blog.admin.bean.PageInfo;
import com.hhoa.blog.admin.bean.UmsMenuParam;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * The interface Ums menu service.
 *
 * @author hhoa
 * @date 2022 /6/13
 */
public interface UmsMenuService {
    /**
     * Gets menu.
     *
     * @param menuName the menu name
     * @return the menu
     */
    UmsMenu getMenu(String menuName);

    /**
     * Gets menu.
     *
     * @param menuId the menu id
     * @return the menu
     */
    UmsMenu getMenu(Long menuId);
    /**
     * 获取所有资源
     *
     * @return 所有资源 all menus
     */
    List<UmsMenu> getAllMenus();

    /**
     * 分页获取资源列表
     *
     * @param pageInfo   @return 分页资源 list
     * @param menuParams
     */
    List<UmsMenu> list(PageInfo pageInfo, UmsMenu menuParams);
    /**
     * 添加资源
     *
     * @param menuParam 资源参数
     */
    @Transactional
    void addMenu(UmsMenuParam menuParam);

    /**
     * 修改资源
     *
     * @param menuName  the menu name
     * @param menuParam 资源参数
     */
    @Transactional
    void updateMenu(String menuName, UmsMenuParam menuParam);

    /**
     * 删除资源
     *
     * @param menuId 资源id
     */
    @Transactional
    void deleteMenu(Long menuId);

    /**
     * Delete menu.
     *
     * @param menuName the menu name
     */
    @Transactional
    void deleteMenu(String menuName);
}
