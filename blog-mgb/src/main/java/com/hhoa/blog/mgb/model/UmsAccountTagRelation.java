package com.hhoa.blog.mgb.model;

import io.swagger.v3.oas.annotations.media.Schema;
import java.io.Serializable;

/**
 * 账户标签关系
 * @author hhoa 
 * @date 2022-09-06
 */
@Schema(description = "账户标签关系")
public class UmsAccountTagRelation implements Serializable {
    private Long id;

    private Long tagId;

    private Long accountId;

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
     * 
     * @return tag_id 
     */
    public Long getTagId() {
        return tagId;
    }

    /**
     * 
     * @param tagId 
     */
    public void setTagId(Long tagId) {
        this.tagId = tagId;
    }

    /**
     * 
     * @return account_id 
     */
    public Long getAccountId() {
        return accountId;
    }

    /**
     * 
     * @param accountId 
     */
    public void setAccountId(Long accountId) {
        this.accountId = accountId;
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
        sb.append(", tagId=").append(tagId);
        sb.append(", accountId=").append(accountId);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}