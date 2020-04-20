package com.liyulong.blog.back.sys.controller;


import com.liyulong.blog.back.sys.service.SysRoleService;
import com.liyulong.blog.main.common.result.PageResult;
import com.liyulong.blog.main.common.result.Result;
import com.liyulong.blog.main.common.result.ResultUtil;
import com.liyulong.blog.main.common.validator.AddGroup;
import com.liyulong.blog.main.common.validator.ValidatorUtils;
import com.liyulong.blog.main.pojo.sys.SysRole;
import io.swagger.annotations.ApiImplicitParam;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 * 角色 前端控制器
 * </p>
 *
 * @author lihanlu
 * @since 2020-03-24
 */
@RestController
@RequestMapping("/admin/sys/role")
public class SysRoleController {

    @Autowired
    private SysRoleService roleService;

    /**
     * 创建一个角色
     * @param role
     * @return
     */
    @PostMapping("/createRole")
    public Result createRole(@RequestBody SysRole role){
        ValidatorUtils.validateEntity(role, AddGroup.class);
        roleService.createRole(role);
        return ResultUtil.success();
    }

    /**
     * 批量删除角色
     * @param roleIds
     * @return
     */
    @DeleteMapping("/deleteByIds")
    @RequiresPermissions("sys:role:delete")
    public Result deleteRole(@RequestParam Integer[] roleIds) {
        try {
            roleService.deleteByIds(roleIds);
        } catch (Exception e) {
            return ResultUtil.failure(e.getMessage());
        }
        return ResultUtil.success();
    }

    /**
     * 获取角色列表
     * @return
     */
    @GetMapping("/list")
    @RequiresPermissions("sys:role:list")
    public Result getRoleList(@RequestParam(required = false) String roleName,
                                  @RequestParam(value = "page",required = false,defaultValue = "1") Integer page,
                                  @RequestParam(value = "size",required = false,defaultValue = "10") Integer size){
        return ResultUtil.success(roleService.queryRoleByPage(roleName, page, size));
    }



}
