package com.liyulong.blog.index.operation.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.liyulong.blog.main.pojo.operation.Tag;
import com.liyulong.blog.main.pojo.operation.vo.TagVO;

import java.util.List;

public interface TagService extends IService<Tag> {

    /**
     * 获取tagVoList
     * @return
     */
    List<TagVO> listTagVo();

}
