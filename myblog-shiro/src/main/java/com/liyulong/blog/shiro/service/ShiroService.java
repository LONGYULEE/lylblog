package com.liyulong.blog.shiro.service;

import com.liyulong.blog.main.pojo.sys.SysUser;
import com.liyulong.blog.main.pojo.sys.SysUserToken;

import java.util.Set;

public interface ShiroService {

    /**
     * 通过token查询用户token
     * @param token token
     * @return 用户token对象
     */
    SysUserToken findByToken(String token);

    /**
     * 查询用户信息
     * @param userId 用户id
     * @return 用户对象
     */
    SysUser queryUser(Integer userId);

    /**
     * 刷新token
     * @param userId 用户id
     */
    void refreshToken(Integer userId);

    /**
     * 查询用户所有的权限
     * @param userId
     * @return
     */
    Set<String> queryPermissions(Integer userId);
}
