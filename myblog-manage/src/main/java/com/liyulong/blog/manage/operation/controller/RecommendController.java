package com.liyulong.blog.manage.operation.controller;


import com.liyulong.blog.main.common.constant.RedisCacheNames;
import com.liyulong.blog.main.common.result.Result;
import com.liyulong.blog.main.common.result.ResultUtil;
import com.liyulong.blog.main.common.util.PageUtils;
import com.liyulong.blog.main.common.validator.ValidatorUtils;
import com.liyulong.blog.main.pojo.operation.Recommend;
import com.liyulong.blog.main.pojo.operation.vo.RecommendVO;
import com.liyulong.blog.manage.operation.service.RecommendService;
import javafx.beans.binding.ObjectExpression;
import org.apache.ibatis.annotations.Delete;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.web.bind.annotation.*;

import org.springframework.stereotype.Controller;

import javax.annotation.Resource;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 推荐 前端控制器
 * </p>
 *
 * @author lihanlu
 * @since 2020-03-24
 */
@RestController
@RequestMapping("/admin/operation/recommend")
@CacheConfig(cacheNames = RedisCacheNames.RECOMMEND)
public class RecommendController {

    @Resource
    private RecommendService recommendService;

    /**
     * 列表
     * @param param title
     * @return
     */
    @GetMapping("/list")
    @RequiresPermissions("operation:recommend:list")
    public Result list(@RequestParam Map<String, Object> param){
        PageUtils page = recommendService.queryPage(param);
        return ResultUtil.success(page);
    }

    @GetMapping("select")
    @RequiresPermissions("operation:recommend:list")
    public Result select(@RequestParam(value = "id") String id){
        List<RecommendVO> list = recommendService.listSelect();
        return ResultUtil.success(list);
    }

    /**
     * 单个详情
     * @param recommendId
     * @return
     */
    @GetMapping("info")
    @RequiresPermissions("operation:recommend:info")
    public Result info(@RequestParam(value = "recommendId") String recommendId){
        Recommend recommend = recommendService.getById(recommendId);
        return ResultUtil.success(recommend);
    }

    //保存
    @PostMapping("/save")
    @RequiresPermissions("operation:recommend:save")
    @CacheEvict(allEntries = true)  //是否清空所有缓存
    public Result save(@RequestBody Recommend recommend){
        //校验
        ValidatorUtils.validateEntity(recommend);
        recommendService.save(recommend);
        return ResultUtil.success();
    }

    //修改
    @PostMapping("/udpate")
    @RequiresPermissions("operation:recommend:update")
    @CacheEvict(allEntries = true)  //是否清空所有缓存
    public Result update(@RequestBody Recommend recommend){
        //校验
        ValidatorUtils.validateEntity(recommend);
        recommendService.updateById(recommend);
        return ResultUtil.success();
    }

    //删除
    @DeleteMapping("/delete")
    @RequiresPermissions("operation:recommend:delete")
    @CacheEvict(allEntries = true)  //是否清空所有缓存
    public Result batchDelete(@RequestBody String[] recommendIds){
        recommendService.removeByIds(Arrays.asList(recommendIds));
        return ResultUtil.success();
    }

    //推荐置顶
    @GetMapping("/updateTop")
    @RequiresPermissions("operation:recommend:update")
    @CacheEvict(allEntries = true)  //是否清空所有缓存
    public Result updateTop(@RequestParam("id") Integer id){
        recommendService.updateTop(id);
        return ResultUtil.success();
    }

}
