package com.liyulong.blog.manage.operation.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.liyulong.blog.main.common.util.PageUtils;
import com.liyulong.blog.main.common.util.Query;
import com.liyulong.blog.main.mapper.operation.TagLinkMapper;
import com.liyulong.blog.main.mapper.operation.TagMapper;
import com.liyulong.blog.main.pojo.operation.Tag;
import com.liyulong.blog.manage.operation.service.TagService;
import com.liyulong.blog.main.pojo.operation.TagLink;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;
import java.util.Optional;

/**
 * <p>
 * 标签 服务实现类
 * </p>
 *
 * @author lihanlu
 * @since 2020-03-24
 */
@Service
public class TagServiceImpl extends ServiceImpl<TagMapper, Tag> implements TagService {

    @Resource
    private TagLinkMapper tagLinkMapper;

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<Tag> iPage = baseMapper.selectPage(new Query<Tag>(params).getPage(),
                new QueryWrapper<Tag>().lambda());
        return new PageUtils(iPage);
    }

    @Override
    public List<Tag> listByLinkId(Integer linkId, Integer type) {
        return baseMapper.listByLinkId(linkId,type);
    }

    @Override
    public void saveTagAndNew(List<Tag> tagList, Integer linkId, Integer type) {
        Optional.ofNullable(tagList)
                .ifPresent(tagListValue -> tagListValue.forEach(tag -> {
                    if (tag.getId() == null) {
                        this.baseMapper.insert(tag);
                    }
                    TagLink tagLink = new TagLink(linkId, tag.getId(), type);
                    tagLinkMapper.insert(tagLink);
                }));
    }

    @Override
    public void deleteTagLink(Integer linkId, Integer type) {
        tagLinkMapper.delete(new QueryWrapper<TagLink>().lambda()
                .eq(linkId != null, TagLink::getLinkId, linkId)
                .eq(type != null, TagLink::getType, type));
    }
}
