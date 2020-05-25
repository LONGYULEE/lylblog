package com.liyulong.blog.index.article.controller;

import com.liyulong.blog.index.article.service.ArticleService;
import com.liyulong.blog.main.common.constant.RedisCacheNames;
import com.liyulong.blog.main.common.result.Result;
import com.liyulong.blog.main.common.result.ResultUtil;
import com.liyulong.blog.main.common.util.PageUtils;
import com.liyulong.blog.main.pojo.article.vo.ArticleVO;
import io.swagger.annotations.ApiOperation;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.Map;

@ApiOperation("ArticleIndexController")
@RestController("articleIndexController")
@CacheConfig(cacheNames = {RedisCacheNames.ARTICLE})
public class ArticleController {

    @Resource
    private ArticleService articleService;

    @GetMapping("/article/{articleId}")
//    @LogView(type = "article")
    public Result getArticle(@PathVariable Integer articleId){
        ArticleVO article = articleService.getArticleVo(articleId);
        return ResultUtil.success(article);
    }

    @PutMapping("/article/like/{id}")
//    @LogLike(type = "article")
    public Result likeArticle(@PathVariable Integer id) {
        return ResultUtil.success();
    }

    @GetMapping("/articles")
    @Cacheable
    public Result list(@RequestParam Map<String, Object> params){
        PageUtils page = articleService.queryPageCondition(params);
        return ResultUtil.success(page);
    }

}
