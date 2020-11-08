package com.liyulong.blog.index.article.service.impl;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.liyulong.blog.index.article.service.ArticleService;
import com.liyulong.blog.main.common.enums.ModuleEnum;
import com.liyulong.blog.main.common.util.PageUtils;
import com.liyulong.blog.main.common.util.Query;
import com.liyulong.blog.main.mapper.article.ArticleMapper;
import com.liyulong.blog.main.pojo.article.Article;
import com.liyulong.blog.main.pojo.article.vo.ArticleVO;
import com.liyulong.blog.main.pojo.operation.Tag;
import com.liyulong.blog.manage.operation.service.TagService;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

@Service("ArticleIndexService")
public class ArticleServiceImpl extends ServiceImpl<ArticleMapper, Article> implements ArticleService {

    @Resource
    private TagService tagService;

    @Override
    public PageUtils queryPageCondition(Map<String, Object> params) {
        Page<ArticleVO> page = new Query<ArticleVO>(params).getPage();
        List<ArticleVO> articleList = baseMapper.queryPageCondition(page, params);
        articleList.forEach(item -> {
            List<Tag> tags = tagService.listByLinkId(item.getId(), ModuleEnum.ARTICLE.getValue());
            item.setTagList(tags);
        });
        // 封装ArticleVo
        page.setRecords(articleList);
        return new PageUtils(page);
    }

    @Override
    public ArticleVO getArticleVo(Integer articleId) {
        Article article = baseMapper.selectById(articleId);
        ArticleVO articleVo = new ArticleVO();
        BeanUtils.copyProperties(article,articleVo);
        articleVo.setTagList(tagService.listByLinkId(articleId, ModuleEnum.ARTICLE.getValue()));
        return articleVo;
    }

    @Override
    public ArticleVO getSimpleArticleVo(Integer articleId) {
        ArticleVO articleVo = baseMapper.getSimpleArticleVo(articleId);
        articleVo.setTagList(tagService.listByLinkId(articleId,ModuleEnum.ARTICLE.getValue()));
        return articleVo;
    }

    @Override
    public List<String> getCategoryId() {
        List<String> categoryId = baseMapper.getCategoryId();
        List<String> list = new ArrayList<>();
        categoryId.forEach(item -> {
            if(item != null){
                String[] split = item.split(",");
                list.addAll(Arrays.asList(split));
            }else {
                list.add(null);
            }
        });
        return list;
    }

    @Override
    public PageUtils getArticleByCategoyId(String categoryId) {

        return null;
    }

}
