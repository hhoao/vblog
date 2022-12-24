package com.hhoa.vblog.portal.service;


import com.hhoa.vblog.portal.bean.UmsAccountDetails;

/**
 * The interface Ums account cache service.
 *
 * @author hhoa
 * @since 2022 /5/13
 */
public interface UmsAccountCacheService {
    /**
     * 通过角色名和资源设置token.
     *
     * @param username             用户名
     * @param administratorDetails 角色名
     */
    void setKey(String username, UmsAccountDetails administratorDetails);

    /**
     * 判断是否有该key.
     *
     * @param username username
     * @return 是否存在 boolean
     */
    boolean hasKey(String username);

    /**
     * 获取.
     *
     * @param username 用户名
     * @return 角色 key
     */
    UmsAccountDetails getKey(String username);

    /**
     * 删除key.
     *
     * @param username 用户名
     */
    void delKey(String username);

}
