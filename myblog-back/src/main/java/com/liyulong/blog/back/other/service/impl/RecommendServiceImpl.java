package com.liyulong.blog.back.other.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.liyulong.blog.back.other.service.RecommendService;
import com.liyulong.blog.main.mapper.other.RecommendMapper;
import com.liyulong.blog.main.pojo.other.Recommend;
import org.springframework.stereotype.Service;

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

}
