package com.hhoa.vblog.admin.service;

import com.hhoa.vblog.admin.bean.PageInfo;
import com.hhoa.vblog.mgb.model.AmsArticle;
import com.hhoa.vblog.mgb.model.AmsCatalog;

import java.util.List;

/**
 * The interface Ams catalog service.
 *
 * @author hhoa
 * @since 2022 /9/3
 */
public interface AmsCatalogService {
    /**
     * Select list list.
     *
     * @param amsCatalogQueryWrapper the ams catalog query wrapper
     * @param pageInfo               the page info
     * @return the list
     */
    List<AmsCatalog> selectList(AmsCatalog amsCatalogQueryWrapper,
                                PageInfo pageInfo);

    /**
     * Add catalog.
     *
     * @param catalogParam the catalog param
     */
    void addCatalog(AmsCatalog catalogParam);

    /**
     * Update catalog.
     *
     * @param catalogParam the catalog param
     */
    void updateCatalog(AmsCatalog catalogParam);

    /**
     * Delete catalog.
     *
     * @param catalogId the catalog name
     */
    void deleteCatalog(Long catalogId);

    /**
     * Gets catalog articles.
     *
     * @param catalogId the catalog id
     * @param pageInfo  the page info
     * @return the catalog articles
     */
    List<AmsArticle> getCatalogArticles(Long catalogId, PageInfo pageInfo);
}
