package com.liyulong.blog.main.pojo.book;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * <p>
 * 图书表
 * </p>
 *
 * @author lihanlu
 * @since 2020-03-24
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class Book extends Model<Book> {

    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 标题
     */
    private String title;

    /**
     * 封面
     */
    private String cover;

    /**
     * 作者
     */
    private String author;

    /**
     * 分类类别
     */
    private String categoryId;

    /**
     * 是否推荐
     */
    private Boolean recommend;

    /**
     * 出版社
     */
    private String publisher;

    /**
     * 出版日期
     */
    private LocalDate publishDate;

    /**
     * 页数
     */
    private Integer pageNum;

    /**
     * 评分
     */
    private Double grade;

    /**
     * 简介
     */
    private String description;

    /**
     * 原书目录
     */
    private String catalogue;

    /**
     * 创建时间
     */
    private LocalDateTime createTime;

    /**
     * 更新时间
     */
    private LocalDateTime updateTime;

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
     * 是否发布
     */
    private Boolean publish;

    /**
     * 读书状态
     */
    private Integer progress;

    /**
     * 是否阅读
     */
    private Boolean reading;


    @Override
    protected Serializable pkVal() {
        return this.id;
    }

}
