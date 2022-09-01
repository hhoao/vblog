package com.hhoa.blog.mgb.mapper;

import com.hhoa.blog.mgb.model.UmsRoleResourceRelation;
import com.hhoa.blog.mgb.model.UmsRoleResourceRelationExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface UmsRoleResourceRelationMapper {
    /**
     * countByExample
     * @param example example
     * @return long
     */
    long countByExample(UmsRoleResourceRelationExample example);

    /**
     * deleteByExample
     * @param example example
     * @return int
     */
    int deleteByExample(UmsRoleResourceRelationExample example);

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
    int insert(UmsRoleResourceRelation row);

    /**
     * insertSelective
     * @param row row
     * @return int
     */
    int insertSelective(UmsRoleResourceRelation row);

    /**
     * selectByExample
     * @param example example
     * @return java.util.List<com.hhoa.blog.mgb.model.UmsRoleResourceRelation>
     */
    List<UmsRoleResourceRelation> selectByExample(UmsRoleResourceRelationExample example);

    /**
     * selectByPrimaryKey
     * @param id id
     * @return com.hhoa.blog.mgb.model.UmsRoleResourceRelation
     */
    UmsRoleResourceRelation selectByPrimaryKey(Long id);

    /**
     * updateByExampleSelective
     * @param row row
     * @param example example
     * @return int
     */
    int updateByExampleSelective(@Param("row") UmsRoleResourceRelation row, @Param("example") UmsRoleResourceRelationExample example);

    /**
     * updateByExample
     * @param row row
     * @param example example
     * @return int
     */
    int updateByExample(@Param("row") UmsRoleResourceRelation row, @Param("example") UmsRoleResourceRelationExample example);

    /**
     * updateByPrimaryKeySelective
     * @param row row
     * @return int
     */
    int updateByPrimaryKeySelective(UmsRoleResourceRelation row);

    /**
     * updateByPrimaryKey
     * @param row row
     * @return int
     */
    int updateByPrimaryKey(UmsRoleResourceRelation row);
}