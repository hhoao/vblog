package com.hhoa.vblog.portal.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.hhoa.vblog.mgb.model.AmsArticle;
import com.hhoa.vblog.mgb.model.AmsArticleCatalogRelation;
import com.hhoa.vblog.portal.dao.AmsArticleCatalogRelationDao;
import com.hhoa.vblog.portal.service.AmsArticleCatalogRelationService;
import com.hhoa.vblog.portal.service.AmsArticleService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Stream;

/**
 * The type Ams article catalog relation service.
 *
 * @author hhoa
 * @since 2022 /9/4
 */
@AllArgsConstructor
@Service
public class AmsArticleCatalogRelationServiceImpl implements AmsArticleCatalogRelationService {
    private AmsArticleCatalogRelationDao articleCatalogRelationDao;
    private AmsArticleService articleService;


    @Override
    public List<AmsArticle> getCatalogArticles(Long catalogId) {
        AmsArticleCatalogRelation articleCatalogRelation = new AmsArticleCatalogRelation();
        articleCatalogRelation.setCatalogId(catalogId);
        Stream<AmsArticle> amsArticleStream =
                articleCatalogRelationDao.selectList(
                                new QueryWrapper<>(articleCatalogRelation))
                        .stream().map(relation -> {
                            Long articleId = articleCatalogRelation.getArticleId();
                            return articleService.selectById(articleId);
                        });
        return amsArticleStream.toList();
    }
}
