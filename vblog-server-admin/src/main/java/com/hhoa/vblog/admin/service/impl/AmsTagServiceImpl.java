package com.hhoa.vblog.admin.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.github.pagehelper.PageHelper;
import com.hhoa.vblog.admin.dao.AmsTagDao;
import com.hhoa.vblog.admin.service.AmsTagService;
import com.hhoa.vblog.mgb.model.AmsTag;
import com.hhoa.vblog.admin.bean.PageInfo;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * The type Ams tag service.
 *
 * @author hhoa
 * @since 2022 /12/13
 */
@AllArgsConstructor
@Service
public class AmsTagServiceImpl implements AmsTagService {
    private AmsTagDao amsTagDao;

    @Override
    public List<AmsTag> list(AmsTag tagParams, PageInfo pageInfo) {
        PageHelper.startPage(pageInfo);
        QueryWrapper<AmsTag> queryWrapper = new QueryWrapper<>();
        return amsTagDao.selectList(queryWrapper);
    }

    @Override
    public void addTag(AmsTag tagParam) {
        amsTagDao.insert(tagParam);
    }

    @Override
    public void deleteTag(Long tagId) {
        amsTagDao.deleteById(tagId);
    }

    @Override
    public void updateTag(AmsTag tagParam) {
        amsTagDao.updateById(tagParam);
    }
}
