package com.hhoa.blog.portal.service;

import com.hhoa.blog.mgb.model.AmsComment;
import com.hhoa.blog.portal.bean.PageInfo;

import java.util.List;

/**
 * @author hhoa
 * @since 2022/9/3
 **/

public interface AmsCommentService {
    List<AmsComment> list(AmsComment commentParams, PageInfo pageInfo);
}
