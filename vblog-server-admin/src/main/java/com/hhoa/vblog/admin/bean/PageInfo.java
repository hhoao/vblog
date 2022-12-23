package com.hhoa.vblog.admin.bean;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

/**
 * The type Page info.
 *
 * @author hhoa
 */
@Data
@AllArgsConstructor
@Schema(description = "单页信息")
@Builder
public class PageInfo {
    @Schema(description = "页码", defaultValue = "1")
    private Integer  pageNum;

    @Schema(description = "页面大小", defaultValue = "5")
    private Integer pageSize;

    public PageInfo() {
    }
}
