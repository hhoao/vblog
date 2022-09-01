package com.hhoa.blog.mgb.mapper;

import com.hhoa.blog.mgb.model.UmsResource;
import com.hhoa.blog.mgb.model.UmsResourceExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface UmsResourceMapper {
    /**
     * countByExample
     * @param example example
     * @return long
     */
    long countByExample(UmsResourceExample example);

    /**
     * deleteByExample
     * @param example example
     * @return int
     */
    int deleteByExample(UmsResourceExample example);

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
    int insert(UmsResource row);

    /**
     * insertSelective
     * @param row row
     * @return int
     */
    int insertSelective(UmsResource row);

    /**
     * selectByExample
     * @param example example
     * @return java.util.List<com.hhoa.blog.mgb.model.UmsResource>
     */
    List<UmsResource> selectByExample(UmsResourceExample example);

    /**
     * selectByPrimaryKey
     * @param id id
     * @return com.hhoa.blog.mgb.model.UmsResource
     */
    UmsResource selectByPrimaryKey(Long id);

    /**
     * updateByExampleSelective
     * @param row row
     * @param example example
     * @return int
     */
    int updateByExampleSelective(@Param("row") UmsResource row, @Param("example") UmsResourceExample example);

    /**
     * updateByExample
     * @param row row
     * @param example example
     * @return int
     */
    int updateByExample(@Param("row") UmsResource row, @Param("example") UmsResourceExample example);

    /**
     * updateByPrimaryKeySelective
     * @param row row
     * @return int
     */
    int updateByPrimaryKeySelective(UmsResource row);

    /**
     * updateByPrimaryKey
     * @param row row
     * @return int
     */
    int updateByPrimaryKey(UmsResource row);
}