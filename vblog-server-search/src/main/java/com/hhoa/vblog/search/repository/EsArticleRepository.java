package com.hhoa.vblog.search.repository;

import com.hhoa.vblog.search.bean.EsArticle;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.annotations.Query;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

/**
 * EsArticleRepository.
 *
 * @author hhoa
 * @since 2022/8/4
 **/

public interface EsArticleRepository extends ElasticsearchRepository<EsArticle, Integer> {
    @Query("""
            {
                "bool": {
                  "should": [
                    {
                      "match": {
                        "title": {
                          "query": "?0",
                          "analyzer": "ik_max_word",
                          "boost": 2
                        }
                      }
                    },
                    {
                      "match": {
                        "digest": {
                          "query": "?",
                          "analyzer": "ik_max_word",
                          "boost": 1.5
                        }
                      }
                    },
                    {
                      "match": {
                        "content": {
                          "query": "?0",
                          "analyzer": "ik_max_word",
                          "boost": 0.5
                        }
                      }
                    }
                  ]
                }
              }
            """)
    Page<EsArticle> search(String queryInfo, Pageable page);
}
