package com.liyulong.blog.index.timeline.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.liyulong.blog.main.pojo.timeline.Timeline;

import java.util.List;

public interface TimelineService {

    /**
     * 获取timeline数据
     * @return
     */
    List<Timeline> listTimeLine();

}
