package com.hhoa.vblog.admin.bean;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * The type File param.
 *
 * @author hhoa
 * @since 2022 /7/13
 */
@Schema(description = "FileParam", name = "FileParam")
@Data
public class FileParam {
    private String name;
}
