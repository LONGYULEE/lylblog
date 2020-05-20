package com.liyulong.blog.main.pojo.timeline;

import lombok.Data;

import java.util.List;

/**
 * TimeLineMonh
 * @description
 */
@Data
public class TimelineMonth {

    private Integer month;

    private Integer count;

    private List<TimelinePost> posts;

}
