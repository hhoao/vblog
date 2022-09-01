package com.hhoa.blog.mgb.mapper;

import com.hhoa.blog.mgb.model.UmsRoleMenuRelation;
import com.hhoa.blog.mgb.model.UmsRoleMenuRelationExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface UmsRoleMenuRelationMapper {
    /**
     * countByExample
     * @param example example
     * @return long
     */
    long countByExample(UmsRoleMenuRelationExample example);

    /**
     * deleteByExample
     * @param example example
     * @return int
     */
    int deleteByExample(UmsRoleMenuRelationExample example);

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
    int insert(UmsRoleMenuRelation row);

    /**
     * insertSelective
     * @param row row
     * @return int
     */
    int insertSelective(UmsRoleMenuRelation row);

    /**
     * selectByExample
     * @param example example
     * @return java.util.List<com.hhoa.blog.mgb.model.UmsRoleMenuRelation>
     */
    List<UmsRoleMenuRelation> selectByExample(UmsRoleMenuRelationExample example);

    /**
     * selectByPrimaryKey
     * @param id id
     * @return com.hhoa.blog.mgb.model.UmsRoleMenuRelation
     */
    UmsRoleMenuRelation selectByPrimaryKey(Long id);

    /**
     * updateByExampleSelective
     * @param row row
     * @param example example
     * @return int
     */
    int updateByExampleSelective(@Param("row") UmsRoleMenuRelation row, @Param("example") UmsRoleMenuRelationExample example);

    /**
     * updateByExample
     * @param row row
     * @param example example
     * @return int
     */
    int updateByExample(@Param("row") UmsRoleMenuRelation row, @Param("example") UmsRoleMenuRelationExample example);

    /**
     * updateByPrimaryKeySelective
     * @param row row
     * @return int
     */
    int updateByPrimaryKeySelective(UmsRoleMenuRelation row);

    /**
     * updateByPrimaryKey
     * @param row row
     * @return int
     */
    int updateByPrimaryKey(UmsRoleMenuRelation row);
}