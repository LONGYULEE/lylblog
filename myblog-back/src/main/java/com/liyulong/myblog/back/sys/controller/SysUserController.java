package com.liyulong.myblog.back.sys.controller;

import com.liyulong.blog.main.common.result.Result;
import com.liyulong.blog.main.common.result.ResultUtil;
import com.liyulong.blog.main.common.validator.AddGroup;
import com.liyulong.blog.main.common.validator.ValidatorUtils;
import com.liyulong.blog.main.pojo.sys.SysUser;
import com.liyulong.myblog.back.sys.service.SysUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/admin/sys/user")
public class SysUserController {

    @Autowired
    private SysUserService sysUserService;

    @PostMapping("/createUser")
    public Result createUser(@RequestBody SysUser user){
        ValidatorUtils.validateEntity(user, AddGroup.class);
        sysUserService.createUser(user);
        return ResultUtil.success();
    }

}
