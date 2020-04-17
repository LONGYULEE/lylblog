package com.liyulong.blog.shiro;

import com.liyulong.blog.main.common.result.Result;
import com.liyulong.blog.main.common.result.ResultUtil;
import com.liyulong.blog.main.common.util.JsonUtil;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.web.filter.authc.AuthenticatingFilter;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 未使用
 */
public class MyFilter extends AuthenticatingFilter {
    @Override
    protected AuthenticationToken createToken(ServletRequest request, ServletResponse response) throws Exception {
        //获取请求token
        String token = getRequestToken((HttpServletRequest) request);
        if(StringUtils.isEmpty(token)){
            return null;
        }
        return new MyToken(token);
    }

    @Override
    protected boolean onAccessDenied(ServletRequest request, ServletResponse response) throws Exception {
        HttpServletResponse httpResponse = (HttpServletResponse) response;
        //获取请求token，如果token不存在，直接返回401
        String token = getRequestToken((HttpServletRequest) request);
        if(StringUtils.isEmpty(token)){
            HttpServletResponse httpServletResponse = (HttpServletResponse) response;
            //是否允许后续请求携带认证信息（cookies）,该值只能是true,否则不返回
            httpServletResponse.setHeader("Access-Control-Allow-Credentials","true");
            httpServletResponse.setHeader("Access-Control-Allow-Origin",((HttpServletRequest) request).getHeader(
                    "Origin"));
            String json = JsonUtil.toJson(ResultUtil.failure("token无效"));
            //设置返回信息编码格式，否则中文显示乱码
            httpResponse.setHeader("content-type", "text/html;charset=UTF-8");
            httpResponse.getWriter().print(json);
            return false;
        }
        return executeLogin(request,response);
    }

    @Override
    protected boolean isAccessAllowed(ServletRequest request, ServletResponse response, Object mappedValue) {
        return ((HttpServletRequest) request).getMethod().equals(RequestMethod.OPTIONS.name());
    }

    @Override
    protected boolean onLoginFailure(AuthenticationToken token, AuthenticationException e, ServletRequest request, ServletResponse response) {
        HttpServletResponse httpServletResponse = (HttpServletResponse) response;
        httpServletResponse.setContentType("application/json;charset=utf-8");
        httpServletResponse.setHeader("Access-Control-Allow-Credentials","true");
        httpServletResponse.setHeader("Access-Control-Allow-Origin",((HttpServletRequest) request).getHeader(
                "Origin"));
        try {
            //处理登录失败的异常
            Throwable throwable = e.getCause() == null ? e : e.getCause();
            Result r = ResultUtil.failure(throwable.getMessage());
            String json = JsonUtil.toJson(r);
            httpServletResponse.getWriter().print(json);
        } catch (Exception e1) {

        }
        return false;
    }

    //获取请求的token
    private String getRequestToken(HttpServletRequest httpServletRequest){
        //从header中获取token
        String token = httpServletRequest.getHeader("Authorization");
        //如果header中不存在token，则从参数中获取token
        if(StringUtils.isEmpty(token)){
            token = httpServletRequest.getParameter("token");
        }
        return token;
    }
}
