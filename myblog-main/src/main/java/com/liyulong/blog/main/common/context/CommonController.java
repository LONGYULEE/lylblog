package com.liyulong.blog.main.common.context;

import com.liyulong.blog.main.pojo.sys.SysUser;
import org.apache.shiro.SecurityUtils;

/**
 * 公共controller方法，获取当前登录的用户信息
 */
public class CommonController {

    protected SysUser getUser(){
        return (SysUser) SecurityUtils.getSubject().getPrincipal();
    }

    protected Integer getUserId(){
        return getUser().getUserId();
    }

}
