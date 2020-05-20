package com.liyulong.blog.index.article.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.liyulong.blog.index.article.service.ArticleService;
import com.liyulong.blog.main.common.util.PageUtils;
import com.liyulong.blog.main.mapper.article.ArticleMapper;
import com.liyulong.blog.main.pojo.article.Article;
import com.liyulong.blog.main.pojo.article.vo.ArticleVO;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service("ArticleIndexService")
public class ArticleServiceImpl extends ServiceImpl<ArticleMapper, Article> implements ArticleService {
    @Override
    public PageUtils queryPageCondition(Map<String, Object> params) {
        return null;
    }

    @Override
    public ArticleVO getArticleVo(Integer articleId) {
        return null;
    }

    @Override
    public ArticleVO getSimpleArticleVo(Integer articleId) {
        return null;
    }
}
