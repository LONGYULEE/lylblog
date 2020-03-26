package com.liyulong.blog.shiro;

import com.liyulong.blog.main.pojo.sys.SysUser;
import com.liyulong.blog.main.pojo.sys.SysUserToken;
import com.liyulong.blog.shiro.service.ShiroService;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * 自定义Realm
 */
// TODO
@Component
public class MyRealm extends AuthorizingRealm {

    @Autowired
    private ShiroService shiroService;

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
        //根据accessToken，查询用户信息
        SysUserToken tokenEntity = shiroService.findByToken(token);
        //token失效
        if(tokenEntity == null){
            throw new IncorrectCredentialsException("token失效");
        }
        //查询用户信息
        SysUser user = shiroService.queryUser(tokenEntity.getUserId());
        //账号锁定
        if(user.getStatus() == 0){
            throw new LockedAccountException("账号已被锁定");
        }
        //TODO 修改刷新token策略
        //续期
        shiroService.refreshToken(tokenEntity.getUserId());
        return new SimpleAuthenticationInfo(user,token,this.getName());
    }
}
