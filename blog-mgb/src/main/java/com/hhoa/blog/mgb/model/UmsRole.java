package com.hhoa.blog.mgb.model;

import io.swagger.v3.oas.annotations.media.Schema;
import java.io.Serializable;

/**
 * 角色
 * @author hhoa 
 * @date 2022-09-03
 */
@Schema(description = "角色")
public class UmsRole implements Serializable {
    private Long id;

    @Schema(description = "名称")
    private String name;

    @Schema(description = "启用状态：false->禁用；true->启用")
    private Boolean status;

    @Schema(description = "描述")
    private String description;

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
     * 名称
     * @return name 名称
     */
    public String getName() {
        return name;
    }

    /**
     * 名称
     * @param name 名称
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * 启用状态：false->禁用；true->启用
     * @return status 启用状态：false->禁用；true->启用
     */
    public Boolean getStatus() {
        return status;
    }

    /**
     * 启用状态：false->禁用；true->启用
     * @param status 启用状态：false->禁用；true->启用
     */
    public void setStatus(Boolean status) {
        this.status = status;
    }

    /**
     * 描述
     * @return description 描述
     */
    public String getDescription() {
        return description;
    }

    /**
     * 描述
     * @param description 描述
     */
    public void setDescription(String description) {
        this.description = description;
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
        sb.append(", name=").append(name);
        sb.append(", status=").append(status);
        sb.append(", description=").append(description);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}