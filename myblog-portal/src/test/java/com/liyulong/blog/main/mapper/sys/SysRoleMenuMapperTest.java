package com.liyulong.blog.main.mapper.sys;

import com.liylong.blog.portal.MyblogApplication;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@SpringBootTest(classes = MyblogApplication.class)
@RunWith(SpringRunner.class)
public class SysRoleMenuMapperTest {

    @Autowired
    private SysRoleMenuMapper roleMenuMapper;

    @Test
    public void queryMenuIdListByRoleId() {
        List<Integer> list = roleMenuMapper.queryMenuIdListByRoleId(1);
        System.out.println(list);
    }
}