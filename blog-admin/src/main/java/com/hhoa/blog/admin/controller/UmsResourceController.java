package com.hhoa.blog.admin.controller;

import com.hhoa.blog.admin.bean.PageInfo;
import com.hhoa.blog.admin.bean.UmsResourceParam;
import com.hhoa.blog.admin.service.UmsResourceService;
import com.hhoa.blog.mgb.model.UmsResource;
import com.hhoa.blog.common.api.CommonPage;
import com.hhoa.blog.common.api.CommonResult;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 资源管理
 * @author hhoa
 * @date 2022/5/16
 **/
@RestController
@AllArgsConstructor
@Tag(description = "UmsResourceController", name = "资源管理")
public class UmsResourceController {
    private final UmsResourceService resourceService;

    @Operation(description = "分页获取资源列表", summary = "分页获取资源列表")
    @GetMapping("/resources")
    public CommonResult<CommonPage<UmsResource>> list(PageInfo pageInfo,
                                                      UmsResource resource){
        List<UmsResource> allResources = resourceService.list(pageInfo, resource);
        return CommonResult.success(CommonPage.restPage(allResources));
    }
    @Operation(description = "添加资源", summary = "添加资源")
    @PostMapping("/resources")
    public CommonResult<String> addResource(@RequestBody UmsResourceParam resourceParam){
        resourceService.addResource(resourceParam);
        return CommonResult.success(null);
    }
    @Operation(summary = "修改资源")
    @PatchMapping("/resources/{resourceName}")
    public CommonResult<String> updateResource(@PathVariable("resourceName") String resourceName,
                                               @RequestBody UmsResourceParam resourceParam){
        resourceService.updateResource(resourceName, resourceParam);
        return CommonResult.success(null);
    }
    @Operation(summary = "删除资源")
    @DeleteMapping("/resources/{resourceName}")
    public CommonResult<String> delResource(@PathVariable("resourceName") String resourceName){
        resourceService.deleteResource(resourceName);
        return CommonResult.success(null);
    }
}
