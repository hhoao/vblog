package com.hhoa.blog.portal.service;

import com.hhoa.blog.mgb.model.AmsArticle;
import com.hhoa.blog.mgb.model.AmsCatalog;
import com.hhoa.blog.portal.bean.PageInfo;

import java.util.List;

/**
 * The interface Ams catalog service.
 *
 * @author hhoa
 * @since 2022 /9/3
 */
public interface AmsCatalogService  {
    /**
     * Select list list.
     *
     * @param amsCatalogQueryWrapper the ams catalog query wrapper
     * @param pageInfo               the page info
     * @return the list
     */
    List<AmsCatalog> selectList(AmsCatalog amsCatalogQueryWrapper, PageInfo pageInfo);

    /**
     * Gets catalog articles.
     *
     * @param catalogId the catalog id
     * @param pageInfo  the page info
     * @return the catalog articles
     */
    List<AmsArticle> getCatalogArticles(Long catalogId, PageInfo pageInfo);
}
