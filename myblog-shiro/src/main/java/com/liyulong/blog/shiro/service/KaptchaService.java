package com.liyulong.blog.shiro.service;

public interface KaptchaService {

    /**
     * 获取验证码
     * @param username 用户名
     * @return 图片base64编码
     */
    String getKaptcha(String username);


    /**
     * 验证验证码
     * @param username 用户名
     * @param code 验证码
     * @return
     */
    Boolean verifyKaptcha(String username,String code);

}
