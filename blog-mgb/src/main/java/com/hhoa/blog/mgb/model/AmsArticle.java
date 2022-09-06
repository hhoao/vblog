package com.hhoa.blog.mgb.model;

import io.swagger.v3.oas.annotations.media.Schema;
import java.io.Serializable;

/**
 * 文
 * @author hhoa 
 * @date 2022-09-06
 */
@Schema(description = "文")
public class AmsArticle implements Serializable {
    @Schema(description = "文件id")
    private Long id;

    @Schema(description = "标题")
    private String title;

    @Schema(description = "作者")
    private String author;

    @Schema(description = "摘要")
    private String digest;

    @Schema(description = "类型(0:原创, 1:翻译, 2:转载)")
    private Integer type;

    @Schema(description = "阅读量")
    private String readingAmount;

    @Schema(description = "是否置顶")
    private Boolean top;

    @Schema(description = "作品等级")
    private Integer level;

    @Schema(description = "是否可见(0: 否, 1: 是)")
    private Boolean visible;

    @Schema(description = "封面图片")
    private String cover;

    @Schema(description = "内容")
    private String content;

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
     * 标题
     * @return title 标题
     */
    public String getTitle() {
        return title;
    }

    /**
     * 标题
     * @param title 标题
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * 作者
     * @return author 作者
     */
    public String getAuthor() {
        return author;
    }

    /**
     * 作者
     * @param author 作者
     */
    public void setAuthor(String author) {
        this.author = author;
    }

    /**
     * 摘要
     * @return digest 摘要
     */
    public String getDigest() {
        return digest;
    }

    /**
     * 摘要
     * @param digest 摘要
     */
    public void setDigest(String digest) {
        this.digest = digest;
    }

    /**
     * 类型(0:原创, 1:翻译, 2:转载)
     * @return type 类型(0:原创, 1:翻译, 2:转载)
     */
    public Integer getType() {
        return type;
    }

    /**
     * 类型(0:原创, 1:翻译, 2:转载)
     * @param type 类型(0:原创, 1:翻译, 2:转载)
     */
    public void setType(Integer type) {
        this.type = type;
    }

    /**
     * 阅读量
     * @return reading_amount 阅读量
     */
    public String getReadingAmount() {
        return readingAmount;
    }

    /**
     * 阅读量
     * @param readingAmount 阅读量
     */
    public void setReadingAmount(String readingAmount) {
        this.readingAmount = readingAmount;
    }

    /**
     * 是否置顶
     * @return top 是否置顶
     */
    public Boolean getTop() {
        return top;
    }

    /**
     * 是否置顶
     * @param top 是否置顶
     */
    public void setTop(Boolean top) {
        this.top = top;
    }

    /**
     * 作品等级
     * @return level 作品等级
     */
    public Integer getLevel() {
        return level;
    }

    /**
     * 作品等级
     * @param level 作品等级
     */
    public void setLevel(Integer level) {
        this.level = level;
    }

    /**
     * 是否可见(0: 否, 1: 是)
     * @return visible 是否可见(0: 否, 1: 是)
     */
    public Boolean getVisible() {
        return visible;
    }

    /**
     * 是否可见(0: 否, 1: 是)
     * @param visible 是否可见(0: 否, 1: 是)
     */
    public void setVisible(Boolean visible) {
        this.visible = visible;
    }

    /**
     * 封面图片
     * @return cover 封面图片
     */
    public String getCover() {
        return cover;
    }

    /**
     * 封面图片
     * @param cover 封面图片
     */
    public void setCover(String cover) {
        this.cover = cover;
    }

    /**
     * 内容
     * @return content 内容
     */
    public String getContent() {
        return content;
    }

    /**
     * 内容
     * @param content 内容
     */
    public void setContent(String content) {
        this.content = content;
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
        sb.append(", title=").append(title);
        sb.append(", author=").append(author);
        sb.append(", digest=").append(digest);
        sb.append(", type=").append(type);
        sb.append(", readingAmount=").append(readingAmount);
        sb.append(", top=").append(top);
        sb.append(", level=").append(level);
        sb.append(", visible=").append(visible);
        sb.append(", cover=").append(cover);
        sb.append(", content=").append(content);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}