package com.liyulong.blog.main.pojo.sys;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.liyulong.blog.main.common.validator.AddGroup;
import com.liyulong.blog.main.common.validator.UpdateGroup;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ApiModel(value = "SysUser对象",description = "用户") //描述返回对象的意义
public class SysUser implements Serializable {

    private static final long serialVersionUID = -6646487795344909751L;

    //@ApiModelProperty(value = "主键") //用在出入参数对象的字段上
    @TableId(value = "user_id",type = IdType.AUTO)
    private Integer userId;

    @NotBlank(message = "用户名不能为空",groups = {AddGroup.class, UpdateGroup.class})
    @ApiModelProperty(value = "用户名")
    private String username;

    @NotBlank(message = "密码不能为空",groups = AddGroup.class)
    @ApiModelProperty(value = "密码")
    private String password;

    @NotBlank(message = "邮箱不能为空",groups = {AddGroup.class,UpdateGroup.class})
    @Email(message = "邮箱格式不正确",groups = {AddGroup.class,UpdateGroup.class})
    @ApiModelProperty(value = "邮箱")
    private String email;

    @ApiModelProperty(value = "密码盐值")
    private String salt;

    @ApiModelProperty(value = "创建人id",example = "0")
    private Integer createUserId;

    @ApiModelProperty(value = "0:禁用，1正常",example = "0")
    private Integer status;

    @ApiModelProperty(value = "创建时间")
    @TableField(fill = FieldFill.INSERT)
    private Date createTime;

    @ApiModelProperty(value = "用户头像")
    private String avatar;

    //标注此属性不存在于数据库字段中，查询时不查询此属性
    @TableField(exist = false)
    private List<Integer> roleIdList;

}
