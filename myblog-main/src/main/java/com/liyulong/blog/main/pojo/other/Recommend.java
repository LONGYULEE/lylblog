package com.liyulong.blog.main.pojo.other;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;
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
public class Recommend extends Model<Recommend> {

    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 推荐的文章Id
     */
    private Integer linkId;

    /**
     * 推荐类型
     */
    private Integer type;

    /**
     * 顺序
     */
    private Integer orderNum;

    /**
     * 标题
     */
    private String title;

    /**
     * 置顶
     */
    private Boolean top;


    @Override
    protected Serializable pkVal() {
        return this.id;
    }

}
