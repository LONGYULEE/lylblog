package com.liyulong.blog.shiro;

import com.liyulong.blog.main.common.util.JwtUtil;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.stereotype.Component;

/**
 * 自定义Realm
 */
// TODO
@Component
public class MyRealm extends AuthorizingRealm {

    @Override
    public boolean supports(AuthenticationToken token) {
        return token instanceof MyToken;
    }

    /**
     * 授权:验证时判断用户角色权限
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        return null;
    }

    /**
     * 认证:登录时判断用户名或密码是否正确
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken auth) throws AuthenticationException {
        String token = (String) auth.getPrincipal();
        String userId = JwtUtil.getClaim(token,"userId");

        if(userId == null){
            throw new AuthenticationException("token无效，请重新登录");
        }

        if(JwtUtil.verify(token)){
            return new SimpleAuthenticationInfo(token,token,this.getName());
        }
        throw new AuthenticationException("token无效");

    }
}
