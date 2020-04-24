package com.liyulong.blog.main.pojo.operation.vo;

import com.liyulong.blog.main.pojo.operation.Tag;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class TagVO extends Tag {

    private static final long serialVersionUID = -6330213901781665990L;
    @ApiModelProperty(value = "友链数")
    private String linkNum;
}
