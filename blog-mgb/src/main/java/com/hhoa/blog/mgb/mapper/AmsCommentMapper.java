package com.hhoa.blog.mgb.mapper;

import com.hhoa.blog.mgb.model.AmsComment;
import com.hhoa.blog.mgb.model.AmsCommentExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface AmsCommentMapper {
    /**
     * countByExample
     * @param example example
     * @return long
     */
    long countByExample(AmsCommentExample example);

    /**
     * deleteByExample
     * @param example example
     * @return int
     */
    int deleteByExample(AmsCommentExample example);

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
    int insert(AmsComment row);

    /**
     * insertSelective
     * @param row row
     * @return int
     */
    int insertSelective(AmsComment row);

    /**
     * selectByExample
     * @param example example
     * @return java.util.List<com.hhoa.blog.mgb.model.AmsComment>
     */
    List<AmsComment> selectByExample(AmsCommentExample example);

    /**
     * selectByPrimaryKey
     * @param id id
     * @return com.hhoa.blog.mgb.model.AmsComment
     */
    AmsComment selectByPrimaryKey(Long id);

    /**
     * updateByExampleSelective
     * @param row row
     * @param example example
     * @return int
     */
    int updateByExampleSelective(@Param("row") AmsComment row, @Param("example") AmsCommentExample example);

    /**
     * updateByExample
     * @param row row
     * @param example example
     * @return int
     */
    int updateByExample(@Param("row") AmsComment row, @Param("example") AmsCommentExample example);

    /**
     * updateByPrimaryKeySelective
     * @param row row
     * @return int
     */
    int updateByPrimaryKeySelective(AmsComment row);

    /**
     * updateByPrimaryKey
     * @param row row
     * @return int
     */
    int updateByPrimaryKey(AmsComment row);
}