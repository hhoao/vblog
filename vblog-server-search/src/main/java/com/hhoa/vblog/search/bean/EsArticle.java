package com.hhoa.vblog.search.bean;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

import javax.validation.constraints.Size;
import java.io.Serializable;

/**
 * EsArticle.
 *
 * @author hhoa
 * @since 2022-07-15
 */
@Data
@Schema(description = "esArticle")
@Document(indexName = "articles", createIndex = false)
public class EsArticle implements Serializable {
    @Id
    private Long id;

    @Field(type = FieldType.Keyword)
    @Schema(description = "title")
    private String title;

    @Schema(description = "摘要")
    @Size(min = 10)
    private String digest;
//
//    @Schema(description = "内容")
//    @Size(min = 20)
//    private String content;
}
