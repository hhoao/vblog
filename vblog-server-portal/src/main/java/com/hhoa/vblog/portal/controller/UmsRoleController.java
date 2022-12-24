package com.hhoa.vblog.portal.controller;


import com.hhoa.vblog.common.api.CommonPage;
import com.hhoa.vblog.common.api.CommonResult;
import com.hhoa.vblog.mgb.model.UmsResource;
import com.hhoa.vblog.portal.bean.PageInfo;
import com.hhoa.vblog.portal.service.UmsRoleResourceRelationService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * 角色管理.
 *
 * @author hhoa
 * @since  2022/5/16
 **/
@RestController
@Tag(description = "UmsRoleController", name = "角色管理")
@RequiredArgsConstructor
public class UmsRoleController {
    private final UmsRoleResourceRelationService resourceRoleRelationService;

    @Operation(summary = "分页获取角色所有资源")
    @GetMapping("/roles/{roleName}/resources")
    public CommonResult<CommonPage<UmsResource>> listRoleResources(
            @Parameter(description = "角色名字") @PathVariable("roleName") String roleName,
            PageInfo pageInfo) {
        List<UmsResource> resources =
                resourceRoleRelationService.listRoleResources(roleName, pageInfo);
        return CommonResult.success(CommonPage.restPage(resources));
    }
}
