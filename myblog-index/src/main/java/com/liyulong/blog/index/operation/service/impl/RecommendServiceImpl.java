package com.liyulong.blog.index.operation.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.liyulong.blog.index.article.service.ArticleService;
import com.liyulong.blog.index.operation.service.RecommendService;
import com.liyulong.blog.main.mapper.operation.RecommendMapper;
import com.liyulong.blog.main.pojo.article.vo.ArticleVO;
import com.liyulong.blog.main.pojo.operation.Recommend;
import com.liyulong.blog.main.pojo.operation.vo.RecommendVO;
import org.apache.commons.beanutils.BeanUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.lang.reflect.InvocationTargetException;
import java.util.List;

@Service("RecommendIndexService")
public class RecommendServiceImpl extends ServiceImpl<RecommendMapper, Recommend> implements RecommendService {

    @Resource
    private ArticleService articleService;

    @Override
    public List<RecommendVO> listRecommendVo() {
        List<RecommendVO> recommendList =this.baseMapper.listRecommendVo();
        return getRecommendList(recommendList);
    }

    @Override
    public List<RecommendVO> listHotRead() {
        List<RecommendVO> hotReadList =this.baseMapper.listHotRead();
        getRecommendList(hotReadList);
        hotReadList.get(0).setTop(true);
        return hotReadList;
    }

    private List<RecommendVO> getRecommendList(List<RecommendVO> recommendVOList){
        recommendVOList.forEach(recommendVO -> {
            try {
                ArticleVO simpleArticleVo = articleService.getSimpleArticleVo(recommendVO.getLinkId());
                //spring 和 common 的 copyProperties copy是相反的
                BeanUtils.copyProperties(recommendVO,simpleArticleVo);
                recommendVO.setUrlType("article");
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
        return recommendVOList;
    }


}
