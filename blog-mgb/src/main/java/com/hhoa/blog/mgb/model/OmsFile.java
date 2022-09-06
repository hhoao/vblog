package com.hhoa.blog.mgb.model;

import io.swagger.v3.oas.annotations.media.Schema;
import java.io.Serializable;

/**
 * 文件
 * @author hhoa 
 * @date 2022-09-06
 */
@Schema(description = "文件")
public class OmsFile implements Serializable {
    @Schema(description = "文件id")
    private Long id;

    @Schema(description = "文件名称")
    private String name;

    @Schema(description = "文件类型")
    private String type;

    @Schema(description = "文件大小（KB）")
    private Double size;

    @Schema(description = "下载链接")
    private String url;

    @Schema(description = "文件uuid")
    private String uuid;

    @Schema(description = "链接是否可用（1：是 0：否）")
    private Boolean enable;

    private static final long serialVersionUID = 1L;

    /**
     * 文件id
     * @return id 文件id
     */
    public Long getId() {
        return id;
    }

    /**
     * 文件id
     * @param id 文件id
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * 文件名称
     * @return name 文件名称
     */
    public String getName() {
        return name;
    }

    /**
     * 文件名称
     * @param name 文件名称
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * 文件类型
     * @return type 文件类型
     */
    public String getType() {
        return type;
    }

    /**
     * 文件类型
     * @param type 文件类型
     */
    public void setType(String type) {
        this.type = type;
    }

    /**
     * 文件大小（KB）
     * @return size 文件大小（KB）
     */
    public Double getSize() {
        return size;
    }

    /**
     * 文件大小（KB）
     * @param size 文件大小（KB）
     */
    public void setSize(Double size) {
        this.size = size;
    }

    /**
     * 下载链接
     * @return url 下载链接
     */
    public String getUrl() {
        return url;
    }

    /**
     * 下载链接
     * @param url 下载链接
     */
    public void setUrl(String url) {
        this.url = url;
    }

    /**
     * 文件uuid
     * @return uuid 文件uuid
     */
    public String getUuid() {
        return uuid;
    }

    /**
     * 文件uuid
     * @param uuid 文件uuid
     */
    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    /**
     * 链接是否可用（1：是 0：否）
     * @return enable 链接是否可用（1：是 0：否）
     */
    public Boolean getEnable() {
        return enable;
    }

    /**
     * 链接是否可用（1：是 0：否）
     * @param enable 链接是否可用（1：是 0：否）
     */
    public void setEnable(Boolean enable) {
        this.enable = enable;
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
        sb.append(", type=").append(type);
        sb.append(", size=").append(size);
        sb.append(", url=").append(url);
        sb.append(", uuid=").append(uuid);
        sb.append(", enable=").append(enable);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}