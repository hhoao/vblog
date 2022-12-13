package com.hhoa.blog.portal.controller;


import com.hhoa.blog.common.api.CommonPage;
import com.hhoa.blog.common.api.CommonResult;
import com.hhoa.blog.mgb.model.AmsArticle;
import com.hhoa.blog.mgb.model.AmsCatalog;
import com.hhoa.blog.portal.bean.PageInfo;
import com.hhoa.blog.portal.service.AmsCatalogService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

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
    public CommonResult<CommonPage<AmsCatalog>> list(PageInfo pageInfo,
                                                     AmsCatalog catalogParams) {
        List<AmsCatalog> amsCatalogs = catalogService.selectList(catalogParams, pageInfo);
        return CommonResult.success(CommonPage.restPage(amsCatalogs));
    }


    @Operation(summary = "分页获取目录文章")
    @GetMapping("/catalogs/{catalogId}/articles")
    public CommonResult<CommonPage<AmsArticle>> getCatalogArticles(PageInfo pageInfo,
                                                 @PathVariable Long catalogId){
        List<AmsArticle> articles = catalogService.getCatalogArticles(catalogId, pageInfo);
        return CommonResult.success(CommonPage.restPage(articles));
    }
}
