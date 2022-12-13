package com.hhoa.blog.admin.service;

import com.hhoa.blog.admin.bean.PageInfo;
import com.hhoa.blog.mgb.model.AmsComment;

import java.util.List;

/**
 * @author hhoa
 * @since 2022/9/3
 **/

public interface AmsCommentService {
    List<AmsComment> list(AmsComment commentParams, PageInfo pageInfo);

    void addComment(AmsComment commentParam);

    void updateComment(AmsComment commentParam);

    void deleteComment(Integer commentId);
}
