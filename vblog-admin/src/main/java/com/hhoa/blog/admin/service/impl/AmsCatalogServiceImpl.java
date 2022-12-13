package com.hhoa.blog.admin.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.github.pagehelper.PageHelper;
import com.hhoa.blog.admin.bean.PageInfo;
import com.hhoa.blog.admin.dao.AmsCatalogDao;
import com.hhoa.blog.admin.service.AmsArticleCatalogRelationService;
import com.hhoa.blog.admin.service.AmsCatalogService;
import com.hhoa.blog.mgb.model.AmsArticle;
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
        List<AmsArticle> articles = articleCatalogRelationService.getCatalogArticles(catalogId);
        return articles;
    }
}
