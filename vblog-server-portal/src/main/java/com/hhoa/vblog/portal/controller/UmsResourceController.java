package com.hhoa.vblog.portal.controller;

import com.hhoa.vblog.common.api.CommonPage;
import com.hhoa.vblog.common.api.CommonResult;
import com.hhoa.vblog.mgb.model.UmsResource;
import com.hhoa.vblog.portal.bean.PageInfo;
import com.hhoa.vblog.portal.service.UmsResourceService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * 资源管理.
 *
 * @author hhoa
 * @since 2022/5/16
 **/
@RestController
@AllArgsConstructor
@Tag(description = "UmsResourceController", name = "资源管理")
public class UmsResourceController {
    private final UmsResourceService resourceService;

    @Operation(description = "分页获取资源列表", summary = "分页获取资源列表")
    @GetMapping("/resources")
    public CommonResult<CommonPage<UmsResource>> list(PageInfo pageInfo,
                                                      UmsResource resource) {
        List<UmsResource> allResources = resourceService.list(pageInfo, resource);
        return CommonResult.success(CommonPage.restPage(allResources));
    }
}
