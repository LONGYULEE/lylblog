package com.liyulong.blog.manage.operation.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.liyulong.blog.manage.operation.service.CategoryService;
import com.liyulong.blog.main.mapper.operation.CategoryMapper;
import com.liyulong.blog.main.pojo.operation.Category;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author lihanlu
 * @since 2020-03-24
 */
@Service
public class CategoryServiceImpl extends ServiceImpl<CategoryMapper, Category> implements CategoryService {

    @Override
    public List<Category> findAllWithParentName(Map<String, Object> map) {
        return baseMapper.queryAll(map);
    }

    @Override
    public List<Category> findByParentId(Integer parentId) {
        return baseMapper.selectList(new QueryWrapper<Category>().lambda().eq(Category::getParentId, parentId));
    }

    @Override
    public String renderCategoryArr(String categoryIds, List<Category> categoryList) {
        if (StringUtils.isEmpty(categoryIds)) {
            return "";
        }
        List<String> categoryStrList = new ArrayList<>();
        String[] categoryIdArr = categoryIds.split(",");
        for (String s : categoryIdArr) {
            Integer categoryId = Integer.parseInt(s);
            // 根据Id查找类别名称
            String categoryStr = categoryList
                    .stream()
                    .filter(category -> category.getId().equals(categoryId))
                    .map(Category::getName)
                    .findAny().orElse("类别已被删除");
            categoryStrList.add(categoryStr);
        }
        return String.join(",",categoryStrList);

    }
}
