package com.liyulong.blog.main.pojo.other;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 友链
 * </p>
 *
 * @author lihanlu
 * @since 2020-03-24
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="Link对象", description="友链")
public class Link implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    @TableId(value = "id", type = IdType.AUTO)
    @ApiModelProperty(value = "主键")
    private Integer id;

    /**
     * 链接名称
     */
    @ApiModelProperty(value = "连接名称")
    private String title;

    /**
     * 链接地址
     */
    @ApiModelProperty(value = "连接地址")
    private String url;

    /**
     * 头像
     */
    @ApiModelProperty(value = "头像")
    private String avatar;


}
