package com.liyulong.myblog.back.tag.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.liyulong.blog.main.mapper.tag.TagLinkMapper;
import com.liyulong.blog.main.pojo.tag.TagLink;
import com.liyulong.myblog.back.tag.service.TagLinkService;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 标签多对多维护表 服务实现类
 * </p>
 *
 * @author lihanlu
 * @since 2020-03-24
 */
@Service
public class TagLinkServiceImpl extends ServiceImpl<TagLinkMapper, TagLink> implements TagLinkService {

}
