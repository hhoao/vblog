package com.hhoa.vblog.portal.service.impl;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.github.pagehelper.PageHelper;
import com.hhoa.vblog.common.exception.Asserts;
import com.hhoa.vblog.mgb.model.UmsResource;
import com.hhoa.vblog.portal.bean.PageInfo;
import com.hhoa.vblog.portal.dao.UmsResourceDao;
import com.hhoa.vblog.portal.service.UmsResourceService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * The type Ums resource service.
 *
 * @author hhoa
 * @since 2022 /5/5
 */
@Service
@RequiredArgsConstructor
public class UmsResourceServiceImpl implements UmsResourceService {
    private final UmsResourceDao resourceDao;

    @Override
    public UmsResource getResource(Long resourceId) {
        UmsResource retResource = resourceDao.selectById(resourceId);
        if (retResource == null) {
            Asserts.fail("没有该资源");
        }
        return retResource;
    }

    @Override
    public List<UmsResource> getAllResources() {
        return resourceDao.selectList(new QueryWrapper<>());
    }


    @Override
    public List<UmsResource> list(PageInfo pageInfo, UmsResource resource) {
        PageHelper.startPage(pageInfo.getPageNum(), pageInfo.getPageSize());
        return resourceDao.selectList(new QueryWrapper<>(resource));
    }
}
