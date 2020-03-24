package com.liyulong.blog.main.pojo.sys;

import io.swagger.annotations.ApiModel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ApiModel(value = "SysUserToken对象",description = "系统用户Token")
public class SysUserToken implements Serializable {

    private static final long serialVersionUID = 5187771727644778153L;

    private Integer userId;

    private String token;

}
