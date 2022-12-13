package com.hhoa.blog.portal.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.hhoa.blog.mgb.model.AmsArticle;
import com.hhoa.blog.mgb.model.AmsArticleCatalogRelation;
import com.hhoa.blog.portal.dao.AmsArticleCatalogRelationDao;
import com.hhoa.blog.portal.service.AmsArticleCatalogRelationService;
import com.hhoa.blog.portal.service.AmsArticleService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Stream;

/**
 * @author hhoa
 * @since 2022/9/4
 **/

@AllArgsConstructor
@Service
public class AmsArticleCatalogRelationServiceImpl implements AmsArticleCatalogRelationService {
    private AmsArticleCatalogRelationDao articleCatalogRelationDao;
    private AmsArticleService articleService;


    @Override
    public List<AmsArticle> getCatalogArticles(Long catalogId) {
        AmsArticleCatalogRelation articleCatalogRelation = new AmsArticleCatalogRelation();
        articleCatalogRelation.setCatalogId(catalogId);
        Stream<AmsArticle> amsArticleStream = articleCatalogRelationDao.selectList(new QueryWrapper<>(articleCatalogRelation)).
                stream().map(relation -> {
                    Long articleId = articleCatalogRelation.getArticleId();
                    return articleService.selectById(articleId);
                });
        return amsArticleStream.toList();
    }
}
