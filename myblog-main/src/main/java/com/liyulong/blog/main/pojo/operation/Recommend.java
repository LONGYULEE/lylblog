package com.liyulong.blog.main.pojo.operation;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 推荐
 * </p>
 *
 * @author lihanlu
 * @since 2020-03-24
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="Recommend对象", description="推荐")
public class Recommend implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    @TableId(value = "id", type = IdType.AUTO)
    @ApiModelProperty(value = "主键")
    private Integer id;

    /**
     * 推荐的文章Id
     */
    @ApiModelProperty(value = "推荐文章id")
    private Integer linkId;

    /**
     * 推荐类型
     */
    @ApiModelProperty(value = "推荐类型")
    private Integer type;

    /**
     * 顺序
     */
    @ApiModelProperty(value = "推荐顺序")
    private Integer orderNum;

    /**
     * 标题
     */
    @ApiModelProperty(value = "标题")
    private String title;

    /**
     * 置顶
     */
    @ApiModelProperty(value = "是否置顶")
    private Boolean top;

}
