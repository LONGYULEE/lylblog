package com.liyulong.blog.shiro.service;

public interface KaptchaService {

    /**
     * 获取验证码
     * @return 图片base64编码
     */
    String getKaptcha();


    /**
     * 验证验证码
     * @param code 验证码
     * @return
     */
    Boolean verifyKaptcha(String code);

}
