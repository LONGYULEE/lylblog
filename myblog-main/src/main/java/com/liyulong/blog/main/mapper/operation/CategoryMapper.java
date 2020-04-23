package com.liyulong.blog.main.mapper.operation;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.liyulong.blog.main.pojo.operation.Category;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author lihanlu
 * @since 2020-03-24
 */
@Mapper
public interface CategoryMapper extends BaseMapper<Category> {

    List<Category> queryAll(Map<String, Object> map);
}
