package com.liyulong.blog.index.timeline.service.impl;

import com.liyulong.blog.index.timeline.service.TimelineService;
import com.liyulong.blog.main.mapper.timeline.TimelineMapper;
import com.liyulong.blog.main.pojo.timeline.Timeline;
import com.liyulong.blog.main.pojo.timeline.TimelineMonth;
import com.liyulong.blog.main.pojo.timeline.TimelinePost;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.List;

@Service
public class TimelineServiceImpl implements TimelineService {

    @Resource
    private TimelineMapper timelineMapper;

    @Override
    public List<Timeline> listTimeLine() {
        List<Timeline> timelineList = timelineMapper.listTimeline();
        getTimelineMonth(timelineList);
        return timelineList;
    }

    private List<Timeline> getTimelineMonth(List<Timeline> timelineList){
        for (Timeline timeline : timelineList) {
            List<TimelineMonth> timelineMonthList = new ArrayList<>();
            for(int i = Calendar.DECEMBER + 1;i>0;i--){
                List<TimelinePost> timelinePostList = timelineMapper.listTimelinePost(timeline.getYear(),i);
                if(CollectionUtils.isEmpty(timelinePostList)){
                    continue;
                }
                TimelineMonth month = new TimelineMonth();
                month.setCount(timelinePostList.size());
                month.setMonth(i);
                month.setPosts(timelinePostList);
                timelineMonthList.add(month);
            }
            timeline.setMonths(timelineMonthList);
        }
        return timelineList;
    }
}
