package com.hhoa.vblog.mgb.model;

import io.swagger.v3.oas.annotations.media.Schema;
import java.io.Serializable;

/**
 * 标签
 * @author hhoa
 * @date 2022-12-12
 */
@Schema(description = "标签")
public class AmsTag implements Serializable {
    private Long id;

    @Schema(description = "标签")
    private String label;

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
     * 标签
     * @return label 标签
     */
    public String getLabel() {
        return label;
    }

    /**
     * 标签
     * @param label 标签
     */
    public void setLabel(String label) {
        this.label = label;
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
        sb.append(", label=").append(label);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}
