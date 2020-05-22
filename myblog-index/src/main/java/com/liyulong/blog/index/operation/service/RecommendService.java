package com.liyulong.blog.index.operation.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.liyulong.blog.main.pojo.operation.Recommend;
import com.liyulong.blog.main.pojo.operation.vo.RecommendVO;

import java.util.List;

public interface RecommendService extends IService<Recommend> {

    /**
     * 获取推荐列表
     * @return list
     */
    List<RecommendVO> listRecommendVo();

    /**
     * 获取热门列表
     * @return
     */
    List<RecommendVO> listHotRead();

}
