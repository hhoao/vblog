package com.hhoa.vblog.admin.service;

import com.hhoa.vblog.admin.bean.PageInfo;
import com.hhoa.vblog.mgb.model.AmsComment;

import java.util.List;

/**
 * The interface Ams comment service.
 *
 * @author hhoa
 * @since 2022 /9/3
 */
public interface AmsCommentService {
    /**
     * List list.
     *
     * @param commentParams the comment params
     * @param pageInfo      the page info
     * @return the list
     */
    List<AmsComment> list(AmsComment commentParams, PageInfo pageInfo);

    /**
     * Add comment.
     *
     * @param commentParam the comment param
     */
    void addComment(AmsComment commentParam);

    /**
     * Update comment.
     *
     * @param commentParam the comment param
     */
    void updateComment(AmsComment commentParam);

    /**
     * Delete comment.
     *
     * @param commentId the comment id
     */
    void deleteComment(Long commentId);
}
