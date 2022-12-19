package com.hhoa.vblog.admin.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.github.pagehelper.PageHelper;
import com.hhoa.vblog.admin.bean.PageInfo;
import com.hhoa.vblog.admin.dao.AmsArticleDao;
import com.hhoa.vblog.admin.service.AmsArticleService;
import com.hhoa.vblog.mgb.model.AmsArticle;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * The type Ams article service.
 *
 * @author hhoa
 * @since 2022 /9/3
 */
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
    public void addArticle(AmsArticle articleParam) {
        articleDao.insert(articleParam);
    }

    @Override
    public void updateArticle(AmsArticle articleParam) {
        articleDao.updateById(articleParam);
    }

    @Override
    public void deleteArticle(Integer articleId) {
        articleDao.deleteById(articleId);
    }

    @Override
    public AmsArticle selectById(Long articleId) {
        return articleDao.selectById(articleId);
    }
}
