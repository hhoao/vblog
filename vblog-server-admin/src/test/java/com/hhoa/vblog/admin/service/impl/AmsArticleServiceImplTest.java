package com.hhoa.vblog.admin.service.impl;

import com.hhoa.vblog.admin.TransactionTest;
import com.hhoa.vblog.admin.bean.PageInfo;
import com.hhoa.vblog.admin.service.AmsArticleService;
import com.hhoa.vblog.mgb.model.AmsArticle;
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
class AmsArticleServiceImplTest extends TransactionTest {
    @Autowired
    private AmsArticleService articleService;

    static AmsArticle getArticleExample() {
        AmsArticle amsArticle = new AmsArticle();
        amsArticle.setContent("HelloWorldContent");
        amsArticle.setTitle("HelloWorldTitle");
        amsArticle.setDigest("HelloWorldDigest");
        amsArticle.setAuthor("HelloWorldAuthor");
        return amsArticle;
    }

    @Test
    void list() {
        articleService.addArticle(getArticleExample());
        AmsArticle article = new AmsArticle();
        List<AmsArticle> list = articleService.list(article, new PageInfo(1, 5));
        Assertions.assertTrue(list.size() > 0);
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

    @Test
    void addArticle() {
        articleService.addArticle(getArticleExample());
        AmsArticle article = new AmsArticle();
        List<AmsArticle> list = articleService.list(article, new PageInfo(1, 5));
        Assertions.assertTrue(list.size() > 0);
    }

    @Test
    void updateArticle() {
        AmsArticle articleExample = getArticleExample();
        articleService.addArticle(articleExample);
        List<AmsArticle> list = articleService.list(articleExample,
                new PageInfo(1, 0));
        AmsArticle amsArticle = list.get(0);
        amsArticle.setTitle("TestArticle");
        articleService.updateArticle(amsArticle);
        AmsArticle amsArticle1 = articleService.selectById(amsArticle.getId());
        Assertions.assertEquals(amsArticle1.getTitle(), amsArticle.getTitle());
    }

    @Test
    void deleteArticle() {
        AmsArticle articleExample = getArticleExample();
        articleService.addArticle(articleExample);
        List<AmsArticle> list = articleService.list(articleExample,
                new PageInfo(1, 0));
        AmsArticle amsArticle = list.get(0);
        articleService.deleteArticle(amsArticle.getId());
        Assertions.assertNull(articleService.selectById(amsArticle.getId()));
    }
}
