package com.liyulong.blog.index.operation.controller;

import com.liyulong.blog.index.operation.service.CategoryService;
import com.liyulong.blog.main.common.constant.RedisCacheNames;
import com.liyulong.blog.main.common.result.Result;
import com.liyulong.blog.main.common.result.ResultUtil;
import com.liyulong.blog.main.pojo.operation.Category;
import com.liyulong.blog.main.pojo.operation.Number;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController("categoryPortalController")
@CacheConfig(cacheNames = RedisCacheNames.CATEGORY)
@RequestMapping("/operation")
public class CategoryController {

    @Resource
    private CategoryService categoryService;

    @GetMapping("/categories")
    @Cacheable
    public Result listCategory(@RequestParam Map<String,Object> params) {
        List<Category> categoryList = categoryService.listCategory(params);
        return ResultUtil.success(categoryList);
    }

    @GetMapping("/getCategoyNum")
    public Result getCategoryNumber(){
        Number number = categoryService.getNumbers();
        return ResultUtil.success(number);
    }

    @GetMapping("/getCategoryNumberAndName")
//    @Cacheable
    public Result getCategoryNumberAndName(){
        HashMap<String, Integer> map = categoryService.getNumberAndName();
        return ResultUtil.success(map);
    }

}
