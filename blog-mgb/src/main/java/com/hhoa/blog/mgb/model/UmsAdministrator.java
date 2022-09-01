package com.hhoa.blog.mgb.model;

import io.swagger.v3.oas.annotations.media.Schema;
import java.io.Serializable;
import java.util.Date;

/**
 * 管理员
 * @author hhoa 
 * @date 2022-09-01
 */
@Schema(description = "管理员")
public class UmsAdministrator implements Serializable {
    private Long id;

    @Schema(description = "密码")
    private String password;

    @Schema(description = "用户名")
    private String username;

    @Schema(description = "账号启用状态: 0->禁言， 1->启用")
    private Integer status;

    @Schema(description = "创建时间")
    private Date createTime;

    @Schema(description = "最后登录时间")
    private Date latestTime;

    @Schema(description = "头像")
    private String icon;

    @Schema(description = "角色id")
    private Long roleId;

    private static final long serialVersionUID = 1L;

    /**
     * 
     * @return id 
     */
    public Long getId() {
        return id;
    }

    /**
     * 
     * @param id 
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * 密码
     * @return password 密码
     */
    public String getPassword() {
        return password;
    }

    /**
     * 密码
     * @param password 密码
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * 用户名
     * @return username 用户名
     */
    public String getUsername() {
        return username;
    }

    /**
     * 用户名
     * @param username 用户名
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * 账号启用状态: 0->禁言， 1->启用
     * @return status 账号启用状态: 0->禁言， 1->启用
     */
    public Integer getStatus() {
        return status;
    }

    /**
     * 账号启用状态: 0->禁言， 1->启用
     * @param status 账号启用状态: 0->禁言， 1->启用
     */
    public void setStatus(Integer status) {
        this.status = status;
    }

    /**
     * 创建时间
     * @return create_time 创建时间
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     * 创建时间
     * @param createTime 创建时间
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    /**
     * 最后登录时间
     * @return latest_time 最后登录时间
     */
    public Date getLatestTime() {
        return latestTime;
    }

    /**
     * 最后登录时间
     * @param latestTime 最后登录时间
     */
    public void setLatestTime(Date latestTime) {
        this.latestTime = latestTime;
    }

    /**
     * 头像
     * @return icon 头像
     */
    public String getIcon() {
        return icon;
    }

    /**
     * 头像
     * @param icon 头像
     */
    public void setIcon(String icon) {
        this.icon = icon;
    }

    /**
     * 角色id
     * @return role_id 角色id
     */
    public Long getRoleId() {
        return roleId;
    }

    /**
     * 角色id
     * @param roleId 角色id
     */
    public void setRoleId(Long roleId) {
        this.roleId = roleId;
    }

    /**
     * toString
     * @return java.lang.String
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", password=").append(password);
        sb.append(", username=").append(username);
        sb.append(", status=").append(status);
        sb.append(", createTime=").append(createTime);
        sb.append(", latestTime=").append(latestTime);
        sb.append(", icon=").append(icon);
        sb.append(", roleId=").append(roleId);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}