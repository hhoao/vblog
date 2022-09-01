package com.hhoa.blog.mgb.mapper;

import com.hhoa.blog.mgb.model.UmsAdministrator;
import com.hhoa.blog.mgb.model.UmsAdministratorExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface UmsAdministratorMapper {
    /**
     * countByExample
     * @param example example
     * @return long
     */
    long countByExample(UmsAdministratorExample example);

    /**
     * deleteByExample
     * @param example example
     * @return int
     */
    int deleteByExample(UmsAdministratorExample example);

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
    int insert(UmsAdministrator row);

    /**
     * insertSelective
     * @param row row
     * @return int
     */
    int insertSelective(UmsAdministrator row);

    /**
     * selectByExample
     * @param example example
     * @return java.util.List<com.hhoa.blog.mgb.model.UmsAdministrator>
     */
    List<UmsAdministrator> selectByExample(UmsAdministratorExample example);

    /**
     * selectByPrimaryKey
     * @param id id
     * @return com.hhoa.blog.mgb.model.UmsAdministrator
     */
    UmsAdministrator selectByPrimaryKey(Long id);

    /**
     * updateByExampleSelective
     * @param row row
     * @param example example
     * @return int
     */
    int updateByExampleSelective(@Param("row") UmsAdministrator row, @Param("example") UmsAdministratorExample example);

    /**
     * updateByExample
     * @param row row
     * @param example example
     * @return int
     */
    int updateByExample(@Param("row") UmsAdministrator row, @Param("example") UmsAdministratorExample example);

    /**
     * updateByPrimaryKeySelective
     * @param row row
     * @return int
     */
    int updateByPrimaryKeySelective(UmsAdministrator row);

    /**
     * updateByPrimaryKey
     * @param row row
     * @return int
     */
    int updateByPrimaryKey(UmsAdministrator row);
}