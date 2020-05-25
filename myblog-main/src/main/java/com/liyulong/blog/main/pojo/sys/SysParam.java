package com.liyulong.blog.main.pojo.sys;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true) //是否允许链式调用
@ApiModel(value = "SysParam对象",description = "系统参数")
public class SysParam implements Serializable {

    private static final long serialVersionUID = -3846513283748331952L;

    @TableId(value = "id",type = IdType.AUTO)
    private Integer id;

    @NotNull(message = "参数键不能为空")
    @ApiModelProperty(value = "参数键",example = "0")
    private Integer parKey;

    @ApiModelProperty(value = "参数值")
    @NotBlank(message = "参数值不能为空")
    private String parValue;

    @ApiModelProperty(value = "参数url")
    private String menuUrl;

    @NotBlank(message = "参数类型不能为空")
    @ApiModelProperty(value = "参数类型")
    private String type;

}
