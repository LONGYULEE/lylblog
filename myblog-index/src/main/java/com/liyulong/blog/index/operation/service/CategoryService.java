package com.liyulong.blog.index.operation.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.liyulong.blog.main.pojo.operation.Category;
import com.liyulong.blog.main.pojo.operation.Number;

import java.util.HashMap;
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

    /**
     * 获取分类的数量和名称
     * @return
     */
    HashMap<String, Integer> getNumberAndName();
}
