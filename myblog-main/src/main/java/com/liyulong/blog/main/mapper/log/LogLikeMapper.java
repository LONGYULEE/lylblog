package com.liyulong.blog.main.mapper.log;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.liyulong.blog.main.pojo.log.LogLike;
import org.apache.ibatis.annotations.Mapper;

/**
 * <p>
 * 点赞日志 Mapper 接口
 * </p>
 *
 * @author lihanlu
 * @since 2020-03-24
 */
@Mapper
public interface LogLikeMapper extends BaseMapper<LogLike> {

}
