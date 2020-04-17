package com.liyulong.blog.back.sys.service.impl;

import com.liylong.blog.portal.MyblogApplication;
import com.liyulong.blog.back.sys.service.SysUserService;
import com.liyulong.blog.main.common.util.PageUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.*;

@SpringBootTest(classes = MyblogApplication.class)
@RunWith(SpringRunner.class)
public class SysUserServiceImplTest {

    @Autowired
    private SysUserService userService;

    @Test
    public void queryPage() {
//        Map<String,Object> map = new HashMap<>();
//        map.put("userId",1);

    }
}