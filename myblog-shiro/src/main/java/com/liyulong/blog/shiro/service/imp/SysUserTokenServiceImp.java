package com.liyulong.blog.shiro.service.imp;

import com.liyulong.blog.main.common.constant.RedisConstant;
import com.liyulong.blog.main.common.result.Result;
import com.liyulong.blog.main.common.result.ResultUtil;
import com.liyulong.blog.main.common.util.JwtUtil;
import com.liyulong.blog.main.common.util.RedisUtil;
import com.liyulong.blog.main.mapper.sys.SysUserMapper;
import com.liyulong.blog.shiro.service.KaptchaService;
import com.liyulong.blog.shiro.service.SysUserTokenService;
import org.apache.commons.lang.StringUtils;
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
    public void logout(String token) {
        token = token.replaceAll("Bearer ","");
        String userId = JwtUtil.getClaim(token,"userId");
        redisUtil.delete(RedisConstant.USER_TOKEN + userId);
        redisUtil.delete(RedisConstant.USER_TOKEN + token);
    }

    @Override
    public Result createToken(Integer userId) {
        //生成一个token
        String token = JwtUtil.createToken(userId + "",System.currentTimeMillis() + "");
        String tokenKey = RedisConstant.USER_TOKEN + token;
        String userIdKey = RedisConstant.USER_TOKEN + userId;
        //判断是否生成过token
        String tokenInRedis = redisUtil.get(userIdKey);
        if(!StringUtils.isEmpty(tokenInRedis)){
            //删除原来的token
            redisUtil.delete(RedisConstant.USER_TOKEN + token);
        }
        //将token存入redis
        redisUtil.set(tokenKey,userId,60 * 2 * 60);
        redisUtil.set(userIdKey,token,60 * 2 * 60);
        return ResultUtil.success(token);
    }

}
