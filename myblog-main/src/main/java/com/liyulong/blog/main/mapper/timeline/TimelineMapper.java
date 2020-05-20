package com.liyulong.blog.main.mapper.timeline;

import com.liyulong.blog.main.pojo.timeline.Timeline;
import com.liyulong.blog.main.pojo.timeline.TimelinePost;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * TimeLineMapper
 * @description
 */
@Mapper
public interface TimelineMapper {

    List<TimelinePost> listTimelinePost(@Param("year") Integer year, @Param("month") Integer month);

    List<Timeline> listTimeline();
}
