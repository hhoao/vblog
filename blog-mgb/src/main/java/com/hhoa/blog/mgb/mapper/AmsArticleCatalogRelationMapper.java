package com.hhoa.blog.mgb.mapper;

import com.hhoa.blog.mgb.model.AmsArticleCatalogRelation;
import com.hhoa.blog.mgb.model.AmsArticleCatalogRelationExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface AmsArticleCatalogRelationMapper {
    /**
     * countByExample
     * @param example example
     * @return long
     */
    long countByExample(AmsArticleCatalogRelationExample example);

    /**
     * deleteByExample
     * @param example example
     * @return int
     */
    int deleteByExample(AmsArticleCatalogRelationExample example);

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
    int insert(AmsArticleCatalogRelation row);

    /**
     * insertSelective
     * @param row row
     * @return int
     */
    int insertSelective(AmsArticleCatalogRelation row);

    /**
     * selectByExample
     * @param example example
     * @return java.util.List<com.hhoa.blog.mgb.model.AmsArticleCatalogRelation>
     */
    List<AmsArticleCatalogRelation> selectByExample(AmsArticleCatalogRelationExample example);

    /**
     * selectByPrimaryKey
     * @param id id
     * @return com.hhoa.blog.mgb.model.AmsArticleCatalogRelation
     */
    AmsArticleCatalogRelation selectByPrimaryKey(Long id);

    /**
     * updateByExampleSelective
     * @param row row
     * @param example example
     * @return int
     */
    int updateByExampleSelective(@Param("row") AmsArticleCatalogRelation row, @Param("example") AmsArticleCatalogRelationExample example);

    /**
     * updateByExample
     * @param row row
     * @param example example
     * @return int
     */
    int updateByExample(@Param("row") AmsArticleCatalogRelation row, @Param("example") AmsArticleCatalogRelationExample example);

    /**
     * updateByPrimaryKeySelective
     * @param row row
     * @return int
     */
    int updateByPrimaryKeySelective(AmsArticleCatalogRelation row);

    /**
     * updateByPrimaryKey
     * @param row row
     * @return int
     */
    int updateByPrimaryKey(AmsArticleCatalogRelation row);
}