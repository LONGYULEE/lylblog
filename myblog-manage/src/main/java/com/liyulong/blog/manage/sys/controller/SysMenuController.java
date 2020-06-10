package com.liyulong.blog.manage.sys.controller;


import com.liyulong.blog.manage.sys.service.SysMenuService;
import com.liyulong.blog.main.common.context.UserContext;
import com.liyulong.blog.main.common.enums.MenuTypeEnum;
import com.liyulong.blog.main.common.exception.MyException;
import com.liyulong.blog.main.common.result.Result;
import com.liyulong.blog.main.common.result.ResultUtil;
import com.liyulong.blog.main.pojo.sys.SysMenu;
import com.liyulong.blog.shiro.service.ShiroService;
import org.apache.commons.lang.StringUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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
    public Result list(){
        List<SysMenu> menuList = menuService.list(null);
        menuList.forEach(item -> {
            SysMenu parentMenu = menuService.getById(item.getParentId());
            if(parentMenu != null){
                item.setParentName(parentMenu.getName());
            }
        });
        return ResultUtil.success(menuList);
    }

    /**
     * 选择菜单(添加、修改菜单)
     */
    @GetMapping("/select")
    @RequiresPermissions("sys:menu:select")
    public Result select(){
        //查询列表数据
        List<SysMenu> menuList = menuService.queryNotButtonList();
        //添加一级菜单
        SysMenu root = new SysMenu();
        root.setMenuId(0);
        root.setName("一级菜单");
        root.setParentId(-1);
        root.setOpen(true);
        menuList.add(root);
        return ResultUtil.success(menuList);
    }

    /**
     * 查询单个菜单信息
     * @param menuId
     * @return
     */
    @GetMapping("/info/{menuId}")
    @RequiresPermissions("sys:menu:info")
    public Result info(@PathVariable("menuId") Integer menuId){
        SysMenu menu = menuService.getById(menuId);
        return ResultUtil.success(menu);
    }

    /**
     * 新增菜单
     * @param menu
     * @return
     */
    @PostMapping("/save")
    @RequiresPermissions("sys:menu:save")
    public Result save(@RequestBody SysMenu menu){
        //数据校验
        verifyForm(menu);
        menuService.save(menu);
        return ResultUtil.success();
    }

    /**
     * 更新菜单
     * @param menu
     * @return
     */
    @PutMapping("/update")
    @RequiresPermissions("sys:menu:update")
    public Result update(@RequestBody SysMenu menu){
        verifyForm(menu);
        menuService.updateById(menu);
        return ResultUtil.success();
    }

    /**
     * 删除菜单
     * @param menuId
     * @return
     */
    @DeleteMapping("/delete/{menuId}")
    @RequiresPermissions("sys:menu:delete")
    public Result delete(@PathVariable("menuId") Integer menuId){
        if(menuId <= 29){
            return ResultUtil.failure("系统菜单，不能删除");
        }

        //判断是否有子菜单或按钮
        List<SysMenu> menuList = menuService.queryListParentId(menuId);
        if(menuList.size() > 0){
            return ResultUtil.failure("请先删除子菜单或按钮");
        }
        menuService.delete(menuId);
        return ResultUtil.success();
    }

    /**
     * 验证参数是否正确
     * @param menu
     */
    public void verifyForm(SysMenu menu){
        if (StringUtils.isBlank(menu.getName())) {
            throw new MyException("菜单名称不能为空");
        }

        if (menu.getParentId() == null) {
            throw new MyException("上级菜单不能为空");
        }

        //菜单
        if (menu.getType() == MenuTypeEnum.MENU.getValue()) {
            if (StringUtils.isBlank(menu.getUrl())) {
                throw new MyException("菜单URL不能为空");
            }
        }

        //上级菜单类型
        int parentType = MenuTypeEnum.CATALOG.getValue();
        if (menu.getParentId() != 0) {
            SysMenu parentMenu = menuService.getById(menu.getParentId());
            parentType = parentMenu.getType();
        }

        //目录、菜单
        if (menu.getType() == MenuTypeEnum.CATALOG.getValue() ||
                menu.getType() == MenuTypeEnum.MENU.getValue()) {
            if (parentType != MenuTypeEnum.CATALOG.getValue()) {
                throw new MyException("上级菜单只能为目录类型");
            }
        }

        //按钮
        if (menu.getType() == MenuTypeEnum.BUTTON.getValue()) {
            if (parentType != MenuTypeEnum.MENU.getValue()) {
                throw new MyException("上级菜单只能为菜单类型");
            }
        }
    }

}
