package com.hhoa.blog.portal.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.github.pagehelper.PageHelper;
import com.hhoa.blog.mgb.model.AmsArticle;
import com.hhoa.blog.portal.bean.PageInfo;
import com.hhoa.blog.portal.dao.AmsArticleDao;
import com.hhoa.blog.portal.service.AmsArticleService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author hhoa
 * @since 2022/9/3
 **/

@AllArgsConstructor
@Service
public class AmsArticleServiceImpl implements AmsArticleService {
    private AmsArticleDao articleDao;
    @Override
    public List<AmsArticle> list(AmsArticle articleParams, PageInfo pageInfo) {
        PageHelper.startPage(pageInfo);
        return articleDao.selectList(new QueryWrapper<>(articleParams));
    }
    @Override
    public AmsArticle selectById(Long articleId) {
        return articleDao.selectById(articleId);
    }
}
