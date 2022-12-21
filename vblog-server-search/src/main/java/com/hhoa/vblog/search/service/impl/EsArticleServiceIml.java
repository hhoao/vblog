package com.hhoa.vblog.search.service.impl;

import com.hhoa.vblog.search.bean.EsArticle;
import com.hhoa.vblog.search.repository.EsArticleRepository;
import com.hhoa.vblog.search.service.EsArticleService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.core.ElasticsearchOperations;
import org.springframework.stereotype.Service;

/**
 * EsArticleServiceIml.
 *
 * @author hhoa
 * @since 2022/8/4
 **/

@Service
public class EsArticleServiceIml implements EsArticleService {
    final EsArticleRepository esArticleRepository;
    final ElasticsearchOperations elasticsearchOperations;

    public EsArticleServiceIml(ElasticsearchOperations elasticsearchOperations,
                               EsArticleRepository articleRepository) {
        this.elasticsearchOperations = elasticsearchOperations;
        this.esArticleRepository = articleRepository;
    }

    @Override
    public Page<EsArticle> search(String queryInfo, Pageable pageRequest) {
        return esArticleRepository.search(queryInfo, pageRequest);
    }
}
