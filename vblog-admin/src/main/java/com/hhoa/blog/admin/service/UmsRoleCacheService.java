package com.hhoa.blog.admin.service;


import com.hhoa.blog.mgb.model.UmsRole;

/**
 * 角色缓存服务
 *
 * @author hhoa
 * @date 2022/5/14
 **/
public interface UmsRoleCacheService {
    /**
     * 删除key
     *
     * @param userName 用户名
     */
    void delKey(String userName);

    /**
     * 通过用户名名设置角色
     *
     * @param role     角色
     * @param userName 角色名
     */
    void setKeyByUserName(String userName, UmsRole role);

    /**
     * 通过用户名名设置角色
     *
     * @param role     角色
     * @param userName 角色名
     * @param expire   过期时间
     */
    void setKeyByUserName(String userName, UmsRole role, Long expire);

    /**
     * 通过用户名名获取角色
     *
     * @param userName 用户名
     * @return 角色
     */
    UmsRole getKeysByUserName(String userName);

}
