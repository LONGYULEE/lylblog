package com.liyulong.blog.manage.sys.controller;


import com.liyulong.blog.manage.sys.service.SysRoleService;
import com.liyulong.blog.main.common.context.UserContext;
import com.liyulong.blog.main.common.result.Result;
import com.liyulong.blog.main.common.result.ResultUtil;
import com.liyulong.blog.main.common.validator.AddGroup;
import com.liyulong.blog.main.common.validator.ValidatorUtils;
import com.liyulong.blog.main.pojo.sys.SysRole;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

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
     * 修改角色信息
     * @param role
     * @return
     */
    @PostMapping("/update")
    public Result updateRole(@RequestBody SysRole role){
        ValidatorUtils.validateEntity(role);
        roleService.updateById(role);
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
    public Result getRoleListByPage(@RequestParam Map<String,Object> map){
        return ResultUtil.success(roleService.queryRoleByPage(map));
    }

    @GetMapping("/select")
    @RequiresPermissions("sys:role:select")
    public Result getRoleList(){
        Map<String,Object> map = new HashMap<>();
        //如果不是管理员只能查询自己创建的角色列表
        if(UserContext.getUserId() == 1){
            map.put("createUserId",UserContext.getUserId());
        }
        Collection<SysRole> roleCollection = roleService.listByMap(map);
        return ResultUtil.success(roleCollection);
    }


}
