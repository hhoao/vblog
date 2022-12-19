package com.hhoa.vblog.portal.service;

import com.hhoa.vblog.mgb.model.AmsComment;
import com.hhoa.vblog.portal.bean.PageInfo;

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
}
