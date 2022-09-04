package com.hhoa.blog.admin.service;

import com.hhoa.blog.admin.bean.PageInfo;
import com.hhoa.blog.mgb.model.AmsCatalog;

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
    void deleteCatalog(Integer catalogId);
}
