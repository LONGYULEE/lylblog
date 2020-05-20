package com.liyulong.blog.main.pojo.timeline;

import lombok.Data;

import java.util.List;

/**
 * TimeLine
 * @description
 */
@Data
public class Timeline {

    private Integer year;

    private Integer count;

    private List<TimelineMonth> months;
}
