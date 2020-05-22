package com.liyulong.blog.index.operation.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.liyulong.blog.index.operation.service.LinkService;
import com.liyulong.blog.main.mapper.operation.LinkMapper;
import com.liyulong.blog.main.pojo.operation.Link;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("LinkIndexService")
public class LinkServiceImpl extends ServiceImpl<LinkMapper, Link> implements LinkService {

    @Override
    public List<Link> listLink() {
        return baseMapper.selectList(null);
    }

}
