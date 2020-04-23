package com.liyulong.blog.manage.operation.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.liyulong.blog.main.common.util.PageUtils;
import com.liyulong.blog.main.common.util.Query;
import com.liyulong.blog.main.mapper.operation.LinkMapper;
import com.liyulong.blog.main.pojo.operation.Link;
import com.liyulong.blog.manage.operation.service.LinkService;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * <p>
 * 友链 服务实现类
 * </p>
 *
 * @author lihanlu
 * @since 2020-03-24
 */
@Service
public class LinkServiceImpl extends ServiceImpl<LinkMapper, Link> implements LinkService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        String title = (String) params.get("title");
        IPage<Link> iPage = baseMapper.selectPage(new Query<Link>(params).getPage(),
                new QueryWrapper<Link>().lambda().like(StringUtils.isNotBlank(title), Link::getTitle,title));
        return new PageUtils(iPage);
    }
}
