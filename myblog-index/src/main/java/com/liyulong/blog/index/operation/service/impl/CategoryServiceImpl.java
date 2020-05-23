package com.liyulong.blog.index.operation.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.liyulong.blog.index.operation.service.CategoryService;
import com.liyulong.blog.main.mapper.operation.CategoryMapper;
import com.liyulong.blog.main.pojo.operation.Category;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service("CategoryIndexService")
public class CategoryServiceImpl extends ServiceImpl<CategoryMapper, Category> implements CategoryService {
    @Override
    public List<Category> listCategory(Map<String, Object> params) {
        String type = (String) params.get("type");
        String rank = (String) params.get("rank");
        return baseMapper.selectList(new QueryWrapper<Category>().lambda()
                .eq(StringUtils.isNotEmpty(type),Category::getType,type)
                .eq(StringUtils.isNotEmpty(rank),Category::getRank,rank));
    }
}
