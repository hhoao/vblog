package com.hhoa.blog.admin.service;


import com.hhoa.blog.admin.bean.UmsAdministratorDetails;

/**
 * @author hhoa
 * @date 2022/5/13
 **/
public interface UmsAdministratorCacheService {
    /**
     * 延长时间
     *
     * @param username 用户名
     */
    void expire(String username);
    /**
     * 延长时间
     *
     * @param username   用户名
     * @param expiration 时间
     */
    void expire(String username, Long expiration);
    /**
     * 通过角色名和资源设置token
     *
     * @param username    用户名
     * @param administratorDetails 角色名
     */
    void setKey(String username, UmsAdministratorDetails administratorDetails);

    /**
     * 判断是否有该key
     *
     * @param username username
     * @return 是否存在
     */
    boolean hasKey(String username);

    /**
     * 获取
     *
     * @param username 用户名
     * @return 角色
     */
    UmsAdministratorDetails getKey(String username);

    /**
     * 删除key
     *
     * @param username 用户名
     */
    void delKey(String username);

}
