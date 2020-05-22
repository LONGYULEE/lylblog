package com.liyulong.blog.index.timeline.controller;

import com.liyulong.blog.index.timeline.service.TimelineService;
import com.liyulong.blog.main.common.result.Result;
import com.liyulong.blog.main.common.result.ResultUtil;
import com.liyulong.blog.main.pojo.timeline.Timeline;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("/timeline")
public class TimelineController {

    @Resource
    private TimelineService timelineService;

    @GetMapping("")
    public Result listTimeline(){
        List<Timeline> timelineList = timelineService.listTimeLine();
        return ResultUtil.success(timelineList);
    }

}
