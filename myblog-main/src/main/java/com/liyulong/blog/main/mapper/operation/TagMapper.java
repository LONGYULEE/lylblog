package com.liyulong.blog.main.mapper.operation;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.liyulong.blog.main.pojo.operation.Tag;
import com.liyulong.blog.main.pojo.operation.vo.TagVO;
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

    /**
     * 根据 linkId 获取 tag 列表
     * @param linkId 友链id
     * @param type 连接类型
     * @return tag 列表
     */
    List<Tag> listByLinkId(@Param("linkId") Integer linkId, @Param("type") Integer type);

    /**
     * 根据 linkId 删除多对多关联
     * @param linkId
     * @param type
     */
    void deleteTagLink(@Param("linkId") Integer linkId,@Param("type") Integer type);

    /**
     * 获取 TagList
     * @return
     */
    List<TagVO> listTagVo();
}
