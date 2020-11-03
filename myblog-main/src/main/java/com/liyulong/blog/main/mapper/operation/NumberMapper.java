package com.liyulong.blog.main.mapper.operation;

import com.liyulong.blog.main.pojo.operation.Number;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface NumberMapper {
    /**
     * 获取标签，文章，分类数量
     * @return
     */
    Number getNumbers();
}
