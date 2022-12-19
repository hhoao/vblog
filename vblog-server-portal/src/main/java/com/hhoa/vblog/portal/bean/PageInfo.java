package com.hhoa.vblog.portal.bean;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * 页信息参数.
 *
 * @author hhoa
 **/

@Data
@AllArgsConstructor
@Schema(description = "单页信息")
public class PageInfo {
    @Schema(description = "页码", defaultValue = "1")
    private int pageNum;
    @Schema(description = "页面大小", defaultValue = "5")
    private int pageSize;
}
