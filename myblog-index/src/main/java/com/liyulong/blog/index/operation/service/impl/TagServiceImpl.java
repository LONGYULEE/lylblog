package com.liyulong.blog.index.operation.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.liyulong.blog.index.operation.service.TagService;
import com.liyulong.blog.main.mapper.operation.TagMapper;
import com.liyulong.blog.main.pojo.operation.Tag;
import com.liyulong.blog.main.pojo.operation.vo.TagVO;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("TagIndexService")
public class TagServiceImpl extends ServiceImpl<TagMapper, Tag> implements TagService {

    /**
     * 返回 tagVo 列表
     * @return
     */
    @Override
    public List<TagVO> listTagVo() {
        return baseMapper.listTagVo();
    }

}
