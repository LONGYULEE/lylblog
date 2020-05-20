package com.liyulong.blog.main.pojo.timeline;

import lombok.Data;

import java.util.Date;

/**
 * TimeLineData
 * @description
 */
@Data
public class TimelinePost {

    private Integer id;

    private String title;

    private String description;

    private String postType;

    private Date createTime;

}
