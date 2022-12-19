package com.hhoa.vblog.portal.service;

import com.hhoa.vblog.mgb.model.AmsArticle;
import com.hhoa.vblog.portal.bean.PageInfo;

import java.util.List;

/**
 * The interface Ams article service.
 *
 * @author hhoa
 * @since 2022 /9/3
 */
public interface AmsArticleService {
    /**
     * List list.
     *
     * @param articleParams the article params
     * @param pageInfo      the page info
     * @return the list
     */
    List<AmsArticle> list(AmsArticle articleParams, PageInfo pageInfo);

    /**
     * Select by id ams article.
     *
     * @param articleId the article id
     * @return the ams article
     */
    AmsArticle selectById(Long articleId);
}
