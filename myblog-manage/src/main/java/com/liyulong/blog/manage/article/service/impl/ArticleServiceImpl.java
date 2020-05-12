package com.liyulong.blog.manage.article.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.liyulong.blog.main.common.enums.ModuleEnum;
import com.liyulong.blog.main.common.util.PageUtils;
import com.liyulong.blog.main.common.util.Query;
import com.liyulong.blog.main.pojo.article.dto.ArticleDTO;
import com.liyulong.blog.main.pojo.article.vo.ArticleVO;
import com.liyulong.blog.main.pojo.operation.Category;
import com.liyulong.blog.manage.article.service.ArticleService;
import com.liyulong.blog.main.mapper.article.ArticleMapper;
import com.liyulong.blog.main.pojo.article.Article;
import com.liyulong.blog.manage.operation.service.CategoryService;
import com.liyulong.blog.manage.operation.service.TagService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Optional;

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

    @Autowired
    private TagService tagService;

    @Autowired
    private CategoryService categoryService;

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        Page<ArticleVO> page = new Query<ArticleVO>(params).getPage();
        List<ArticleVO> articleList = baseMapper.listArticleVo(page, params);
        // 查询所有分类
        List<Category> categoryList = categoryService.list(new QueryWrapper<Category>().lambda().eq(Category::getType, ModuleEnum.ARTICLE.getValue()));
        // 封装ArticleVo
        Optional.ofNullable(articleList).ifPresent((articleVos ->
                articleVos.forEach(articleVo -> {
                    // 设置类别
                    articleVo.setCategoryListStr(categoryService.renderCategoryArr(articleVo.getCategoryId(),categoryList));
                    // 设置标签列表
                    articleVo.setTagList(tagService.listByLinkId(articleVo.getId(),ModuleEnum.ARTICLE.getValue()));
                })));
        page.setRecords(articleList);
        return new PageUtils(page);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void saveArticle(ArticleDTO blogArticle) {
        baseMapper.insert(blogArticle);
        tagService.saveTagAndNew(blogArticle.getTagList(),blogArticle.getId(),ModuleEnum.ARTICLE.getValue());
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void deleteBatch(Integer[] articleIds) {
        //先删除博文标签多对多关联
        Arrays.stream(articleIds).forEach(articleId -> {
            tagService.deleteTagLink(articleId,ModuleEnum.ARTICLE.getValue());
        });
        this.baseMapper.deleteBatchIds(Arrays.asList(articleIds));
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void updateArticle(ArticleDTO blogArticle) {
        // 删除多对多所属标签
        tagService.deleteTagLink(blogArticle.getId(),ModuleEnum.ARTICLE.getValue());
        // 更新所属标签
        tagService.saveTagAndNew(blogArticle.getTagList(),blogArticle.getId(), ModuleEnum.ARTICLE.getValue());
        // 更新博文
        baseMapper.updateById(blogArticle);
    }

    @Override
    public ArticleDTO getArticle(Integer articleId) {
        ArticleDTO articleDto = new ArticleDTO();
        Article article = this.baseMapper.selectById(articleId);
        BeanUtils.copyProperties(article,articleDto);
        // 查询所属标签
        articleDto.setTagList(tagService.listByLinkId(articleId,ModuleEnum.ARTICLE.getValue()));
        return articleDto;
    }

    @Override
    public boolean checkByCategory(Integer categoryId) {
        return baseMapper.checkByCategory(categoryId) > 0;
    }
}
