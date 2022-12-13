package com.hhoa.blog.admin.bean;

import io.swagger.v3.oas.annotations.media.Schema;

import javax.validation.constraints.Size;
import java.util.Date;

/**
 * @author hhoa
 * @since 2022/12/1
 **/

@Schema(description = "文章参数")
public class AmsArticleParam {
    @Schema(description = "文件id")
    private Long id;

    @Schema(description = "标题")
    @Size(min = 2)
    private String title;

    @Schema(description = "作者")
    private String author;

    @Schema(description = "摘要")
    @Size(min = 10)
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

    @Schema(description = "最后修改时间")
    private Date lastModification;

    @Schema(description = "创建时间")
    private Date createTime;

    @Schema(description = "内容")
    @Size(min = 20)
    private String content;
}
