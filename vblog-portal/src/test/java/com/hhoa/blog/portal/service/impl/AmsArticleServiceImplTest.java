package com.hhoa.blog.portal.service.impl;

import com.hhoa.blog.mgb.model.AmsArticle;
import com.hhoa.blog.portal.bean.PageInfo;
import com.hhoa.blog.portal.service.AmsArticleService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

/**
 * @author hhoa
 * @since 2022/12/13
 **/



@SpringBootTest
class AmsArticleServiceImplTest {
    @Autowired
    private AmsArticleService articleService;

    @Test
    void list() {
        AmsArticle article = new AmsArticle();
        List<AmsArticle> list = articleService.list(article, new PageInfo(1, 5));
    }

    @Test
    void selectById() {
        AmsArticle article = new AmsArticle();
        List<AmsArticle> list = articleService.list(article, new PageInfo(1, 5));
        for (AmsArticle articleItem : list) {
            AmsArticle amsArticle = articleService.selectById(articleItem.getId());
            Assertions.assertNotNull(amsArticle);
        }

    }
}
