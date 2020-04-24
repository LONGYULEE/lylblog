package com.liyulong.blog.main.pojo.operation.vo;

import com.liyulong.blog.main.pojo.operation.Recommend;
import com.liyulong.blog.main.pojo.operation.Tag;
import io.swagger.annotations.ApiKeyAuthDefinition;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Date;
import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Data
public class RecommendVO extends Recommend {

    private static final long serialVersionUID = -5372740217231042602L;
    @ApiModelProperty(value = "描述")
    private String description;

    @ApiModelProperty(value = "阅读数")
    private Long readNum;

    @ApiModelProperty(value = "评论数")
    private Long commentNum;

    @ApiModelProperty(value = "喜欢数")
    private Long likeNum;

    @ApiModelProperty(value = "连接类型")
    private String urlType;

    @ApiModelProperty(value = "封面")
    private String cover;

    @ApiModelProperty(value = "创建时间")
    private Date createTime;

    @ApiModelProperty(value = "标签列表")
    private List<Tag> tagList;

}
