package com.hhoa.blog.mgb.mapper;

import com.hhoa.blog.mgb.model.AmsArticle;
import com.hhoa.blog.mgb.model.AmsArticleExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface AmsArticleMapper {
    /**
     * countByExample
     * @param example example
     * @return long
     */
    long countByExample(AmsArticleExample example);

    /**
     * deleteByExample
     * @param example example
     * @return int
     */
    int deleteByExample(AmsArticleExample example);

    /**
     * deleteByPrimaryKey
     * @param id id
     * @return int
     */
    int deleteByPrimaryKey(Long id);

    /**
     * insert
     * @param row row
     * @return int
     */
    int insert(AmsArticle row);

    /**
     * insertSelective
     * @param row row
     * @return int
     */
    int insertSelective(AmsArticle row);

    /**
     * selectByExampleWithBLOBs
     * @param example example
     * @return java.util.List<com.hhoa.blog.mgb.model.AmsArticle>
     */
    List<AmsArticle> selectByExampleWithBLOBs(AmsArticleExample example);

    /**
     * selectByExample
     * @param example example
     * @return java.util.List<com.hhoa.blog.mgb.model.AmsArticle>
     */
    List<AmsArticle> selectByExample(AmsArticleExample example);

    /**
     * selectByPrimaryKey
     * @param id id
     * @return com.hhoa.blog.mgb.model.AmsArticle
     */
    AmsArticle selectByPrimaryKey(Long id);

    /**
     * updateByExampleSelective
     * @param row row
     * @param example example
     * @return int
     */
    int updateByExampleSelective(@Param("row") AmsArticle row, @Param("example") AmsArticleExample example);

    /**
     * updateByExampleWithBLOBs
     * @param row row
     * @param example example
     * @return int
     */
    int updateByExampleWithBLOBs(@Param("row") AmsArticle row, @Param("example") AmsArticleExample example);

    /**
     * updateByExample
     * @param row row
     * @param example example
     * @return int
     */
    int updateByExample(@Param("row") AmsArticle row, @Param("example") AmsArticleExample example);

    /**
     * updateByPrimaryKeySelective
     * @param row row
     * @return int
     */
    int updateByPrimaryKeySelective(AmsArticle row);

    /**
     * updateByPrimaryKeyWithBLOBs
     * @param row row
     * @return int
     */
    int updateByPrimaryKeyWithBLOBs(AmsArticle row);

    /**
     * updateByPrimaryKey
     * @param row row
     * @return int
     */
    int updateByPrimaryKey(AmsArticle row);
}