package com.hhoa.vblog.admin.service.impl;

import com.hhoa.vblog.admin.TransactionTest;
import com.hhoa.vblog.admin.bean.PageInfo;
import com.hhoa.vblog.admin.service.AmsArticleService;
import com.hhoa.vblog.admin.service.AmsCommentService;
import com.hhoa.vblog.mgb.model.AmsArticle;
import com.hhoa.vblog.mgb.model.AmsComment;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;


/**
 * AmsCommentServiceImplTest.
 *
 * @author hhoa
 * @since 2022/12/20
 **/

class AmsCommentServiceImplTest extends TransactionTest {

    @Autowired
    AmsCommentService commentService;

    @Autowired
    AmsArticleService articleService;

    public AmsArticle getArticleExample() {
        AmsArticle amsArticle = new AmsArticle();
        amsArticle.setContent("HelloWorldContent");
        amsArticle.setTitle("HelloWorldTitle");
        amsArticle.setDigest("HelloWorldDigest");
        amsArticle.setAuthor("HelloWorldAuthor");

        articleService.addArticle(amsArticle);
        List<AmsArticle> articleList = articleService.list(amsArticle, new PageInfo(1, 0));
        return articleList.get(0);
    }

    @Test
    void list() {
        AmsArticle articleExample = getArticleExample();
        AmsComment amsComment = new AmsComment();
        amsComment.setNickname("testNickName");
        amsComment.setArticleId(articleExample.getId());
        commentService.addComment(amsComment);
        List<AmsComment> list = commentService.list(null, new PageInfo(1, 0));
        Assertions.assertTrue(list.size() > 0);
    }

    @Test
    void addComment() {
        AmsArticle articleExample = getArticleExample();
        AmsComment amsComment = new AmsComment();
        Assertions.assertThrows(Exception.class, () -> commentService.addComment(amsComment));
        amsComment.setArticleId(articleExample.getId());
        amsComment.setNickname("testNickname");
        commentService.addComment(amsComment);
        List<AmsComment> list = commentService.list(null, new PageInfo(1, 0));
        Assertions.assertTrue(list.size() > 0);
    }

    @Test
    void deleteComment() {
        AmsArticle articleExample = getArticleExample();
        AmsComment amsComment = new AmsComment();
        amsComment.setNickname("testNickname");
        amsComment.setArticleId(articleExample.getId());
        commentService.addComment(amsComment);
        List<AmsComment> list = commentService.list(null, new PageInfo(1, 0));
        AmsComment amsComment1 = list.get(0);
        commentService.deleteComment(amsComment1.getId());
    }

    @Test
    void updateComment() {
    }
}
