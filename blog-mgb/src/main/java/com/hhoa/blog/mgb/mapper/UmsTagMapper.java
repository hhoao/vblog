package com.hhoa.blog.mgb.mapper;

import com.hhoa.blog.mgb.model.UmsTag;
import com.hhoa.blog.mgb.model.UmsTagExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface UmsTagMapper {
    /**
     * countByExample
     * @param example example
     * @return long
     */
    long countByExample(UmsTagExample example);

    /**
     * deleteByExample
     * @param example example
     * @return int
     */
    int deleteByExample(UmsTagExample example);

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
    int insert(UmsTag row);

    /**
     * insertSelective
     * @param row row
     * @return int
     */
    int insertSelective(UmsTag row);

    /**
     * selectByExample
     * @param example example
     * @return java.util.List<com.hhoa.blog.mgb.model.UmsTag>
     */
    List<UmsTag> selectByExample(UmsTagExample example);

    /**
     * selectByPrimaryKey
     * @param id id
     * @return com.hhoa.blog.mgb.model.UmsTag
     */
    UmsTag selectByPrimaryKey(Long id);

    /**
     * updateByExampleSelective
     * @param row row
     * @param example example
     * @return int
     */
    int updateByExampleSelective(@Param("row") UmsTag row, @Param("example") UmsTagExample example);

    /**
     * updateByExample
     * @param row row
     * @param example example
     * @return int
     */
    int updateByExample(@Param("row") UmsTag row, @Param("example") UmsTagExample example);

    /**
     * updateByPrimaryKeySelective
     * @param row row
     * @return int
     */
    int updateByPrimaryKeySelective(UmsTag row);

    /**
     * updateByPrimaryKey
     * @param row row
     * @return int
     */
    int updateByPrimaryKey(UmsTag row);
}