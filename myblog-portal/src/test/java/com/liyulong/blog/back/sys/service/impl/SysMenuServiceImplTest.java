package com.liyulong.blog.back.sys.service.impl;

import com.alibaba.druid.sql.dialect.mysql.ast.statement.MySqlCreateUserStatement;
import com.liylong.blog.portal.MyblogApplication;
import com.liyulong.blog.back.sys.service.SysMenuService;
import com.liyulong.blog.back.sys.service.SysUserService;
import com.liyulong.blog.main.pojo.sys.SysMenu;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runner.Runner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.junit.Assert.*;

@SpringBootTest(classes = MyblogApplication.class)
@RunWith(SpringRunner.class)
public class SysMenuServiceImplTest {

    @Autowired
    private SysMenuService menuService;

    @Autowired
    private SysUserService userService;

    //查询用户所有的菜单
    @Test
    public void listUserMenu() {
        List<SysMenu> menus = menuService.listUserMenu(1);
        menus.forEach(System.out::println);
        /**
         * SysMenu(menuId=30, parentId=0, name=博文管理, url=null, perms=null, type=0, icon=article, orderNum=0, parentName=null, open=null, list=[SysMenu(menuId=31, parentId=30, name=新增博文, url=article/article-add-or-update, perms=article:save,article:update, type=1, icon=add, orderNum=0, parentName=null, open=null, list=null), SysMenu(menuId=32, parentId=30, name=博文列表, url=article/article, perms=null, type=1, icon=list, orderNum=0, parentName=null, open=null, list=null)])
         * SysMenu(menuId=66, parentId=0, name=阅读管理, url=null, perms=null, type=0, icon=read, orderNum=1, parentName=null, open=null, list=[SysMenu(menuId=73, parentId=66, name=新增笔记, url=book/note-add-or-update, perms=, type=1, icon=add, orderNum=0, parentName=null, open=null, list=null), SysMenu(menuId=67, parentId=66, name=新增图书, url=book/book-add-or-update, perms=, type=1, icon=add, orderNum=1, parentName=null, open=null, list=null), SysMenu(menuId=68, parentId=66, name=笔记管理, url=book/note, perms=null, type=1, icon=list, orderNum=2, parentName=null, open=null, list=null), SysMenu(menuId=61, parentId=66, name=图书管理, url=book/book, perms=null, type=1, icon=list, orderNum=3, parentName=null, open=null, list=null)])
         * SysMenu(menuId=43, parentId=0, name=运营管理, url=null, perms=null, type=0, icon=operation, orderNum=2, parentName=null, open=null, list=[SysMenu(menuId=38, parentId=43, name=分类管理, url=operation/category, perms=null, type=1, icon=category, orderNum=6, parentName=null, open=null, list=null), SysMenu(menuId=50, parentId=43, name=标签管理, url=operation/tag, perms=null, type=1, icon=tag, orderNum=6, parentName=null, open=null, list=null), SysMenu(menuId=74, parentId=43, name=友链管理, url=operation/link, perms=null, type=1, icon=link, orderNum=6, parentName=null, open=null, list=null), SysMenu(menuId=79, parentId=43, name=推荐管理, url=operation/recommend, perms=null, type=1, icon=recommend, orderNum=6, parentName=null, open=null, list=null)])
         * SysMenu(menuId=1, parentId=0, name=系统管理, url=null, perms=null, type=0, icon=config, orderNum=3, parentName=null, open=null, list=[SysMenu(menuId=2, parentId=1, name=管理员列表, url=sys/user, perms=null, type=1, icon=admin, orderNum=1, parentName=null, open=null, list=null), SysMenu(menuId=3, parentId=1, name=角色管理, url=sys/role, perms=null, type=1, icon=role, orderNum=2, parentName=null, open=null, list=null), SysMenu(menuId=4, parentId=1, name=菜单管理, url=sys/menu, perms=null, type=1, icon=menu, orderNum=3, parentName=null, open=null, list=null), SysMenu(menuId=45, parentId=1, name=系统参数, url=sys/param, perms=null, type=1, icon=param, orderNum=4, parentName=null, open=null, list=null), SysMenu(menuId=5, parentId=1, name=SQL监控, url=http://localhost:8080/dbblog/druid/sql.html, perms=null, type=1, icon=config, orderNum=5, parentName=null, open=null, list=null)])
         */
    }

    //查询所有的父菜单，一级菜单
    @Test
    public void queryListParentId() {
        List<SysMenu> list = menuService.queryListParentId(0);
        list.forEach(System.out::println);
        //SysMenu(menuId=30, parentId=0, name=博文管理, url=null, perms=null, type=0, icon=article, orderNum=0, parentName=null, open=null, list=null)
        //SysMenu(menuId=66, parentId=0, name=阅读管理, url=null, perms=null, type=0, icon=read, orderNum=1, parentName=null, open=null, list=null)
        //SysMenu(menuId=43, parentId=0, name=运营管理, url=null, perms=null, type=0, icon=operation, orderNum=2, parentName=null, open=null, list=null)
        //SysMenu(menuId=1, parentId=0, name=系统管理, url=null, perms=null, type=0, icon=config, orderNum=3, parentName=null, open=null, list=null)
    }

    @Test
    public void testQueryListParentId() {
        List<SysMenu> list = menuService.queryListParentId(0, userService.queryAllMenuId(1));
        list.forEach(System.out::println);
        //SysMenu(menuId=30, parentId=0, name=博文管理, url=null, perms=null, type=0, icon=article, orderNum=0, parentName=null, open=null, list=null)
        //SysMenu(menuId=66, parentId=0, name=阅读管理, url=null, perms=null, type=0, icon=read, orderNum=1, parentName=null, open=null, list=null)
        //SysMenu(menuId=43, parentId=0, name=运营管理, url=null, perms=null, type=0, icon=operation, orderNum=2, parentName=null, open=null, list=null)
        //SysMenu(menuId=1, parentId=0, name=系统管理, url=null, perms=null, type=0, icon=config, orderNum=3, parentName=null, open=null, list=null)
    }

    @Test
    public void queryNotButtonList() {
    }

    @Test
    public void getUserMenuList() {
    }

    @Test
    public void delete() {
    }

    //根据userId查询所有menuId
    @Test
    public void testGetAllMenuId(){
        List<Integer> list = userService.queryAllMenuId(1);
        System.out.println(list);
        //[1, 2, 3, 4, 5, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25, 26, 30, 31, 32, 33, 34, 38, 39, 40, 41, 42, 43, 45, 46, 47, 48, 49, 50
        // , 51, 52, 53, 54, 61, 62, 63, 64, 65, 66, 67, 68, 69, 70, 71, 72, 73, 74, 75, 76, 77, 78, 79, 80, 81, 82, 83, 84]
    }

    @Test
    public void testGetMenuTreeList(){
//        menuService.q
    }

}