package com.hhoa.blog.mgb.mapper;

import com.hhoa.blog.mgb.model.UmsRole;
import com.hhoa.blog.mgb.model.UmsRoleExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface UmsRoleMapper {
    /**
     * countByExample
     * @param example example
     * @return long
     */
    long countByExample(UmsRoleExample example);

    /**
     * deleteByExample
     * @param example example
     * @return int
     */
    int deleteByExample(UmsRoleExample example);

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
    int insert(UmsRole row);

    /**
     * insertSelective
     * @param row row
     * @return int
     */
    int insertSelective(UmsRole row);

    /**
     * selectByExample
     * @param example example
     * @return java.util.List<com.hhoa.blog.mgb.model.UmsRole>
     */
    List<UmsRole> selectByExample(UmsRoleExample example);

    /**
     * selectByPrimaryKey
     * @param id id
     * @return com.hhoa.blog.mgb.model.UmsRole
     */
    UmsRole selectByPrimaryKey(Long id);

    /**
     * updateByExampleSelective
     * @param row row
     * @param example example
     * @return int
     */
    int updateByExampleSelective(@Param("row") UmsRole row, @Param("example") UmsRoleExample example);

    /**
     * updateByExample
     * @param row row
     * @param example example
     * @return int
     */
    int updateByExample(@Param("row") UmsRole row, @Param("example") UmsRoleExample example);

    /**
     * updateByPrimaryKeySelective
     * @param row row
     * @return int
     */
    int updateByPrimaryKeySelective(UmsRole row);

    /**
     * updateByPrimaryKey
     * @param row row
     * @return int
     */
    int updateByPrimaryKey(UmsRole row);
}