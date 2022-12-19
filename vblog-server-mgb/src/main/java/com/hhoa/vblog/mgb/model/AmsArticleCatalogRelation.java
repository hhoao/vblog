package com.hhoa.vblog.mgb.model;

import io.swagger.v3.oas.annotations.media.Schema;
import java.io.Serializable;

/**
 * 文章和目录关系
 * @author hhoa
 * @date 2022-12-12
 */
@Schema(description = "文章和目录关系")
public class AmsArticleCatalogRelation implements Serializable {
    @Schema(description = "文件id")
    private Long id;

    private Long articleId;

    private Long catalogId;

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
     *
     * @return catalog_id
     */
    public Long getCatalogId() {
        return catalogId;
    }

    /**
     *
     * @param catalogId
     */
    public void setCatalogId(Long catalogId) {
        this.catalogId = catalogId;
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
        sb.append(", catalogId=").append(catalogId);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}
