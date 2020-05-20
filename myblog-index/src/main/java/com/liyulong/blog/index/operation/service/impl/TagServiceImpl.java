package com.liyulong.blog.index.operation.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.liyulong.blog.index.operation.service.TagService;
import com.liyulong.blog.main.mapper.operation.TagMapper;
import com.liyulong.blog.main.pojo.operation.Tag;
import org.springframework.stereotype.Service;

@Service("TagIndexService")
public class TagServiceImpl extends ServiceImpl<TagMapper, Tag> implements TagService {
}
