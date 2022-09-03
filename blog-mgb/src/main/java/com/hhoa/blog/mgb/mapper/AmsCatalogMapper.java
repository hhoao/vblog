package com.hhoa.blog.mgb.mapper;

import com.hhoa.blog.mgb.model.AmsCatalog;
import com.hhoa.blog.mgb.model.AmsCatalogExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface AmsCatalogMapper {
    /**
     * countByExample
     * @param example example
     * @return long
     */
    long countByExample(AmsCatalogExample example);

    /**
     * deleteByExample
     * @param example example
     * @return int
     */
    int deleteByExample(AmsCatalogExample example);

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
    int insert(AmsCatalog row);

    /**
     * insertSelective
     * @param row row
     * @return int
     */
    int insertSelective(AmsCatalog row);

    /**
     * selectByExample
     * @param example example
     * @return java.util.List<com.hhoa.blog.mgb.model.AmsCatalog>
     */
    List<AmsCatalog> selectByExample(AmsCatalogExample example);

    /**
     * selectByPrimaryKey
     * @param id id
     * @return com.hhoa.blog.mgb.model.AmsCatalog
     */
    AmsCatalog selectByPrimaryKey(Long id);

    /**
     * updateByExampleSelective
     * @param row row
     * @param example example
     * @return int
     */
    int updateByExampleSelective(@Param("row") AmsCatalog row, @Param("example") AmsCatalogExample example);

    /**
     * updateByExample
     * @param row row
     * @param example example
     * @return int
     */
    int updateByExample(@Param("row") AmsCatalog row, @Param("example") AmsCatalogExample example);

    /**
     * updateByPrimaryKeySelective
     * @param row row
     * @return int
     */
    int updateByPrimaryKeySelective(AmsCatalog row);

    /**
     * updateByPrimaryKey
     * @param row row
     * @return int
     */
    int updateByPrimaryKey(AmsCatalog row);
}