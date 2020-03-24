package com.liyulong.blog.shiro.service;

import com.liyulong.blog.main.common.result.Result;

public interface SysUserTokenService {

    /**
     * 生成token
     * @param userId 用户
     * @return
     */
    Result login(Integer userId);

    /**
     * 退出登录
     */
    void logout(String token);

}
