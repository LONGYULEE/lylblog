package com.liyulong.blog.shiro.service;

public interface KaptchaService {

    /**
     * 获取验证码
     * @return 图片base64编码
     */
    String getKaptcha(String uuid);


    /**
     * 验证验证码
     * @param code 验证码
     * @param uuid 验证码的唯一标识
     * @return
     */
    Boolean verifyKaptcha(String code,String uuid);

}
