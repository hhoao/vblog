package com.hhoa.vblog.portal.bean;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * The type Ums role param.
 *
 * @author hhoa
 * @since 2022 /5/16
 */
@Data
@Schema(description = "角色参数")
public class UmsRoleParam {
    @Schema(description = "名称")
    private String name;

    @Schema(description = "描述")
    private String description;

    @Schema(description = "启用状态：0->禁用；1->启用", allowableValues = {"0", "1"})
    private Integer status;
}
