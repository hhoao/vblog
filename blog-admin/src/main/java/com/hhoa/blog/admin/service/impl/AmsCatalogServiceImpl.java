package com.hhoa.blog.admin.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.github.pagehelper.PageHelper;
import com.hhoa.blog.admin.bean.PageInfo;
import com.hhoa.blog.admin.dao.AmsCatalogDao;
import com.hhoa.blog.admin.service.AmsCatalogService;
import com.hhoa.blog.mgb.model.AmsCatalog;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author hhoa
 * @since 2022/9/3
 **/

@Service
@AllArgsConstructor
public class AmsCatalogServiceImpl implements AmsCatalogService {
    private AmsCatalogDao catalogDao;
    @Override
    public List<AmsCatalog> selectList(AmsCatalog amsCatalogQueryWrapper, PageInfo pageInfo) {
        PageHelper.startPage(pageInfo);
        return catalogDao.selectList(new QueryWrapper<>(amsCatalogQueryWrapper));
    }

    @Override
    public void addCatalog(AmsCatalog catalogParam) {
        catalogDao.insert(catalogParam);
    }

    @Override
    public void updateCatalog(AmsCatalog catalogParam) {
        catalogDao.updateById(catalogParam);
    }

    @Override
    public void deleteCatalog(String catalogName) {
        AmsCatalog amsCatalog = new AmsCatalog();
        amsCatalog.setName(catalogName);
        AmsCatalog delCatalog = catalogDao.selectOne(new QueryWrapper<>(amsCatalog));
        catalogDao.deleteById(delCatalog);
    }
}
