package com.hhoa.blog.portal.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.github.pagehelper.PageHelper;
import com.hhoa.blog.mgb.model.AmsTag;
import com.hhoa.blog.portal.bean.PageInfo;
import com.hhoa.blog.portal.dao.AmsTagDao;
import com.hhoa.blog.portal.service.AmsTagService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author hhoa
 * @since 2022/12/13
 **/

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
}
