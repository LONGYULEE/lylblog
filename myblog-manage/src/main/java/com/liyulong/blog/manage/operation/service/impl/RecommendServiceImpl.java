package com.liyulong.blog.manage.operation.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.liyulong.blog.main.common.util.PageUtils;
import com.liyulong.blog.main.common.util.Query;
import com.liyulong.blog.main.pojo.operation.vo.RecommendVO;
import com.liyulong.blog.manage.operation.service.RecommendService;
import com.liyulong.blog.main.mapper.operation.RecommendMapper;
import com.liyulong.blog.main.pojo.operation.Recommend;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 推荐 服务实现类
 * </p>
 *
 * @author lihanlu
 * @since 2020-03-24
 */
@Service
public class RecommendServiceImpl extends ServiceImpl<RecommendMapper, Recommend> implements RecommendService {


    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        String title = (String) params.get("title");
        IPage<Recommend> iPage = baseMapper.selectPage(new Query<Recommend>(params).getPage(),
                new QueryWrapper<Recommend>().lambda()
                        .like(StringUtils.isNotEmpty(title),Recommend::getTitle,title));
        return new PageUtils(iPage);
    }

    @Override
    public List<RecommendVO> listSelect() {
        return baseMapper.listSeclect();
    }

    @Override
    public void updateTop(Integer id) {
        // 更新本条
        Recommend recommend = new Recommend();
        recommend.setTop(true);
        recommend.setId(id);
        this.baseMapper.updateById(recommend);
        //批量更新其他
        recommend.setTop(false);
        recommend.setId(null);
        this.baseMapper.update(recommend,new QueryWrapper<Recommend>().lambda()
                .ne(Recommend::getId,id));
    }

    @Override
    public void deleteBatchByLinkId(Integer[] articleIds, int type) {
        for (int linkId : articleIds) {
            baseMapper.delete(new QueryWrapper<Recommend>().lambda()
                    .eq(Recommend::getLinkId, linkId)
                    .eq(Recommend::getType, type));
        }
    }
}
