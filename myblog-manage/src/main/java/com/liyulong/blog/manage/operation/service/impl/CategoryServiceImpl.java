package com.liyulong.blog.manage.operation.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.liyulong.blog.manage.operation.service.CategoryService;
import com.liyulong.blog.main.mapper.operation.CategoryMapper;
import com.liyulong.blog.main.pojo.operation.Category;
import org.springframework.stereotype.Service;

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
    public List<Category> findByParentId(Integer id) {
        return null;
    }
}
