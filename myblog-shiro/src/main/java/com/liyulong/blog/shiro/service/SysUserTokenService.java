package com.liyulong.blog.shiro.service;

import com.liyulong.blog.main.common.result.Result;

public interface SysUserTokenService {

    /**
     * 退出登录
     */
    void logout(String token);

    /**
     * 创建一个token
     * @param userId
     * @return
     */
    Result createToken(Integer userId);
}
