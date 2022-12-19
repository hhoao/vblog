package com.hhoa.vblog.admin.service;

import com.hhoa.vblog.admin.bean.PageInfo;
import com.hhoa.vblog.mgb.model.AmsTag;

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

    /**
     * Add tag.
     *
     * @param tagParam the tag param
     */
    void addTag(AmsTag tagParam);

    /**
     * Delete tag.
     *
     * @param tagId the tag id
     */
    void deleteTag(Integer tagId);

    /**
     * Update tag.
     *
     * @param tagParam the tag param
     */
    void updateTag(AmsTag tagParam);
}
