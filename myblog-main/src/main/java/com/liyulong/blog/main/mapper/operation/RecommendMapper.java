package com.liyulong.blog.main.mapper.operation;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.liyulong.blog.main.pojo.operation.Recommend;
import com.liyulong.blog.main.pojo.operation.vo.RecommendVO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * <p>
 * 推荐 Mapper 接口
 * </p>
 *
 * @author lihanlu
 * @since 2020-03-24
 */
@Mapper
public interface RecommendMapper extends BaseMapper<Recommend> {

    /**
     * 获取推荐文章列表
     * @return
     */
    List<RecommendVO> listSeclect();

    /**
     * 获取推荐列表
     * @return
     */
    List<RecommendVO> listRecommendVo();

    /**
     * 获取最热列表
     * @return
     */
    List<RecommendVO> listHotRead();
}
