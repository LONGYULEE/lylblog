package com.liyulong.blog.back.sys.controller;


import com.liyulong.blog.back.sys.service.SysRoleService;
import com.liyulong.blog.main.common.result.Result;
import com.liyulong.blog.main.common.result.ResultUtil;
import com.liyulong.blog.main.common.validator.AddGroup;
import com.liyulong.blog.main.common.validator.ValidatorUtils;
import com.liyulong.blog.main.pojo.sys.SysRole;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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
    private SysRoleService sysRoleService;

    @PostMapping("/createRole")
    public Result createRole(@RequestBody SysRole role){
        ValidatorUtils.validateEntity(role, AddGroup.class);
        sysRoleService.createRole(role);
        return ResultUtil.success();
    }

    @DeleteMapping("/deleteByIds")
    public Result deleteRole(@RequestBody Integer[] roleIds){
        sysRoleService.deleteByIds(roleIds);
        return ResultUtil.success();
    }

}
