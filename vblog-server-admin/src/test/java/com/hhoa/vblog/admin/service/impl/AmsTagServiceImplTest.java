package com.hhoa.vblog.admin.service.impl;

import com.hhoa.vblog.admin.TransactionTest;
import com.hhoa.vblog.admin.bean.PageInfo;
import com.hhoa.vblog.admin.service.AmsTagService;
import com.hhoa.vblog.mgb.model.AmsTag;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * AmsTagServiceImplTest.
 *
 * @author hhoa
 * @since 2022/12/20
 **/

class AmsTagServiceImplTest extends TransactionTest {
    @Autowired
    AmsTagService tagService;

    @Test
    void list() {
        AmsTag amsTag = new AmsTag();
        amsTag.setLabel("testLabel");
        tagService.addTag(amsTag);
        List<AmsTag> list = tagService.list(null, new PageInfo(1, 0));
        Assertions.assertTrue(list.size() > 0);
    }

    @Test
    void addTag() {
        AmsTag amsTag = new AmsTag();
        Assertions.assertThrows(Exception.class, () -> tagService.addTag(amsTag));
        amsTag.setLabel("testLabel");
        tagService.addTag(amsTag);
        List<AmsTag> list = tagService.list(null, new PageInfo(1, 0));
        Assertions.assertTrue(list.size() > 0);
    }

    @Test
    void deleteTag() {
        AmsTag amsTag = new AmsTag();
        amsTag.setLabel("testLabel");
        tagService.addTag(amsTag);
        List<AmsTag> list = tagService.list(null, new PageInfo(1, 0));
        AmsTag amsTag1 = list.get(0);
        tagService.deleteTag(amsTag1.getId());
    }

    @Test
    void updateTag() {
    }
}
