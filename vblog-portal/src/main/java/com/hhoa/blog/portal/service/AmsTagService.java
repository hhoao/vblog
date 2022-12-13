package com.hhoa.blog.portal.service;

import com.hhoa.blog.mgb.model.AmsComment;
import com.hhoa.blog.mgb.model.AmsTag;
import com.hhoa.blog.portal.bean.PageInfo;

import java.util.List;

/**
 * @author hhoa
 * @since 2022/12/13
 **/

public interface AmsTagService {
    List<AmsTag> list(AmsTag tagParams, PageInfo pageInfo);
}
