package com.liyulong.blog.shiro;

import com.liyulong.blog.main.common.util.JwtUtil;
import com.liyulong.blog.main.common.util.RedisUtil;
import com.liyulong.blog.shiro.service.SyncCacheService;
import org.apache.shiro.web.filter.authc.BasicHttpAuthenticationFilter;
import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

public class JwtFilter extends BasicHttpAuthenticationFilter {

    @Autowired
    private RedisUtil redisUtil;

    @Autowired
    private SyncCacheService syncCacheService;

    /**
     * 检测 Header 中是否存在 Authorization 字段
     * 判断是否登录
     */
    @Override
    protected boolean isLoginAttempt(ServletRequest request, ServletResponse response) {
        HttpServletRequest httpServletRequest = (HttpServletRequest) request;
        String authorization = httpServletRequest.getHeader("Authorization");
        return authorization != null;
    }

    /**
     * 登录验证
     * @param request 请求
     * @param response 响应
     * @return 执行登录
     * @throws Exception 异常
     */
    @Override
    protected boolean executeLogin(ServletRequest request, ServletResponse response) throws Exception {
        HttpServletRequest httpServletRequest = (HttpServletRequest) request;
        String authorization = httpServletRequest.getHeader("Authorization");

        MyToken token = new MyToken(authorization);
        //提交给realm进行登入，如果错误抛出异常并捕获
        getSubject(request,response).login(token);

        //获取账号
        String account = JwtUtil.getClaim(authorization,"userId");

        //如果没有抛出异常则代表登入成功，返回true
        //this.refreshTokenIfNeed(account,authorization,response);
        return true;
    }

    /**
     * 是否允许访问
     */
    @Override
    protected boolean isAccessAllowed(ServletRequest request, ServletResponse response, Object mappedValue) {
        //判断是否登录，是登录 执行 excuteLogin()
        return false;
    }


}
