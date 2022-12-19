package com.hhoa.vblog.portal.service;

import com.hhoa.vblog.mgb.model.AmsTag;
import com.hhoa.vblog.portal.bean.PageInfo;

import java.util.List;

/**
 * The interface Ams tag service.
 *
 * @author hhoa
 * @since 2022 /12/13
 */
public interface AmsTagService {
    /**
     * List list.
     *
     * @param tagParams the tag params
     * @param pageInfo  the page info
     * @return the list
     */
    List<AmsTag> list(AmsTag tagParams, PageInfo pageInfo);
}
