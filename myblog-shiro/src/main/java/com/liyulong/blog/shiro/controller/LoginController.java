package com.liyulong.blog.shiro.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.liyulong.blog.main.common.exception.MyException;
import com.liyulong.blog.main.common.result.Result;
import com.liyulong.blog.main.common.result.ResultUtil;
import com.liyulong.blog.main.mapper.sys.SysUserMapper;
import com.liyulong.blog.main.pojo.sys.SysUser;
import com.liyulong.blog.main.pojo.sys.form.LoginForm;
import com.liyulong.blog.shiro.service.KaptchaService;
import com.liyulong.blog.shiro.service.SysUserTokenService;
import org.apache.shiro.crypto.hash.Md5Hash;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/admin")
public class LoginController{

    @Autowired
    private SysUserMapper sysUserMapper;

    @Autowired
    private SysUserTokenService sysUserTokenService;

    @Autowired
    private KaptchaService kaptchaService;

    @GetMapping("/sys/getKaptcha")
    public String getKaptcha(String username){
        return kaptchaService.getKaptcha(username);
    }

    @PostMapping("/sys/login")
    public Result login(@RequestBody LoginForm form){
        //判断验证码是否正确
        if(!kaptchaService.verifyKaptcha(form.getUsername(),form.getCaptcha())){
            throw new MyException("验证码错误");
        }

        //获取用户信息
        SysUser user = sysUserMapper.selectOne(new QueryWrapper<SysUser>()
                .lambda()
                .eq(SysUser::getUsername,form.getUsername()));
        //判断用户名密码是否正确
        if(user == null || !user.getPassword().equals(new Md5Hash(form.getPassword(),user.getSalt(),2).toString())){
            //用户名或密码错误
            return ResultUtil.failure("用户名或密码错误");
        }
        //判断账户是否锁定
        if(user.getStatus() == 0){
            return ResultUtil.failure("账号已被锁定");
        }
        return sysUserTokenService.createToken(user.getUserId());

    }

    @GetMapping("/sys/logout")
    public Result logout(HttpServletRequest request){
        sysUserTokenService.logout(request.getHeader("Authorization"));
        return ResultUtil.success();
    }

}
