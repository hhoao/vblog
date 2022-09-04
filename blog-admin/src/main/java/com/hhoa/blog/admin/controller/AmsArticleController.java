package com.hhoa.blog.admin.controller;

import com.hhoa.blog.admin.bean.PageInfo;
import com.hhoa.blog.admin.service.AmsArticleService;
import com.hhoa.blog.common.api.CommonPage;
import com.hhoa.blog.common.api.CommonResult;
import com.hhoa.blog.mgb.model.AmsArticle;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author hhoa
 * @since 2022/9/3
 **/

@RestController
@RequiredArgsConstructor
@Tag(name = "文章管理", description = "AmsArticleController")
public class AmsArticleController {
    private final AmsArticleService articleService;

    @Operation(description = "分页获取文章列表", summary = "分页获取文章列表")
    @GetMapping("/articles")
    @Parameters({@Parameter()})
    public CommonResult<CommonPage<AmsArticle>> list(PageInfo pageInfo,
                                                     AmsArticle articleParams) {
        List<AmsArticle> amsArticles = articleService.list(articleParams, pageInfo);
        return CommonResult.success(CommonPage.restPage(amsArticles));
    }

    @Operation(description = "添加文章", summary = "添加文章")
    @PostMapping("/articles")
    public CommonResult<String> addArticle(@RequestBody AmsArticle articleParam) {
        articleService.addArticle(articleParam);
        return CommonResult.success(null);
    }

    @Operation(summary = "修改文章")
    @PatchMapping("/articles")
    public CommonResult<String> updateArticle(@RequestBody AmsArticle articleParam) {
        articleService.updateArticle(articleParam);
        return CommonResult.success(null);
    }

    @Operation(summary = "删除文章")
    @DeleteMapping("/articles/{articleId}")
    public CommonResult<String> delArticle(@PathVariable Integer articleId) {
        articleService.deleteArticle(articleId);
        return CommonResult.success(null);
    }
}
