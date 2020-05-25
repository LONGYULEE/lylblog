package com.liyulong.blog.main.pojo.article;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.liyulong.blog.main.common.mybatisfill.BaseEntity;
import com.liyulong.blog.main.common.validator.AddGroup;
import com.liyulong.blog.main.common.validator.UpdateGroup;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import org.springframework.data.elasticsearch.annotations.Document;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * <p>
 * 文章
 * </p>
 *
 * @author lihanlu
 * @since 2020-03-24
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="BlogArticle对象", description="文章")
@Document(indexName = "dbblog",type = "article")
public class Article extends BaseEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 文章标题
     */
    @ApiModelProperty(value = "文章标题")
    @NotBlank(message="博文标题不能为空", groups = {AddGroup.class, UpdateGroup.class})
    private String title;

    /**
     * 文章描述
     */
    @ApiModelProperty(value = "文章描述")
    private String description;

    /**
     * 文章作者
     */
    @ApiModelProperty(value = "文章作者")
    private String author;

    /**
     * 文章内容
     */
    @ApiModelProperty(value = "文章内容")
    @NotBlank(message="博文内容不能为空", groups = {AddGroup.class, UpdateGroup.class})
    private String content;

    /**
     * 阅读量
     */
    @ApiModelProperty(value = "阅读量",example = "0")
    private Integer readNum;

    /**
     * 评论量
     */
    @ApiModelProperty(value = "评论量",example = "0")
    private Integer commentNum;

    /**
     * 点赞量
     */
    @ApiModelProperty(value = "点赞量",example = "0")
    private Integer likeNum;

    /**
     * 文章展示类别,1:普通，2：大图片，3：无图片
     */
    @ApiModelProperty(value = "文章展示类别,0:普通，1：大图片，2：无图片",example = "0")
    private Integer coverType;

    /**
     * 封面
     */
    @ApiModelProperty(value = "封面")
    private String cover;

    /**
     * 是否推荐文章
     */
    @ApiModelProperty(value = "是否推荐文章")
    private Boolean recommend;

    /**
     * 分类类别存在多级分类，用逗号隔开
     */
    @ApiModelProperty(value = "分类类别")
    private String categoryId;

    /**
     * 发布状态
     */
    @ApiModelProperty(value = "发布状态",example = "0")
    private Integer publish;

    /**
     * 是否置顶
     */
    @ApiModelProperty(value = "是否置顶")
    private Boolean top;

}
