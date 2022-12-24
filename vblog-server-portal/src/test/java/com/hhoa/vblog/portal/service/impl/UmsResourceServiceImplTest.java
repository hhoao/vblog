package com.hhoa.vblog.portal.service.impl;

import com.hhoa.vblog.mgb.model.UmsResource;
import com.hhoa.vblog.portal.TransactionTest;
import com.hhoa.vblog.portal.bean.PageInfo;
import com.hhoa.vblog.portal.service.UmsResourceService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ConfigurableApplicationContext;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * UmsResourceServiceImplTest.
 *
 * @author hhoa
 * @since 2022/6/4
 **/

class UmsResourceServiceImplTest extends TransactionTest {
    @Autowired
    UmsResourceService resourceService;
    @Autowired
    ConfigurableApplicationContext applicationContext;

    @Test
    void getResource() {
    }

    @Test
    void getAllResources() {
        List<UmsResource> allResources = resourceService.getAllResources();
        Assertions.assertTrue(allResources.size() > 0);
    }
}
