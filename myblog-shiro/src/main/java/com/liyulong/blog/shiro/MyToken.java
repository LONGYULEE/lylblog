package com.liyulong.blog.shiro;

import org.apache.shiro.authc.AuthenticationToken;

/**
 * shiro的认证类，方便获取token
 * 以判断username,password是否正确
 */
public class MyToken implements AuthenticationToken {

    private String token;

    public MyToken(String token) {
        this.token = token;
    }

    @Override
    public Object getPrincipal() {
        return token;
    }

    @Override
    public Object getCredentials() {
        return token;
    }
}
