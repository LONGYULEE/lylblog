package com.liyulong.blog.index.operation.controller;

import com.liyulong.blog.index.operation.service.TagService;
import com.liyulong.blog.main.common.constant.RedisCacheNames;
import com.liyulong.blog.main.common.result.ResultMap;
import com.liyulong.blog.main.pojo.operation.vo.TagVO;
import io.swagger.annotations.ApiOperation;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

@ApiOperation("TagIndexController")
@RestController("TagIndexController")
@RequestMapping("/operation")
@CacheConfig(cacheNames = RedisCacheNames.TAG)
public class TagController {

    @Resource
    private TagService tagService;

    @GetMapping("/tags")
    @Cacheable
    public ResultMap listTag(){
        List<TagVO> tagVOList = tagService.listTagVo();
        return ResultMap.ok().put("tagVOList",tagVOList);
    }

}
