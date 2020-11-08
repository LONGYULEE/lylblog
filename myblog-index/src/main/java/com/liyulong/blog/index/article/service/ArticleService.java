package com.liyulong.blog.index.article.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.liyulong.blog.main.common.util.PageUtils;
import com.liyulong.blog.main.pojo.article.Article;
import com.liyulong.blog.main.pojo.article.vo.ArticleVO;

import java.util.List;
import java.util.Map;

public interface ArticleService extends IService<Article> {

    /**
     * 分页分类获取列表
     * @param params
     * @return
     */
    PageUtils queryPageCondition(Map<String,Object> params);

    /**
     * 获取articleVo对象
     * @param articleId
     * @return
     */
    ArticleVO getArticleVo(Integer articleId);

    /**
     * 获取简单article对象
     * @param articleId
     * @return
     */
    ArticleVO getSimpleArticleVo(Integer articleId);

    /**
     * 获取文章的分类id
     * @return
     */
    List<String> getCategoryId();

    PageUtils getArticleByCategoyId(String categoryId);
}
