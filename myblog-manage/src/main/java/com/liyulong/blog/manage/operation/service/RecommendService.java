package com.liyulong.blog.manage.operation.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.liyulong.blog.main.common.util.PageUtils;
import com.liyulong.blog.main.pojo.operation.Recommend;
import com.liyulong.blog.main.pojo.operation.vo.RecommendVO;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 推荐 服务类
 * </p>
 *
 * @author lihanlu
 * @since 2020-03-24
 */
public interface RecommendService extends IService<Recommend> {

    /**
     * 分页查询
     * @param params
     * @return
     */
    PageUtils queryPage(Map<String, Object> params);

    /**
     * 获取推荐列表
     * @return
     */
    List<RecommendVO> listSelect();

    /**
     * 更新置顶状态
     * @param id
     */
    void updateTop(Integer id);

    /**
     * 批量删除
     * @param articleIds 文章ids
     * @param type type
     */
    void deleteBatchByLinkId(Integer[] articleIds, int type);

}
