package com.liyulong.blog.main.mapper.sys;

import com.liylong.blog.portal.MyblogApplication;
import com.liyulong.blog.main.pojo.sys.SysMenu;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.junit.Assert.*;

@SpringBootTest(classes = MyblogApplication.class)
@RunWith(SpringRunner.class)
public class SysMenuMapperTest {

    @Autowired
    private SysMenuMapper menuMapper;

    @Test
    public void queryNotButtonList() {
        List<SysMenu> list = menuMapper.queryNotButtonList();
        for (SysMenu menu : list) {
            System.out.println(menu);
        }
    }

    @Test
    public void queryListParentId() {
        List<SysMenu> list = menuMapper.queryListParentId(0);
        for (SysMenu menu : list) {
            System.out.println(menu);
        }
    }
}