package com.liyulong.blog.back.sys.controller;

import com.liyulong.blog.back.sys.service.SysUserService;
import com.liyulong.blog.main.common.result.Result;
import com.liyulong.blog.main.common.result.ResultUtil;
import com.liyulong.blog.main.common.validator.AddGroup;
import com.liyulong.blog.main.common.validator.ValidatorUtils;
import com.liyulong.blog.main.pojo.sys.SysUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/admin/sys/user")
public class SysUserController {

    @Autowired
    private SysUserService sysUserService;

    /**
     * 创建用户
     * @param user
     * @return
     */
    @PostMapping("/createUser")
    public Result createUser(@RequestBody SysUser user){
        ValidatorUtils.validateEntity(user, AddGroup.class);
        sysUserService.createUser(user);
        return ResultUtil.success();
    }

    @DeleteMapping("/deleteBatch")
    public Result deleteBatch(@RequestBody Integer[] userIds){
        sysUserService.deleteBatch(userIds);
        return ResultUtil.success();
    }

    @GetMapping("/getUserRole")
    public Result getUserRole(@RequestParam Integer userId){
        return ResultUtil.success(sysUserService.queryUserRole(userId));
    }

}
