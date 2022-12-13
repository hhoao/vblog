package com.hhoa.blog.mgb.model;

import io.swagger.v3.oas.annotations.media.Schema;
import java.io.Serializable;
import java.util.Date;

/**
 * 文章和目录关系
 * @author hhoa 
 * @date 2022-12-12
 */
@Schema(description = "文章和目录关系")
public class AmsComment implements Serializable {
    private Long id;

    private Long articleId;

    @Schema(description = "创建时间")
    private Date createTime;

    @Schema(description = "电子邮箱")
    private String email;

    @Schema(description = "昵称")
    private String nickname;

    @Schema(description = "引用")
    private String reference;

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
     * @return article_id 
     */
    public Long getArticleId() {
        return articleId;
    }

    /**
     * 
     * @param articleId 
     */
    public void setArticleId(Long articleId) {
        this.articleId = articleId;
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
     * 电子邮箱
     * @return email 电子邮箱
     */
    public String getEmail() {
        return email;
    }

    /**
     * 电子邮箱
     * @param email 电子邮箱
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * 昵称
     * @return nickname 昵称
     */
    public String getNickname() {
        return nickname;
    }

    /**
     * 昵称
     * @param nickname 昵称
     */
    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    /**
     * 引用
     * @return reference 引用
     */
    public String getReference() {
        return reference;
    }

    /**
     * 引用
     * @param reference 引用
     */
    public void setReference(String reference) {
        this.reference = reference;
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
        sb.append(", articleId=").append(articleId);
        sb.append(", createTime=").append(createTime);
        sb.append(", email=").append(email);
        sb.append(", nickname=").append(nickname);
        sb.append(", reference=").append(reference);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}