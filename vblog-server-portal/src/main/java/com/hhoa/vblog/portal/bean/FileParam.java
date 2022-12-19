package com.hhoa.vblog.portal.bean;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * 文件请求参数.
 *
 * @author hhoa
 * @since  2022/7/13
 **/

@Schema(description = "FileParam", name = "FileParam")
@Data
public class FileParam {
    private String name;
}
