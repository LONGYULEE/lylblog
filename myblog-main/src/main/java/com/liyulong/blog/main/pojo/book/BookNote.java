package com.liyulong.blog.main.pojo.book;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.baomidou.mybatisplus.annotation.TableId;
import java.time.LocalDateTime;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 笔记
 * </p>
 *
 * @author lihanlu
 * @since 2020-03-24
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class BookNote extends Model<BookNote> {

    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 笔记标题
     */
    private String title;

    /**
     * 笔记描述
     */
    private String description;

    /**
     * 笔记作者
     */
    private String author;

    /**
     * 笔记内容
     */
    private String content;

    /**
     * html的context
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
     * 封面
     */
    private String cover;

    /**
     * 所属书本
     */
    private Integer bookId;

    /**
     * 所属章节
     */
    private String chapter;

    /**
     * 创建时间
     */
    private LocalDateTime createTime;

    /**
     * 更新时间
     */
    private LocalDateTime updateTime;

    /**
     * 是否推荐笔记
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
     * 封面类型
     */
    private Integer coverType;

    /**
     * 是否置顶
     */
    private Boolean top;


    @Override
    protected Serializable pkVal() {
        return this.id;
    }

}
