package com.liyulong.blog.manage.operation.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.liyulong.blog.manage.article.service.ArticleService;
import com.liyulong.blog.manage.operation.service.CategoryService;
import com.liyulong.blog.main.common.constant.RedisCacheNames;
import com.liyulong.blog.main.common.enums.CategoryRankEnum;
import com.liyulong.blog.main.common.exception.MyException;
import com.liyulong.blog.main.common.result.Result;
import com.liyulong.blog.main.common.result.ResultUtil;
import com.liyulong.blog.main.common.validator.ValidatorUtils;
import com.liyulong.blog.main.pojo.operation.Category;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author lihanlu
 * @since 2020-03-24
 */
@RestController
@RequestMapping("/admin/operation/category")
@CacheConfig(cacheNames = RedisCacheNames.CATEGORY)
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    @Autowired
    private ArticleService articleService;

    //列表
    @GetMapping("/list")
    @RequiresPermissions("operation:category:list")
    public Result list(@RequestParam Map<String,Object> map){
        List<Category> list = categoryService.findAllWithParentName(map);
        return ResultUtil.success(list);
    }

    //树状列表
    @GetMapping("/select")
    @RequiresPermissions("operation:category:list")
    public Result select(@RequestParam(required = false) Integer type){
        List<Category> categoryList = categoryService.list(new QueryWrapper<Category>().lambda().
                eq(type!=null,Category::getType,type));
        //添加顶级分类
        Category root = new Category();
        root.setId(-1);
        root.setName("根目录");
        root.setParentId(-1);
        categoryList.add(root);

        return ResultUtil.success(categoryList);
    }

    //信息
    @GetMapping("/info")
    @RequiresPermissions("operation:category:info")
    public Result info(@RequestParam("id") Integer id){
        Category category = categoryService.getById(id);
        return ResultUtil.success(category);
    }

    //保存
    @RequestMapping("/save")
    @RequiresPermissions("operation:category:save")
    @CacheEvict(allEntries = true)
    public Result save(@RequestBody Category category){
        // 数据校验
        ValidatorUtils.validateEntity(category);
        verifyCategory(category);
        categoryService.save(category);
        return ResultUtil.success();
    }

    //数据校验
    private void verifyCategory(Category category) {
        //上级分类级别
        int parentRank = CategoryRankEnum.ROOT.getValue();
        if (category.getParentId() != CategoryRankEnum.FIRST.getValue()
                && category.getParentId() != CategoryRankEnum.ROOT.getValue()) {
            Category parentCategory = categoryService.getById(category.getParentId());
            parentRank = parentCategory.getRank();
        }
        // 一级
        if (category.getRank() == CategoryRankEnum.FIRST.getValue()) {
            if (category.getParentId() != CategoryRankEnum.ROOT.getValue()){
                throw new MyException("上级目录只能为根目录");
            }
        }
        //二级
        if (category.getRank() == CategoryRankEnum.SECOND.getValue()) {
            if (parentRank != CategoryRankEnum.FIRST.getValue()) {
                throw new MyException("上级目录只能为一级类型");
            }
        }
        //三级
        if (category.getRank() == CategoryRankEnum.THIRD.getValue()) {
            if (parentRank != CategoryRankEnum.SECOND.getValue()) {
                throw new MyException("上级目录只能为二级类型");
            }
        }
    }

    //修改
    @PostMapping("/update")
    @RequiresPermissions("operation:category:update")
    @CacheEvict(allEntries = true)
    public Result update(@RequestBody Category category){
        categoryService.updateById(category);
        return ResultUtil.success();
    }

    //删除
    @DeleteMapping("/delete/{id}")
    @RequiresPermissions("operation:category:delete")
    @CacheEvict(allEntries = true)
    public Result delete(@PathVariable Integer id){
        //判断是否有子菜单或按钮
        List<Category> categoryList = categoryService.findByParentId(id);
        if(categoryList.size() > 0){
            return ResultUtil.failure("请先删除子级别");
        }
        // 判断是否有文章
        if(articleService.checkByCategory(id)) {
            return ResultUtil.failure("该类别下有文章，无法删除");
        }
        categoryService.removeById(id);
        return ResultUtil.success();
    }
}
