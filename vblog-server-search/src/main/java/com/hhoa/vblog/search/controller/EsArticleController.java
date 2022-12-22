package com.hhoa.vblog.search.controller;

;
import com.hhoa.vblog.common.api.CommonPage;
import com.hhoa.vblog.common.api.CommonResult;
import com.hhoa.vblog.search.bean.EsArticle;
import com.hhoa.vblog.search.bean.EsPage;
import com.hhoa.vblog.search.service.EsArticleService;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.tags.Tags;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * EsArticleController.
 *
 * @author hhoa
 * @since 2022/8/4
 **/
@Tags({@Tag(name = "文章搜索管理", description = "RetArticleController")})
@RestController
@RequiredArgsConstructor
public class EsArticleController {
    private final EsArticleService esArticleService;

    @GetMapping("/search/article")
    public CommonResult<CommonPage<EsArticle>> search(String queryInfo, EsPage page) {
        Page<EsArticle> search = esArticleService.search(queryInfo, page);
        return CommonResult.success(CommonPage.restPage(search));
    }
}
