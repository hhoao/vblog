package com.hhoa.blog.mgb.mapper;

import com.hhoa.blog.mgb.model.UmsAccount;
import com.hhoa.blog.mgb.model.UmsAccountExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface UmsAccountMapper {
    /**
     * countByExample
     * @param example example
     * @return long
     */
    long countByExample(UmsAccountExample example);

    /**
     * deleteByExample
     * @param example example
     * @return int
     */
    int deleteByExample(UmsAccountExample example);

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
    int insert(UmsAccount row);

    /**
     * insertSelective
     * @param row row
     * @return int
     */
    int insertSelective(UmsAccount row);

    /**
     * selectByExample
     * @param example example
     * @return java.util.List<com.hhoa.blog.mgb.model.UmsAccount>
     */
    List<UmsAccount> selectByExample(UmsAccountExample example);

    /**
     * selectByPrimaryKey
     * @param id id
     * @return com.hhoa.blog.mgb.model.UmsAccount
     */
    UmsAccount selectByPrimaryKey(Long id);

    /**
     * updateByExampleSelective
     * @param row row
     * @param example example
     * @return int
     */
    int updateByExampleSelective(@Param("row") UmsAccount row, @Param("example") UmsAccountExample example);

    /**
     * updateByExample
     * @param row row
     * @param example example
     * @return int
     */
    int updateByExample(@Param("row") UmsAccount row, @Param("example") UmsAccountExample example);

    /**
     * updateByPrimaryKeySelective
     * @param row row
     * @return int
     */
    int updateByPrimaryKeySelective(UmsAccount row);

    /**
     * updateByPrimaryKey
     * @param row row
     * @return int
     */
    int updateByPrimaryKey(UmsAccount row);
}