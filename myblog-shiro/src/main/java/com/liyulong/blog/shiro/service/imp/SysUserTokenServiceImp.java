package com.liyulong.blog.shiro.service.imp;

import com.liyulong.blog.main.common.result.Result;
import com.liyulong.blog.main.common.util.JwtUtil;
import com.liyulong.blog.main.common.util.RedisUtil;
import com.liyulong.blog.main.mapper.sys.SysUserMapper;
import com.liyulong.blog.shiro.MyToken;
import com.liyulong.blog.shiro.service.KaptchaService;
import com.liyulong.blog.shiro.service.SysUserTokenService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SysUserTokenServiceImp implements SysUserTokenService {

    @Autowired
    private RedisUtil redisUtil;

    @Autowired
    private KaptchaService kaptchaService;

    @Autowired
    private SysUserMapper sysUserMapper;

    @Override
    public Result login(Integer userId) {
        //生成一个token
//        String token = JwtUtil.createToken(userId);
//
//        String tokenKey = RedisConstant.USER_TOKEN + token;
//        String userIdKey = RedisConstant.USER_TOKEN + userId;
//
//        //判断是否已经生成过token
//        String tokenInRedis = redisUtil.get(userIdKey);
//        if(!StringUtils.isEmpty(tokenInRedis)){
//            //删除原来的token
//            redisUtil.delete(RedisConstant.USER_TOKEN + tokenInRedis);
//        }
//
//        //将token存入redis
//        redisUtil.set(tokenKey,userId,60 * 2 * 60);
//        redisUtil.set(userIdKey,token,60 * 2 * 60);
//        return ResultUtil.success(token);

        String tokenStr = this.loginSuccess(userId + "");
        Subject subject = SecurityUtils.getSubject();
        AuthenticationToken token = new MyToken(tokenStr);
        subject.login(token);

        //登录成功
        return new Result(true,2000,"登录成功",tokenStr);

    }

    @Override
    public void logout(String token) {
        token = token.replaceAll("Bearer ","");
        Subject subject = SecurityUtils.getSubject();
        subject.logout();
    }

    private String loginSuccess(String userId){
        String currentTimeMillis = String.valueOf(System.currentTimeMillis());

        //生成token
        String token = JwtUtil.createToken(userId,currentTimeMillis);

        //更新refreshToken缓存的时间戳
        redisUtil.set("refresh_token:" + userId,currentTimeMillis,60 * 2 * 60);
        return token;
    }

}
