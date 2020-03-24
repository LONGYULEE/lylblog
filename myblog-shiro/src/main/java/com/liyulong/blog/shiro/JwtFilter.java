package com.liyulong.blog.shiro;

import com.auth0.jwt.exceptions.SignatureVerificationException;
import com.auth0.jwt.exceptions.TokenExpiredException;
import com.liyulong.blog.main.common.exception.MyException;
import com.liyulong.blog.main.common.result.Result;
import com.liyulong.blog.main.common.util.JsonUtil;
import com.liyulong.blog.main.common.util.JwtUtil;
import com.liyulong.blog.main.common.util.RedisUtil;
import com.liyulong.blog.shiro.service.SyncCacheService;
import org.apache.shiro.web.filter.authc.BasicHttpAuthenticationFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

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
        this.refreshTokenIfNeed(account,authorization,response);
        return true;
    }

    /**
     * 是否允许访问
     */
    @Override
    protected boolean isAccessAllowed(ServletRequest request, ServletResponse response, Object mappedValue) {
        //判断是否登录，是登录 执行 excuteLogin()
        if(isLoginAttempt(request,response)){
            try {
                this.executeLogin(request,response);
                return true;
            } catch (Exception e) {
                e.printStackTrace();
                String msg = e.getMessage();
                Throwable throwable = e.getCause();
                if(throwable instanceof SignatureVerificationException){
                    msg = "Token不正确(" + throwable.getMessage() + ")";
                }else if(throwable instanceof TokenExpiredException){
                    //accesstoken已过期
                    if(this.refreshToken(request,response)){
                        return true;
                    }else{
                        msg = "Token已过期(" + throwable.getMessage() + ")";
                    }
                }else {
                    if(throwable != null){
                        msg = throwable.getMessage();
                    }
                }
                this.response401(request,response,msg);
                return false;
            }
        }else {
            return false;
        }
    }

    //重写 onAccessDenied 方法，避免父类中调用再次executeLogin
    @Override
    protected boolean onAccessDenied(ServletRequest request, ServletResponse response) throws Exception {
        this.sendChallenge(request, response);
        return false;
    }

    //刷新accesstoken，进行判断refreshToken是否过期，未过期就返回新的accesstoken且继续正常访问
    private boolean refreshToken(ServletRequest request,ServletResponse response){
        //获取accessToken(Shiro中getAuthzHeader方法已经实现)
        String token = this.getAuthzHeader(request);
        //获取当前token的账号信息
//        String account
        return false;
    }

    //401非法请求
    private void response401(ServletRequest request,ServletResponse response,String msg){
        HttpServletResponse httpServletResponse = (HttpServletResponse) response;
        httpServletResponse.setStatus(HttpStatus.UNAUTHORIZED.value());
        httpServletResponse.setCharacterEncoding("UTF-8");
        httpServletResponse.setContentType("application/json; charset=utf-8");
        PrintWriter out = null;
        try {
            out = httpServletResponse.getWriter();

            Result result = new Result();
            result.setFlag(false);
            result.setCode(2001);
            result.setMessage(msg);
            out.append(JsonUtil.toJson(request));
        } catch (IOException e) {
//            LOGGER.error("返回Response信息出现IOException异常:" + e.getMessage());
            System.out.println("返回Response信息出现IOException异常:" + e.getMessage());
        } finally {
            if (out != null) {
                out.close();
            }
        }
    }

    private boolean refreshTokenIfNeed(String userId,String authorization,ServletResponse response){
        //获取时间戳
        Long currentTimeMillis = System.currentTimeMillis();
        //检查刷新规则
        if(this.refreshCheck(authorization,currentTimeMillis)){
            String lockName = "refresh_check:" + userId;
            boolean b = syncCacheService.getLock(lockName,60);
            if(b){
                //获取锁
                String refreshTokenKey = "refresh_token:" + userId;
                if(redisUtil.exist(refreshTokenKey)){
                    //检查redis中的时间戳与token的事件戳是否一致
                    String tokenTimeStamp = redisUtil.get(refreshTokenKey);
                    String tokenMillis = JwtUtil.getClaim(authorization,"currentTimeMillis");
                    if(!tokenMillis.equals(tokenMillis)){
                        throw new MyException("token无效");
                    }
                }
                //时间戳一致，颁发新的token
                //logger.info();
                String strCurrentTimeMillis = String.valueOf(currentTimeMillis);
                String newToken = JwtUtil.createToken(userId,strCurrentTimeMillis);

                //更新redis中的时间戳
                redisUtil.set(refreshTokenKey,strCurrentTimeMillis);
                //返回一个token
//                return newToken;
                HttpServletResponse httpServletResponse = (HttpServletResponse) response;
                httpServletResponse.setHeader("Authorization", newToken);
                httpServletResponse.setHeader("Access-Control-Expose-Headers", "Authorization");
            }
            syncCacheService.releaseLock(lockName);
        }
        return true;
    }

    /**
     * 检查是否需要更新token
     * @param authorization token
     * @param currentTimeMillis 时间戳
     * @return flag
     */
    private boolean refreshCheck(String authorization,Long currentTimeMillis){
        String tokenMillis = JwtUtil.getClaim(authorization,"currentTimeMillis");
        if(currentTimeMillis - Long.parseLong(tokenMillis) > (60 * 2 * 60)){
            return true;
        }
        return false;
    }
}
