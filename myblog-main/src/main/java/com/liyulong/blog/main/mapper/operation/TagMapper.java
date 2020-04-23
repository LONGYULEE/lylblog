package com.liyulong.blog.main.mapper.operation;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.liyulong.blog.main.pojo.operation.Tag;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * 标签 Mapper 接口
 * </p>
 *
 * @author lihanlu
 * @since 2020-03-24
 */
@Mapper
public interface TagMapper extends BaseMapper<Tag> {

    List<Tag> listByLinkId(@Param("linkId") Integer linkId, @Param("type") Integer type);
}
