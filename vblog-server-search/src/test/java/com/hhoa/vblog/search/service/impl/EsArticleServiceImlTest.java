package com.hhoa.vblog.search.service.impl;

import com.hhoa.vblog.search.service.EsArticleService;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * EsMaterialServiceImlTest.
 *
 * @author hhoa
 * @since 2022/12/22
 **/

@SpringBootTest
class EsArticleServiceImlTest {
    @Autowired
    EsArticleService articleService;

    @Disabled
    @Test
    void search() {
    }
}
