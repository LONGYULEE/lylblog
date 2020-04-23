package com.liyulong.blog.main.pojo.operation;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * <p>
 * 分类
 * </p>
 *
 * @author lihanlu
 * @since 2020-03-24
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("category")
public class Category implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    @ApiModelProperty(value = "主键")
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 名称
     */
    @ApiModelProperty(value = "分类名称")
    @NotBlank(message = "菜单名称不能为空")
    private String name;

    /**
     * 类型：0文章，1阅读
     */
    @ApiModelProperty(value = "类型")
    @NotBlank(message = "类型不能为空")
    private Integer type;

    /**
     * 级别
     */
    @ApiModelProperty(value = "级别")
    @NotBlank(message = "级别不能为空")
    @TableField(value = "`rank`") //rank为关键字
    private Integer rank;

    /**
     * 父主键
     */
    @ApiModelProperty("父菜单id")
    @NotNull(message = "父菜单id不能为空")
    private Integer parentId;

    /**
     * 父菜单名称
     */
    @TableField(exist = false)
    private String parentName;

}
