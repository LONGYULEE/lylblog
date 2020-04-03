package com.liyulong.blog.main.common.context;

import com.liyulong.blog.main.pojo.sys.SysUserToken;

/**
 * 用户上下文对象，方便存取登录用户信息
 */
public class UserContext {

    static final ThreadLocal<SysUserToken> current = new ThreadLocal<>();

    public UserContext(SysUserToken user){
        current.set(user);
    }

    public static SysUserToken getCurrentUser(){
        return current.get();
    }

    public static Integer getUserId(){return current.get().getUserId();}

    public void close(){
        current.remove();
    }
}
