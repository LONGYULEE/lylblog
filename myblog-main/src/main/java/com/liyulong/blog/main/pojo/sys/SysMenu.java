package com.liyulong.blog.main.pojo.sys;

import com.baomidou.mybatisplus.annotation.FieldStrategy;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ApiModel(value = "SysMenu对象",description = "菜单管理")
public class SysMenu implements Serializable {

    private static final long serialVersionUID = 2266461866911456904L;

    @TableId(value = "menu_id",type = IdType.AUTO)
    private Integer menuId;

    @ApiModelProperty(value = "父菜单ID，一级菜单为0",example = "0")
    private Integer parentId;

    @ApiModelProperty(value = "菜单名称")
    private String name;

    @ApiModelProperty(value = "菜单的url")
    private String url;

    @ApiModelProperty(value = "授权(多个用逗号隔开，如：user:list,user:create)")
    //为空忽略
    @TableField(strategy = FieldStrategy.IGNORED)
    private String perms;

    @ApiModelProperty(value = "菜单类型：0：目录，1：菜单，2：按钮",example = "0")
    private Integer type;

    @ApiModelProperty(value = "菜单图标")
    private String icon;

    @ApiModelProperty(value = "排序",example = "0")
    private Integer orderNum;

    //父菜单的名称
    @TableField(exist = false)
    private String parentName;

    //z-tree属性
    @TableField(exist = false)
    private Boolean open;

    //多级菜单存放 list
    @TableField(exist = false)
    private List<?> list;


}
