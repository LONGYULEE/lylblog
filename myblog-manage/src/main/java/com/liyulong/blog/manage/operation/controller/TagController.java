package com.liyulong.blog.manage.operation.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.liyulong.blog.manage.operation.service.TagService;
import com.liyulong.blog.main.common.constant.RedisCacheNames;
import com.liyulong.blog.main.common.enums.ModuleEnum;
import com.liyulong.blog.main.common.result.Result;
import com.liyulong.blog.main.common.result.ResultUtil;
import com.liyulong.blog.main.common.util.PageUtils;
import com.liyulong.blog.main.common.validator.ValidatorUtils;
import com.liyulong.blog.main.mapper.operation.TagLinkMapper;
import com.liyulong.blog.main.pojo.operation.Tag;
import com.liyulong.blog.main.pojo.operation.TagLink;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 标签 前端控制器
 * </p>
 *
 * @author lihanlu
 * @since 2020-03-24
 */
@RestController
@RequestMapping("/admin/operation/tag")
@CacheConfig(cacheNames = RedisCacheNames.TAG)
public class TagController {

    @Resource
    private TagService tagService;

    @Resource
    private TagLinkMapper tagLinkMapper;

    /**
     * 列表
     */
    @GetMapping("/list")
    @RequiresPermissions("operation:tag:list")
    public Result list(@RequestParam Map<String, Object> params){
        PageUtils page = tagService.queryPage(params);
        return ResultUtil.success(page);
    }

    @GetMapping("/select")
    @RequiresPermissions("operation:tag:list")
    public Result select(@RequestParam("type") Integer type){
        List<Tag> tagList = tagService.list(new QueryWrapper<Tag>().lambda()
                .eq(type != null,Tag::getType,type));
        return ResultUtil.success(tagList);
    }


    /**
     * 信息
     */
    @GetMapping("/info/{id}")
    @RequiresPermissions("operation:tag:info")
    public Result info(@PathVariable("id") String id){
        Tag tag = tagService.getById(id);
        return ResultUtil.success(tag);
    }

    /**
     * 保存
     */
    @PostMapping("/save")
    @RequiresPermissions("operation:tag:save")
    @CacheEvict(allEntries = true)
    public Result save(@RequestBody Tag tag){
        ValidatorUtils.validateEntity(tag);
        tagService.save(tag);
        return ResultUtil.success();
    }

    /**
     * 修改
     */
    @PutMapping("/update")
    @RequiresPermissions("operation:tag:update")
    @CacheEvict(allEntries = true)
    public Result update(@RequestBody Tag tag){
        ValidatorUtils.validateEntity(tag);
        tagService.updateById(tag);
        return ResultUtil.success();
    }

    /**
     * 删除
     */
    @DeleteMapping("/delete")
    @RequiresPermissions("operation:tag:delete")
    @CacheEvict(allEntries = true)
    public Result delete(@RequestBody String[] ids){
        for (String id : ids) {
            List<TagLink> tagLinkList = tagLinkMapper.selectList(new QueryWrapper<TagLink>().lambda().eq(TagLink::getTagId, id));
            if(!CollectionUtils.isEmpty(tagLinkList)) {
                TagLink tagLink = tagLinkList.get(0);
                if (tagLink.getType().equals(ModuleEnum.ARTICLE.getValue())) {
                    return ResultUtil.failure("该标签下有文章，无法删除");
                }
            }
        }
        tagService.removeByIds(Arrays.asList(ids));
        return ResultUtil.success();
    }

}
