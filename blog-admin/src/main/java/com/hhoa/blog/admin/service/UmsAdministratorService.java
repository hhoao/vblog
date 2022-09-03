package com.hhoa.blog.admin.service;


import com.hhoa.blog.admin.bean.PageInfo;
import com.hhoa.blog.admin.bean.UmsLoginParam;
import com.hhoa.blog.mgb.model.UmsAdministrator;
import com.hhoa.blog.mgb.model.UmsMenu;
import com.hhoa.blog.mgb.model.UmsResource;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * The interface Ums administrator service.
 *
 * @author hhoa
 */
public interface UmsAdministratorService {

    /**
     * Login string.
     *
     * @param loginParam the login param
     * @return the string
     */
    String login(UmsLoginParam loginParam);

    /**
     * Logout.
     *
     * @param authorization the authorization
     */
    void logout(String authorization);

    /**
     * 获取AdministratorDetails
     *
     * @param administratorName administratorName
     * @return administratorDetails administrator details by administrator name
     */
    UserDetails getAdministratorDetails(String administratorName);


    /**
     * 获取部分用户
     *
     * @param pageInfo      @return 部分用户 list
     * @param administrator the administrator
     * @return the list
     */
    List<UmsAdministrator> list(PageInfo pageInfo, UmsAdministrator administrator);

    /**
     * Refresh token string.
     *
     * @param authorization the authorization
     * @return the string
     */
    String refreshToken(String authorization);

    /**
     * Gets administrator by administratorName.
     *
     * @param administratorName the administratorName
     * @return the administrator by administratorName
     */
    UmsAdministrator getAdministratorByAdministratorName(String administratorName);

    /**
     * 更新用户
     *
     * @param administrator 用户参数
     */
    @Transactional
    void updateAdministrator(UmsAdministrator administrator);

    /**
     * Gets administrator.
     *
     * @param administratorId the administrator id
     * @return the administrator
     */
    UmsAdministrator getAdministrators(Long administratorId);

    /**
     * Delete administrator by administrator id.
     *
     * @param administratorId the administrator id
     */
    void deleteAdministratorByAdministratorId(Long administratorId);

    /**
     * Delete administrators.
     *
     * @param administrator the administrator
     */
    void deleteAdministrators(UmsAdministrator administrator);

    /**
     * Gets administrator.
     *
     * @param administrator the administrator
     * @return the administrator
     */
    List<UmsAdministrator> getAdministrators(UmsAdministrator administrator);

    /**
     * Gets administrator resources.
     *
     * @param administratorId the administrator id
     * @return the administrator resources
     */
    List<UmsResource> getAdministratorResources(Long administratorId);

    /**
     * Gets administrator by authorization.
     *
     * @param authorization the authorization
     * @return the administrator by authorization
     */
    UmsAdministrator getAdministratorByAuthorization(String authorization);

    /**
     * Gets menus by authorization.
     *
     * @param authorization the authorization
     * @return the menus by authorization
     */
    List<UmsMenu> getMenusByAuthorization(String authorization);

    /**
     * Add administrator.
     *
     * @param administrator the administrator
     */
    void addAdministrator(UmsAdministrator administrator);
}
