package com.liyulong.blog.main.pojo.sys;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ApiModel(value = "角色菜单对象",description = "")
public class SysRoleMenu implements Serializable {

    private static final long serialVersionUID = 5425237012232476829L;

    @TableId(value = "id",type = IdType.AUTO)
    private Integer id;

    @ApiModelProperty(value = "用户ID",example = "0")
    private Integer roleId;

    @ApiModelProperty(value = "角色ID",example = "0")
    private Integer menuId;

}
