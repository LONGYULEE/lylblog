package com.liyulong.blog.main.mapper.tag;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.liyulong.blog.main.pojo.tag.Tag;
import org.apache.ibatis.annotations.Mapper;

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

}
