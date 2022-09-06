package com.hhoa.blog.mgb.mapper;

import com.hhoa.blog.mgb.model.UmsAccountTagRelation;
import com.hhoa.blog.mgb.model.UmsAccountTagRelationExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface UmsAccountTagRelationMapper {
    /**
     * countByExample
     * @param example example
     * @return long
     */
    long countByExample(UmsAccountTagRelationExample example);

    /**
     * deleteByExample
     * @param example example
     * @return int
     */
    int deleteByExample(UmsAccountTagRelationExample example);

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
    int insert(UmsAccountTagRelation row);

    /**
     * insertSelective
     * @param row row
     * @return int
     */
    int insertSelective(UmsAccountTagRelation row);

    /**
     * selectByExample
     * @param example example
     * @return java.util.List<com.hhoa.blog.mgb.model.UmsAccountTagRelation>
     */
    List<UmsAccountTagRelation> selectByExample(UmsAccountTagRelationExample example);

    /**
     * selectByPrimaryKey
     * @param id id
     * @return com.hhoa.blog.mgb.model.UmsAccountTagRelation
     */
    UmsAccountTagRelation selectByPrimaryKey(Long id);

    /**
     * updateByExampleSelective
     * @param row row
     * @param example example
     * @return int
     */
    int updateByExampleSelective(@Param("row") UmsAccountTagRelation row, @Param("example") UmsAccountTagRelationExample example);

    /**
     * updateByExample
     * @param row row
     * @param example example
     * @return int
     */
    int updateByExample(@Param("row") UmsAccountTagRelation row, @Param("example") UmsAccountTagRelationExample example);

    /**
     * updateByPrimaryKeySelective
     * @param row row
     * @return int
     */
    int updateByPrimaryKeySelective(UmsAccountTagRelation row);

    /**
     * updateByPrimaryKey
     * @param row row
     * @return int
     */
    int updateByPrimaryKey(UmsAccountTagRelation row);
}