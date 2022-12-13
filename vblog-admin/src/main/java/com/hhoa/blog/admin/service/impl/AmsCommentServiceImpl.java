package com.hhoa.blog.admin.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.github.pagehelper.PageHelper;
import com.hhoa.blog.admin.bean.PageInfo;
import com.hhoa.blog.admin.dao.AmsCommentDao;
import com.hhoa.blog.admin.service.AmsCommentService;
import com.hhoa.blog.mgb.model.AmsComment;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author hhoa
 * @since 2022/9/3
 **/

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
