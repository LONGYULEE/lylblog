package com.liyulong.blog.back.sys.controller;

import com.liyulong.blog.back.sys.service.SysUserRoleService;
import com.liyulong.blog.back.sys.service.SysUserService;
import com.liyulong.blog.main.common.context.UserContext;
import com.liyulong.blog.main.common.result.Result;
import com.liyulong.blog.main.common.result.ResultUtil;
import com.liyulong.blog.main.common.validator.AddGroup;
import com.liyulong.blog.main.common.validator.UpdateGroup;
import com.liyulong.blog.main.common.validator.ValidatorUtils;
import com.liyulong.blog.main.pojo.sys.SysUser;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/admin/sys/user")
public class SysUserController {

    @Autowired
    private SysUserService userService;

    @Autowired
    private SysUserRoleService userRoleService;

    /**
     * 创建用户
     * @param user
     * @return
     */
    @PostMapping("/createUser")
    public Result createUser(@RequestBody SysUser user){
        ValidatorUtils.validateEntity(user, AddGroup.class);
        userService.createUser(user);
        return ResultUtil.success();
    }

    /**
     * 批量删除用户
     * @param userIds
     * @return
     */
    @DeleteMapping("/deleteBatch")
    @RequiresPermissions("sys:user:delete")
    public Result deleteBatch(@RequestBody Integer[] userIds){
        userService.deleteBatch(userIds);
        return ResultUtil.success();
    }

    /**
     * 获取用户角色
     * @param userId
     * @return
     */
    @GetMapping("/getUserRole")
    public Result getUserRole(@RequestParam Integer userId){
        return ResultUtil.success(userService.queryUserRole(userId));
    }

    /**
     * 更新用户
     * @param user
     * @return
     */
    @PostMapping("/updateUser")
    public Result update(SysUser user){
        ValidatorUtils.validateEntity(user, UpdateGroup.class);
        user.setCreateUserId(UserContext.getCurrentUser().getUserId());
        userService.updateById(user);
        return ResultUtil.success();
    }

    /**
     * 获取当前登录用户的信息
     * @return
     */
    @GetMapping("/info")
    public Result info(){
        return ResultUtil.success(UserContext.getCurrentUser());
    }

}
