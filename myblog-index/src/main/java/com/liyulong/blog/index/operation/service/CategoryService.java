package com.liyulong.blog.index.operation.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.liyulong.blog.main.pojo.operation.Category;
import com.liyulong.blog.main.pojo.operation.Number;

import java.util.List;
import java.util.Map;

public interface CategoryService extends IService<Category> {

    /**
     * 获取categoryList
     * @param params
     * @return
     */
    List<Category> listCategory(Map<String, Object> params);

    Number getNumbers();
}
