package com.hhoa.blog.mgb.mapper;

import com.hhoa.blog.mgb.model.OmsFile;
import com.hhoa.blog.mgb.model.OmsFileExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface OmsFileMapper {
    /**
     * countByExample
     * @param example example
     * @return long
     */
    long countByExample(OmsFileExample example);

    /**
     * deleteByExample
     * @param example example
     * @return int
     */
    int deleteByExample(OmsFileExample example);

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
    int insert(OmsFile row);

    /**
     * insertSelective
     * @param row row
     * @return int
     */
    int insertSelective(OmsFile row);

    /**
     * selectByExample
     * @param example example
     * @return java.util.List<com.hhoa.blog.mgb.model.OmsFile>
     */
    List<OmsFile> selectByExample(OmsFileExample example);

    /**
     * selectByPrimaryKey
     * @param id id
     * @return com.hhoa.blog.mgb.model.OmsFile
     */
    OmsFile selectByPrimaryKey(Long id);

    /**
     * updateByExampleSelective
     * @param row row
     * @param example example
     * @return int
     */
    int updateByExampleSelective(@Param("row") OmsFile row, @Param("example") OmsFileExample example);

    /**
     * updateByExample
     * @param row row
     * @param example example
     * @return int
     */
    int updateByExample(@Param("row") OmsFile row, @Param("example") OmsFileExample example);

    /**
     * updateByPrimaryKeySelective
     * @param row row
     * @return int
     */
    int updateByPrimaryKeySelective(OmsFile row);

    /**
     * updateByPrimaryKey
     * @param row row
     * @return int
     */
    int updateByPrimaryKey(OmsFile row);
}