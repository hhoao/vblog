package com.hhoa.blog.mgb.model;

import io.swagger.v3.oas.annotations.media.Schema;
import java.io.Serializable;

/**
 * 账户
 * @author hhoa 
 * @date 2022-09-06
 */
@Schema(description = "账户")
public class UmsAccount implements Serializable {
    private Long id;

    @Schema(description = "密码")
    private String password;

    @Schema(description = "用户名")
    private String username;

    @Schema(description = "账号启用状态: FALSE->禁言， TRUE->启用")
    private Boolean status;

    @Schema(description = "头像")
    private String icon;

    @Schema(description = "角色id")
    private Long roleId;

    @Schema(description = "头衔")
    private String title;

    @Schema(description = "介绍")
    private String introduce;

    @Schema(description = "未阅读消息数")
    private Integer unreadcount;

    @Schema(description = "消息数")
    private Integer notifycount;

    @Schema(description = "国家")
    private String country;

    @Schema(description = "地址")
    private String address;

    @Schema(description = "电话号码")
    private String phone;

    @Schema(description = "个性签名")
    private String signature;

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
     * 账号启用状态: FALSE->禁言， TRUE->启用
     * @return status 账号启用状态: FALSE->禁言， TRUE->启用
     */
    public Boolean getStatus() {
        return status;
    }

    /**
     * 账号启用状态: FALSE->禁言， TRUE->启用
     * @param status 账号启用状态: FALSE->禁言， TRUE->启用
     */
    public void setStatus(Boolean status) {
        this.status = status;
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
     * 头衔
     * @return title 头衔
     */
    public String getTitle() {
        return title;
    }

    /**
     * 头衔
     * @param title 头衔
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * 介绍
     * @return introduce 介绍
     */
    public String getIntroduce() {
        return introduce;
    }

    /**
     * 介绍
     * @param introduce 介绍
     */
    public void setIntroduce(String introduce) {
        this.introduce = introduce;
    }

    /**
     * 未阅读消息数
     * @return unreadCount 未阅读消息数
     */
    public Integer getUnreadcount() {
        return unreadcount;
    }

    /**
     * 未阅读消息数
     * @param unreadcount 未阅读消息数
     */
    public void setUnreadcount(Integer unreadcount) {
        this.unreadcount = unreadcount;
    }

    /**
     * 消息数
     * @return notifyCount 消息数
     */
    public Integer getNotifycount() {
        return notifycount;
    }

    /**
     * 消息数
     * @param notifycount 消息数
     */
    public void setNotifycount(Integer notifycount) {
        this.notifycount = notifycount;
    }

    /**
     * 国家
     * @return country 国家
     */
    public String getCountry() {
        return country;
    }

    /**
     * 国家
     * @param country 国家
     */
    public void setCountry(String country) {
        this.country = country;
    }

    /**
     * 地址
     * @return address 地址
     */
    public String getAddress() {
        return address;
    }

    /**
     * 地址
     * @param address 地址
     */
    public void setAddress(String address) {
        this.address = address;
    }

    /**
     * 电话号码
     * @return phone 电话号码
     */
    public String getPhone() {
        return phone;
    }

    /**
     * 电话号码
     * @param phone 电话号码
     */
    public void setPhone(String phone) {
        this.phone = phone;
    }

    /**
     * 个性签名
     * @return signature 个性签名
     */
    public String getSignature() {
        return signature;
    }

    /**
     * 个性签名
     * @param signature 个性签名
     */
    public void setSignature(String signature) {
        this.signature = signature;
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
        sb.append(", icon=").append(icon);
        sb.append(", roleId=").append(roleId);
        sb.append(", title=").append(title);
        sb.append(", introduce=").append(introduce);
        sb.append(", unreadcount=").append(unreadcount);
        sb.append(", notifycount=").append(notifycount);
        sb.append(", country=").append(country);
        sb.append(", address=").append(address);
        sb.append(", phone=").append(phone);
        sb.append(", signature=").append(signature);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}