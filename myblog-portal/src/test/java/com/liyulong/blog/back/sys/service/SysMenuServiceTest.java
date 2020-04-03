package com.liyulong.blog.back.sys.service;

import com.liylong.blog.portal.MyblogApplication;
import com.liyulong.blog.main.pojo.sys.SysMenu;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@SpringBootTest(classes = MyblogApplication.class)
@RunWith(SpringRunner.class)
public class SysMenuServiceTest {

    @Autowired
    private SysMenuService menuService;

    @Test
    public void listUserMenu() {
        List<SysMenu> list = menuService.listUserMenu(1);
        list.forEach(System.out::println);
    }

    @Test
    public void queryListParentId() {
    }
}