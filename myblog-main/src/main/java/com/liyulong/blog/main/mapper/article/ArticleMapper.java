package com.liyulong.blog.main.mapper.article;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.liyulong.blog.main.pojo.article.Article;
import com.liyulong.blog.main.pojo.article.vo.ArticleVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 文章 Mapper 接口
 * </p>
 *
 * @author lihanlu
 * @since 2020-03-24
 */
@Mapper
public interface ArticleMapper extends BaseMapper<Article> {

    /**
     * 查询列表
     *
     * @param page
     * @param params
     * @return
     */
    List<ArticleVO> listArticleVo(Page<ArticleVO> page, @Param("params") Map<String, Object> params);

    /**
     * 根据条件查询分页
     * @param page
     * @param params
     * @return
     */
    List<ArticleVO> queryPageCondition(Page<ArticleVO> page, @Param("params") Map<String, Object> params);

    /**
     * 更新阅读记录
     * @param id
     */
    void updateReadNum(Integer id);

    /**
     * 获取简单的对象
     * @param articleId
     * @return
     */
    ArticleVO getSimpleArticleVo(Integer articleId);

    /**
     * 更新点赞记录
     * @param parseInt
     */
    void updateLikeNum(int parseInt);

    /**
     * 判断类别下是否有文章
     * @param categoryId
     * @return
     */
    int checkByCategory(Integer categoryId);

    List<String> getCategoryId();

    //获取上一片
    Article getPreById(Integer articleId);

    //获取下一篇
    Article getNextById(Integer articleId);
}
