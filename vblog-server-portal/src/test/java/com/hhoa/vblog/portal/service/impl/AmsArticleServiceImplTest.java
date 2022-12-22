package com.hhoa.vblog.portal.service.impl;

import com.hhoa.vblog.mgb.model.AmsArticle;
import com.hhoa.vblog.portal.bean.PageInfo;
import com.hhoa.vblog.portal.service.AmsArticleService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

/**
 * AmsArticleServiceImplTest.
 *
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
        articleService.list(article, new PageInfo(1, 5));
    }

    @Test
    void selectById() {
        AmsArticle article = new AmsArticle();
        List<AmsArticle> list = articleService.list(article, new PageInfo(1, 5));
        for (AmsArticle articleItem : list) {
            System.out.println(articleItem);
            AmsArticle amsArticle = articleService.selectById(articleItem.getId());
            Assertions.assertNotNull(amsArticle);
        }

    }
}
