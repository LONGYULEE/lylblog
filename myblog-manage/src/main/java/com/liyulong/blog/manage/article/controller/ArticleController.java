package com.liyulong.blog.manage.article.controller;


import com.liyulong.blog.main.common.constant.RedisCacheNames;
import com.liyulong.blog.main.common.enums.ModuleEnum;
import com.liyulong.blog.main.common.mq.annotation.RefreshEsMqSender;
import com.liyulong.blog.main.common.result.Result;
import com.liyulong.blog.main.common.result.ResultUtil;
import com.liyulong.blog.main.common.util.PageUtils;
import com.liyulong.blog.main.common.util.QiNiuUtil;
import com.liyulong.blog.main.common.validator.ValidatorUtils;
import com.liyulong.blog.main.pojo.article.Article;
import com.liyulong.blog.main.pojo.article.dto.ArticleDTO;
import com.liyulong.blog.manage.article.service.ArticleService;
import com.liyulong.blog.manage.operation.service.RecommendService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import org.springframework.stereotype.Controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.Map;
import java.util.Set;

/**
 * <p>
 * 文章 前端控制器
 * </p>
 *
 * @author lihanlu
 * @since 2020-03-24
 */
@RestController
@RequestMapping("/admin/article")
@CacheConfig(cacheNames ={RedisCacheNames.RECOMMEND,RedisCacheNames.TAG,RedisCacheNames.ARTICLE,RedisCacheNames.TIMELINE})
public class ArticleController {

    /**
     * 获取七牛云上传token
     * @return token
     */
    @GetMapping("/getUpToken")
    public Result getUpToken(){
        String upToken = QiNiuUtil.getUpToken();
        return ResultUtil.success(upToken);
    }

    /**
     * 删除七牛云中的文件
     * @param key 文件名
     * @return 是否删除成功
     */
    @GetMapping("/deleteFile")
    public Result deleteFile(@RequestParam String key){
        Boolean b = QiNiuUtil.deleteFile(key);
        return ResultUtil.success(b);
    }

    @Resource
    private ArticleService articleService;

    @Resource
    private RecommendService recommendService;

    @Resource
    private RedisTemplate<String,Object> redisTemplate;

    @GetMapping("/list")
    @RequiresPermissions("article:list")
    public Result listArticle(@RequestParam Map<String, Object> params) {
        PageUtils page = articleService.queryPage(params);
        return ResultUtil.success(page);
    }

    @GetMapping("/info/{articleId}")
    @RequiresPermissions("article:list")
    public Result info(@PathVariable Integer articleId) {
        ArticleDTO article = articleService.getArticle(articleId);
        return ResultUtil.success(article);
    }

    @PostMapping("/save")
    @RequiresPermissions("article:save")
    @CacheEvict(allEntries = true)
    @RefreshEsMqSender(sender = "myblog-manage-saveArticle")
    public Result saveArticle(@RequestBody ArticleDTO article){
        ValidatorUtils.validateEntity(article);
        articleService.saveArticle(article);
        return ResultUtil.success();
    }

    @PutMapping("/update")
    @RequiresPermissions("article:update")
    @CacheEvict(allEntries = true)
    @RefreshEsMqSender(sender = "myblog-manage-updateArticle")
    public Result updateArticle(@RequestBody ArticleDTO article){
        ValidatorUtils.validateEntity(article);
        articleService.updateArticle(article);
        return ResultUtil.success();
    }

    @PutMapping("/update/status")
    @RequiresPermissions("article:update")
    @CacheEvict(allEntries = true)
    @RefreshEsMqSender(sender = "myblog-manage-updateStatus")
    public Result updateStatus(@RequestBody Article article) {
        articleService.updateById(article);
        return ResultUtil.success();
    }


    @DeleteMapping("/delete")
    @RequiresPermissions("article:delete")
    @Transactional(rollbackFor = Exception.class)
    @CacheEvict(allEntries = true)
    @RefreshEsMqSender(sender = "myblog-manage-deleteArticle")
    public Result deleteBatch(@RequestBody Integer[] articleIds) {
        recommendService.deleteBatchByLinkId(articleIds, ModuleEnum.ARTICLE.getValue());
        articleService.deleteBatch(articleIds);
        return ResultUtil.success();
    }

    @DeleteMapping("/cache/refresh")
    @RequiresPermissions("article:cache:refresh")
    public Result flush() {
        Set<String> keys = redisTemplate.keys(RedisCacheNames.PROFIX+"*");
        redisTemplate.delete(keys);
        return ResultUtil.success();
    }

}
