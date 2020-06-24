package com.liyulong.blog.manage.sys.controller;

import com.liyulong.blog.manage.sys.service.SysUserRoleService;
import com.liyulong.blog.manage.sys.service.SysUserService;
import com.liyulong.blog.main.common.context.UserContext;
import com.liyulong.blog.main.common.exception.MyException;
import com.liyulong.blog.main.common.result.Result;
import com.liyulong.blog.main.common.result.ResultUtil;
import com.liyulong.blog.main.common.validator.AddGroup;
import com.liyulong.blog.main.common.validator.UpdateGroup;
import com.liyulong.blog.main.common.validator.ValidatorUtils;
import com.liyulong.blog.main.pojo.sys.SysUser;
import org.apache.commons.lang.ArrayUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.crypto.hash.Md5Hash;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
    @RequiresPermissions("sys:user:save")
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
        if(ArrayUtils.contains(userIds,1)){
            throw new MyException("不能删除系统管理员");
        }
        if(ArrayUtils.contains(userIds,UserContext.getCurrentUser().getUserId())){
            throw new MyException("不能删除当前登录用户");
        }
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
    @RequiresPermissions("sys:user:update")
    public Result update(@RequestBody SysUser user){
        ValidatorUtils.validateEntity(user, UpdateGroup.class);
        user.setCreateUserId(UserContext.getCurrentUser().getUserId());
        user.setPassword(userService.getUpdatePassword(user));
        userService.updateById(user);
        return ResultUtil.success();
    }

    /**
     * 获取当前登录用户的信息
     * @return
     */
    @GetMapping("/info")
    public Result info(){
        SysUser user = userService.getById(UserContext.getUserId());
        Map<String,Object> map = new HashMap<>();
        map.put("id",user.getUserId());
        map.put("username",user.getUsername());
        map.put("avatar",user.getAvatar());
        map.put("token",UserContext.getCurrentUser().getToken());
        return ResultUtil.success(map);
    }

    /**
     * 通过id查询单个用户信息
     * @param userId
     * @return
     */
    @GetMapping("/info/{userId}")
    @RequiresPermissions("sys:user:info")
    public Result info(@PathVariable(name = "userId") Integer userId){
        SysUser user = userService.getById(userId);

        //获取角色信息
        List<Integer> roleList = userRoleService.queryRoleIdList(userId);
        user.setRoleIdList(roleList);
        return ResultUtil.success(user);
    }

    /**
     * 查看用户列表
     * @param map username,page,size
     * @return
     */
    @GetMapping("/list")
    @RequiresPermissions("sys:user:list")
    public Result getList(@RequestParam Map<String,Object> map){
        return ResultUtil.success(userService.queryPage(map));
    }

}
