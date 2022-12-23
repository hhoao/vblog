package com.hhoa.vblog.portal.controller;

import com.hhoa.vblog.common.api.CommonPage;
import com.hhoa.vblog.common.api.CommonResult;
import com.hhoa.vblog.mgb.model.AmsArticle;
import com.hhoa.vblog.portal.bean.PageInfo;
import com.hhoa.vblog.portal.service.AmsArticleService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.Builder;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.context.properties.bind.DefaultValue;
import org.springframework.lang.Nullable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.constraints.Null;
import java.util.List;

/**
 * The type Ams article controller.
 *
 * @author hhoa
 * @since 2022 /9/3
 */
@RestController
@RequiredArgsConstructor
@Tag(name = "文章管理", description = "AmsArticleController")
public class AmsArticleController {
    private final AmsArticleService articleService;

    @Operation(description = "分页获取文章列表", summary = "分页获取文章列表")
    @GetMapping("/articles")
    public CommonResult<CommonPage<AmsArticle>> list(@Nullable PageInfo pageInfo,
                                                     AmsArticle articleParams,
                                                     @Parameter(name = "base") String base) {
        List<AmsArticle> amsArticles = articleService.list(articleParams, pageInfo);
        return CommonResult.success(CommonPage.restPage(amsArticles));
    }

    @Operation(summary = "通过Id获取文章")
    @GetMapping("/articles/{id}")
    public CommonResult<AmsArticle> selectById(@PathVariable("id") Long id) {
        AmsArticle article = articleService.selectById(id);
        return CommonResult.success(article);
    }
}
