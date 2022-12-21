package com.hhoa.vblog.search.service;

import com.hhoa.vblog.search.bean.EsArticle;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * The interface Es article service.
 *
 * @author hhoa
 * @since 2022 /8/4
 */
public interface EsArticleService {
    /**
     * Search page.
     *
     * @param queryInfo   the query info
     * @param pageRequest the page request
     * @return the page
     */
    Page<EsArticle> search(String queryInfo, Pageable pageRequest);
}
