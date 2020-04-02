package com.liyulong.blog.main.pojo.sys.form;

import lombok.Data;

import java.io.Serializable;

@Data
public class LoginForm implements Serializable {

    private static final long serialVersionUID = 8113641840274394792L;

    //用户名
    private String username;
    //密码
    private String password;
    //验证码
    private String captcha;
    //验证码的唯一标识
    private String uuid;

}
