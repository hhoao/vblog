package com.hhoa.blog.admin.bean;

import io.swagger.v3.oas.annotations.Hidden;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NonNull;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

/**
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
