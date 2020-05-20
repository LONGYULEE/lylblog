package com.liyulong.blog.index.operation.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.liyulong.blog.index.operation.service.RecommendService;
import com.liyulong.blog.main.mapper.operation.RecommendMapper;
import com.liyulong.blog.main.pojo.operation.Recommend;
import org.springframework.stereotype.Service;

@Service("RecommendIndexService")
public class RecommendServiceImpl extends ServiceImpl<RecommendMapper, Recommend> implements RecommendService {
}
