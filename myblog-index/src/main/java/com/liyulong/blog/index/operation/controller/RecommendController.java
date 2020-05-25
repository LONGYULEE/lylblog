package com.liyulong.blog.index.operation.controller;

import com.liyulong.blog.index.operation.service.RecommendService;
import com.liyulong.blog.main.common.constant.RedisCacheNames;
import com.liyulong.blog.main.common.result.Result;
import com.liyulong.blog.main.common.result.ResultUtil;
import com.liyulong.blog.main.pojo.operation.vo.RecommendVO;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

@RestController("RecommendIndexController")
@CacheConfig(cacheNames = RedisCacheNames.RECOMMEND)
@RequestMapping("/operation")
public class RecommendController {

    @Resource
    private RecommendService recommendService;

    @RequestMapping("/recommends")
    @Cacheable(key = "'RECOMMEND'")
    public Result listRecommend() {
        List<RecommendVO> recommendList = recommendService.listRecommendVo();
        return ResultUtil.success(recommendList);
    }

    @RequestMapping("/hotReads")
    @Cacheable(key = "'HOTREAD'")
    public Result listHotRead () {
        List<RecommendVO> hotReadList = recommendService.listHotRead();
        return ResultUtil.success(hotReadList);
    }

}
