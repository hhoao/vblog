package com.hhoa.vblog.admin.service.impl;

import com.hhoa.vblog.admin.TransactionTest;
import com.hhoa.vblog.admin.bean.PageInfo;
import com.hhoa.vblog.admin.bean.UmsResourceParam;
import com.hhoa.vblog.admin.config.AdminJwtSecurityConfig;
import com.hhoa.vblog.admin.service.UmsResourceService;
import com.hhoa.vblog.mgb.model.UmsResource;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.function.Consumer;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

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

    static List<Object[]> listParamsProvider() {
        List<Object[]> params = new ArrayList<>();

        UmsResource resource = new UmsResource();
        resource.setMethod("get");
        params.add(
                new Object[]{new PageInfo(1, 5),
                    resource,
                    (Consumer<List<Object>>) o -> Assertions.assertTrue(o.size() > 0)});

        resource = new UmsResource();
        resource.setUrl("/**");
        params.add(new Object[]{
            new PageInfo(1, 5),
            resource,
            (Consumer<List<Object>>) o -> Assertions.assertTrue(o.size() > 0)});

        resource = new UmsResource();
        resource.setId(1L);
        params.add(
                new Object[]{new PageInfo(1, 5),
                    resource, (Consumer<List<Object>>) o -> Assertions.assertTrue(o.size() > 0)});

        params.add(new Object[]{
            new PageInfo(1, 5),
            null,
            (Consumer<List<Object>>) o -> assertEquals(5, o.size())});

        return params;
    }

    @Test
    void getResource() {
        UmsResource allGetResources = resourceService.getResource("所有GET资源");
        assertNotNull(allGetResources);
        UmsResource resource = resourceService.getResource(1L);
        assertEquals(resource.getName(), allGetResources.getName());
    }

    @Test
    void getAllResources() {
        List<UmsResource> allResources = resourceService.getAllResources();
        Assertions.assertTrue(allResources.size() > 0);
    }

    @Test
    void addResource() {
        UmsResourceParam resourceParam = new UmsResourceParam();
        resourceParam.setMethod("GET");
        resourceParam.setName("哈哈哈");
        resourceParam.setUrl("URL");
        resourceService.addResource(resourceParam);
        AdminJwtSecurityConfig.AdminDynamicSecurityServiceConfig
                adminDynamicSecurityServiceConfig =
                applicationContext
                        .getBean(AdminJwtSecurityConfig.AdminDynamicSecurityServiceConfig.class);
        Map<AntPathRequestMatcher, ConfigAttribute> dataSource =
                adminDynamicSecurityServiceConfig.getDataSource();
        UmsResource resource = resourceService.getResource("哈哈哈");
        assertNotNull(dataSource.get(new AntPathRequestMatcher(resource.getUrl(),
                resource.getMethod())));
    }

    @Test
    void updateResource() {
        UmsResourceParam resourceParam = new UmsResourceParam();
        resourceParam.setMethod("GET");
        resourceParam.setName("我的世界");
        resourceService.updateResource("所有GET资源", resourceParam);
        AdminJwtSecurityConfig.AdminDynamicSecurityServiceConfig
                adminDynamicSecurityServiceConfig =
                applicationContext.getBean(
                        AdminJwtSecurityConfig.AdminDynamicSecurityServiceConfig.class);
        Map<AntPathRequestMatcher, ConfigAttribute> dataSource =
                adminDynamicSecurityServiceConfig.getDataSource();
        UmsResource resource = resourceService.getResource("我的世界");
        assertNotNull(dataSource.get(
                new AntPathRequestMatcher(resource.getUrl(), resource.getMethod())));
    }

    @ParameterizedTest
    @ValueSource(longs = {1L})
    void deleteResource(Long resourceId) {
        AdminJwtSecurityConfig.AdminDynamicSecurityServiceConfig adminDynamicSecurityServiceConfig =
                applicationContext.getBean(
                        AdminJwtSecurityConfig.AdminDynamicSecurityServiceConfig.class);
        Map<AntPathRequestMatcher, ConfigAttribute> dataSource =
                adminDynamicSecurityServiceConfig.getDataSource();
        UmsResource resource = resourceService.getResource(resourceId);
        Assertions.assertNotNull(dataSource.get(
                new AntPathRequestMatcher(resource.getUrl(), resource.getMethod())));
        resourceService.deleteResource(resourceId);
        Assertions.assertNull(dataSource.get(
                new AntPathRequestMatcher(resource.getUrl(), resource.getMethod())));
    }

    @ParameterizedTest
    @ValueSource(strings = {"所有GET资源"})
    void deleteResource(String resourceName) {
        AdminJwtSecurityConfig.AdminDynamicSecurityServiceConfig adminDynamicSecurityServiceConfig =
                applicationContext.getBean(
                        AdminJwtSecurityConfig.AdminDynamicSecurityServiceConfig.class);
        Map<AntPathRequestMatcher, ConfigAttribute> dataSource =
                adminDynamicSecurityServiceConfig.getDataSource();
        UmsResource resource = resourceService.getResource(resourceName);
        Assertions.assertNotNull(dataSource.get(
                new AntPathRequestMatcher(resource.getUrl(), resource.getMethod())));
        resourceService.deleteResource(resourceName);
        Assertions.assertNull(dataSource.get(
                new AntPathRequestMatcher(resource.getUrl(), resource.getMethod())));
    }

    @ParameterizedTest
    @MethodSource("listParamsProvider")
    void list(PageInfo pageInfo, UmsResource resource, Consumer<Object> consumer) {
        List<UmsResource> list = resourceService.list(pageInfo, resource);
        consumer.accept(list);
    }
}
