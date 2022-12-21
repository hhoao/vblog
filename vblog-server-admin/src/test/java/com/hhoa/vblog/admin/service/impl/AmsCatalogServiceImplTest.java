package com.hhoa.vblog.admin.service.impl;

import com.hhoa.vblog.admin.TransactionTest;
import com.hhoa.vblog.admin.bean.PageInfo;
import com.hhoa.vblog.admin.service.AmsCatalogService;
import com.hhoa.vblog.mgb.model.AmsCatalog;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * AmsCatalogServiceTest.
 *
 * @author hhoa
 * @since 2022/12/20
 **/

class AmsCatalogServiceImplTest extends TransactionTest {
    @Autowired
    AmsCatalogService catalogService;

    @Test
    void selectList() {
        AmsCatalog amsCatalog = new AmsCatalog();
        amsCatalog.setName("testName");
        catalogService.addCatalog(amsCatalog);
        List<AmsCatalog> list = catalogService.selectList(
                null, new PageInfo(1, 0));
        Assertions.assertTrue(list.size() > 0);
    }

    @Test
    void addCatalog() {
        AmsCatalog amsCatalog = new AmsCatalog();
        Assertions.assertThrows(Exception.class, () -> catalogService.addCatalog(amsCatalog));
        amsCatalog.setName("testName");
        catalogService.addCatalog(amsCatalog);
        List<AmsCatalog> list = catalogService.selectList(
                null, new PageInfo(1, 0));
        Assertions.assertTrue(list.size() > 0);
    }

    @Test
    void deleteCatalog() {
        AmsCatalog amsCatalog = new AmsCatalog();
        amsCatalog.setName("testName");
        catalogService.addCatalog(amsCatalog);
        List<AmsCatalog> list = catalogService.selectList(
                null, new PageInfo(1, 0));
        AmsCatalog amsCatalog1 = list.get(0);
        catalogService.deleteCatalog(amsCatalog1.getId());
    }

    @Test
    void updateCatalog() {
    }


    @Test
    void getCatalogArticles() {
    }
}
