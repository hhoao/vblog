package com.hhoa.vblog.portal.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.github.pagehelper.PageHelper;
import com.hhoa.vblog.mgb.model.AmsComment;
import com.hhoa.vblog.portal.bean.PageInfo;
import com.hhoa.vblog.portal.dao.AmsCommentDao;
import com.hhoa.vblog.portal.service.AmsCommentService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * The type Ams comment service.
 *
 * @author hhoa
 * @since 2022 /9/3
 */
@Service
@AllArgsConstructor
public class AmsCommentServiceImpl implements AmsCommentService {
    private AmsCommentDao commentDao;

    @Override
    public List<AmsComment> list(AmsComment commentParams, PageInfo pageInfo) {
        PageHelper.startPage(pageInfo);
        return commentDao.selectList(new QueryWrapper<>(commentParams));
    }
}