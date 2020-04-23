package com.liyulong.blog.manage.article.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.liyulong.blog.manage.article.service.ArticleService;
import com.liyulong.blog.main.mapper.article.ArticleMapper;
import com.liyulong.blog.main.pojo.article.Article;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 文章 服务实现类
 * </p>
 *
 * @author lihanlu
 * @since 2020-03-24
 */
@Service
public class ArticleServiceImpl extends ServiceImpl<ArticleMapper, Article> implements ArticleService {

    @Override
    public boolean checkByCategory(Integer categoryId) {
        return false;
    }
}
