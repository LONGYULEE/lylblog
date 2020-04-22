package com.liyulong.blog.back.other.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.liyulong.blog.main.pojo.other.Category;

import java.util.List;
import java.util.Map;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author lihanlu
 * @since 2020-03-24
 */
public interface CategoryService extends IService<Category> {

    //查询所有菜单
    List<Category> findByParentName(Map<String,Object> map);

    //根据负类别查询子类别
    List<Category> findByParentId(Integer id);

    //根据类别id数组查询类别数组
//    String
}
