package com.hhoa.blog.admin.controller;


import com.hhoa.blog.admin.bean.PageInfo;
import com.hhoa.blog.admin.service.AmsCatalogService;
import com.hhoa.blog.common.api.CommonPage;
import com.hhoa.blog.common.api.CommonResult;
import com.hhoa.blog.mgb.model.AmsCatalog;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author hhoa
 **/

@RestController
@RequiredArgsConstructor
@Tag(name = "目录管理", description = "AmsCatalogController")
public class AmsCatalogController {
    private final AmsCatalogService catalogService;

    @Operation(description = "分页获取目录列表", summary = "分页获取目录列表")
    @GetMapping("/catalogs")
    @Parameters({@Parameter()})
    public CommonResult<CommonPage<AmsCatalog>> list(PageInfo pageInfo,
                                                     AmsCatalog catalogParams) {
        List<AmsCatalog> amsCatalogs = catalogService.selectList(catalogParams, pageInfo);
        return CommonResult.success(CommonPage.restPage(amsCatalogs));
    }

    @Operation(description = "添加目录", summary = "添加目录")
    @PostMapping("/catalogs")
    public CommonResult<String> addCatalog(@RequestBody AmsCatalog catalogParam) {
        catalogService.addCatalog(catalogParam);
        return CommonResult.success(null);
    }

    @Operation(summary = "修改目录")
    @PatchMapping("/catalogs")
    public CommonResult<String> updateCatalog(@RequestBody AmsCatalog catalogParam) {
        catalogService.updateCatalog(catalogParam);
        return CommonResult.success(null);
    }

    @Operation(summary = "删除目录")
    @DeleteMapping("/catalogs/{catalogName}")
    public CommonResult<String> delCatalog(@PathVariable String catalogName) {
        catalogService.deleteCatalog(catalogName);
        return CommonResult.success(null);
    }
}
