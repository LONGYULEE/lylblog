package com.liyulong.blog.main.pojo.tag;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 标签多对多维护表
 * </p>
 *
 * @author lihanlu
 * @since 2020-03-24
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="TagLink对象", description="标签多对多维护表")
@TableName("tag_link")
public class TagLink implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 标签Id
     */
    @ApiModelProperty(value = "标签id")
    private Integer tagId;

    /**
     * 关联Id
     */
    @ApiModelProperty(value = "关联id")
    private Integer linkId;

    /**
     * 所属类别：0文章，1阅读
     */
    @ApiModelProperty(value = "类别：0文章，1阅读")
    private Integer type;

    public TagLink(Integer tagId, Integer linkId, Integer type) {
        this.tagId = tagId;
        this.linkId = linkId;
        this.type = type;
    }
}
