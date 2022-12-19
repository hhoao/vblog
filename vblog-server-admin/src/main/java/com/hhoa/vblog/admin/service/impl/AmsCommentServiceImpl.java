package com.hhoa.vblog.admin.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.github.pagehelper.PageHelper;
import com.hhoa.vblog.admin.bean.PageInfo;
import com.hhoa.vblog.admin.dao.AmsCommentDao;
import com.hhoa.vblog.admin.service.AmsCommentService;
import com.hhoa.vblog.mgb.model.AmsComment;
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

    @Override
    public void addComment(AmsComment commentParam) {
        commentDao.insert(commentParam);
    }

    @Override
    public void updateComment(AmsComment commentParam) {
        commentDao.updateById(commentParam);
    }

    @Override
    public void deleteComment(Integer commentId) {
        commentDao.deleteById(commentId);
    }
}
