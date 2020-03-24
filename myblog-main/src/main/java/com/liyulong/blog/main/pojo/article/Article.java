package com.liyulong.blog.main.pojo.article;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

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
public class Article extends Model<Article> {

    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 文章标题
     */
    private String title;

    /**
     * 文章描述
     */
    private String description;

    /**
     * 文章作者
     */
    private String author;

    /**
     * 文章内容
     */
    private String content;

    /**
     * html的content
     */
    private String contentFormat;

    /**
     * 阅读量
     */
    private Integer readNum;

    /**
     * 评论量
     */
    private Integer commentNum;

    /**
     * 点赞量
     */
    private Integer likeNum;

    /**
     * 文章展示类别,1:普通，2：大图片，3：无图片
     */
    private Integer coverType;

    /**
     * 封面
     */
    private String cover;

    /**
     * 创建时间
     */
    private LocalDateTime createTime;

    /**
     * 更新时间
     */
    private LocalDateTime updateTime;

    /**
     * 是否推荐文章
     */
    private Boolean recommend;

    /**
     * 分类类别存在多级分类，用逗号隔开
     */
    private String categoryId;

    /**
     * 发布状态
     */
    private Integer publish;

    /**
     * 是否置顶
     */
    private Boolean top;


    @Override
    protected Serializable pkVal() {
        return this.id;
    }

}
