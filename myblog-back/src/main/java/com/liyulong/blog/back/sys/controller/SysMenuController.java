package com.liyulong.blog.back.sys.controller;


import com.liyulong.blog.back.sys.service.SysMenuService;
import com.liyulong.blog.main.common.context.UserContext;
import com.liyulong.blog.main.common.result.Result;
import com.liyulong.blog.main.common.result.ResultUtil;
import com.liyulong.blog.main.pojo.sys.SysMenu;
import com.liyulong.blog.shiro.service.ShiroService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * <p>
 * 菜单管理 前端控制器
 * </p>
 *
 * @author lihanlu
 * @since 2020-03-24
 */
@RestController
@RequestMapping("/admin/sys/menu")
public class SysMenuController {

    @Autowired
    private SysMenuService menuService;

    @Autowired
    private ShiroService shiroService;

    @GetMapping("/nav")
    public Result nav(){
        List<SysMenu> menuList = menuService.listUserMenu(UserContext.getUserId());
        Set<String> permissions = shiroService.queryPermissions(UserContext.getUserId());
        Map<String,Object> map = new HashMap<>();
        map.put("menuList",menuList);
        map.put("permissions",permissions);
        return ResultUtil.success(map);
    }

    /**
     * 获取所有菜单
     */
    @GetMapping("/list")
    @RequiresPermissions("sys:menu:list")
    public List<SysMenu> list(){
        List<SysMenu> menuList = menuService.list(null);
        menuList.forEach(item -> {
            SysMenu parentMenu = menuService.getById(item.getParentId());
            if(parentMenu != null){
                item.setParentName(parentMenu.getName());
            }
        });
        return menuList;
    }

}
