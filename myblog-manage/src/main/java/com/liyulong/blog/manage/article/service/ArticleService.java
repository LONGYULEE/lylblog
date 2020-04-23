package com.liyulong.blog.manage.article.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.liyulong.blog.main.pojo.article.Article;

/**
 * <p>
 * 文章 服务类
 * </p>
 *
 * @author lihanlu
 * @since 2020-03-24
 */
public interface ArticleService extends IService<Article> {

    boolean checkByCategory(Integer categoryId);

}
