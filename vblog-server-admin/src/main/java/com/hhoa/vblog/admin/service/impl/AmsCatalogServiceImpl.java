package com.hhoa.vblog.admin.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.github.pagehelper.PageHelper;
import com.hhoa.vblog.admin.bean.PageInfo;
import com.hhoa.vblog.admin.dao.AmsCatalogDao;
import com.hhoa.vblog.admin.service.AmsArticleCatalogRelationService;
import com.hhoa.vblog.admin.service.AmsCatalogService;
import com.hhoa.vblog.mgb.model.AmsArticle;
import com.hhoa.vblog.mgb.model.AmsCatalog;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * The type Ams catalog service.
 *
 * @author hhoa
 * @since 2022 /9/3
 */
@Service
@AllArgsConstructor
public class AmsCatalogServiceImpl implements AmsCatalogService {
    private AmsCatalogDao catalogDao;
    private AmsArticleCatalogRelationService articleCatalogRelationService;

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
    public void deleteCatalog(Long catalogId) {
        catalogDao.deleteById(catalogId);
    }

    @Override
    public List<AmsArticle> getCatalogArticles(Long catalogId, PageInfo pageInfo) {
        PageHelper.startPage(pageInfo);
        return articleCatalogRelationService.getCatalogArticles(catalogId);
    }
}
