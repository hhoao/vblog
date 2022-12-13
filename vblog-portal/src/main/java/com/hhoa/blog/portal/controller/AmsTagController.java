package com.hhoa.blog.portal.controller;

import com.hhoa.blog.common.api.CommonPage;
import com.hhoa.blog.common.api.CommonResult;
import com.hhoa.blog.mgb.model.AmsArticle;
import com.hhoa.blog.portal.bean.PageInfo;
import com.hhoa.blog.portal.service.AmsArticleService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author hhoa
 * @since 2022/12/12
 **/

@RestController
@RequiredArgsConstructor
@Tag(name = "文章标签管理", description = "AmsTagController")
public class AmsTagController {
    private final AmsArticleService articleService;

    @Operation(description = "分页获取标签列表", summary = "分页获取标签列表")
    @GetMapping("/tags")
    @Parameters({@Parameter()})
    public CommonResult<CommonPage<AmsArticle>> list(PageInfo pageInfo,
                                                     AmsArticle articleParams,
                                                     @Parameter(name = "base") String base) {
        List<AmsArticle> amsArticles = articleService.list(articleParams, pageInfo);
        return CommonResult.success(CommonPage.restPage(amsArticles));
    }
}
