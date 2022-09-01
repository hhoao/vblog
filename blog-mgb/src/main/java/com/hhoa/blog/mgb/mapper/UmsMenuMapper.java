package com.hhoa.blog.mgb.mapper;

import com.hhoa.blog.mgb.model.UmsMenu;
import com.hhoa.blog.mgb.model.UmsMenuExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface UmsMenuMapper {
    /**
     * countByExample
     * @param example example
     * @return long
     */
    long countByExample(UmsMenuExample example);

    /**
     * deleteByExample
     * @param example example
     * @return int
     */
    int deleteByExample(UmsMenuExample example);

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
    int insert(UmsMenu row);

    /**
     * insertSelective
     * @param row row
     * @return int
     */
    int insertSelective(UmsMenu row);

    /**
     * selectByExample
     * @param example example
     * @return java.util.List<com.hhoa.blog.mgb.model.UmsMenu>
     */
    List<UmsMenu> selectByExample(UmsMenuExample example);

    /**
     * selectByPrimaryKey
     * @param id id
     * @return com.hhoa.blog.mgb.model.UmsMenu
     */
    UmsMenu selectByPrimaryKey(Long id);

    /**
     * updateByExampleSelective
     * @param row row
     * @param example example
     * @return int
     */
    int updateByExampleSelective(@Param("row") UmsMenu row, @Param("example") UmsMenuExample example);

    /**
     * updateByExample
     * @param row row
     * @param example example
     * @return int
     */
    int updateByExample(@Param("row") UmsMenu row, @Param("example") UmsMenuExample example);

    /**
     * updateByPrimaryKeySelective
     * @param row row
     * @return int
     */
    int updateByPrimaryKeySelective(UmsMenu row);

    /**
     * updateByPrimaryKey
     * @param row row
     * @return int
     */
    int updateByPrimaryKey(UmsMenu row);
}