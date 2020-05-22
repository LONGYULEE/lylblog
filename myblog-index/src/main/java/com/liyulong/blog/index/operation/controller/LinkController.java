package com.liyulong.blog.index.operation.controller;

import com.liyulong.blog.index.operation.service.LinkService;
import com.liyulong.blog.main.common.constant.RedisCacheNames;
import com.liyulong.blog.main.common.result.Result;
import com.liyulong.blog.main.common.result.ResultMap;
import com.liyulong.blog.main.common.result.ResultUtil;
import com.liyulong.blog.main.pojo.operation.Link;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

@RestController
@CacheConfig(cacheNames = RedisCacheNames.LINK)
@RequestMapping("IndexLink")
public class LinkController {

    @Resource
    private LinkService linkService;

    @GetMapping("/links")
    @Cacheable
    public Result listLink(){
        List<Link> linkList = linkService.listLink();
        return ResultUtil.success(linkList);
    }

}
