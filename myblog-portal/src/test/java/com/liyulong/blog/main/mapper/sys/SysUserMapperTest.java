package com.liyulong.blog.main.mapper.sys;

import com.liylong.blog.portal.MyblogApplication;
import com.liyulong.blog.main.common.context.UserContext;
import com.liyulong.blog.main.pojo.sys.SysUserToken;
import com.liyulong.blog.shiro.service.ShiroService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;
import java.util.Set;

import static org.junit.Assert.*;

@SpringBootTest(classes = MyblogApplication.class)
@RunWith(SpringRunner.class)
public class SysUserMapperTest {

    @Autowired
    private SysUserMapper userMapper;

    @Autowired
    private ShiroService shiroService;

    @Test
    public void queryAllPerms() {
        List<String> list = userMapper.queryAllPerms(7);
        for (String s : list) {
            System.out.println(s);
        }
    }

    @Test
    public void queryAllPermsService(){
        Set<String> strings = shiroService.queryPermissions(9);
        System.out.println(strings);
    }

    @Test
    public void getCurrentUserInfo(){
        SysUserToken currentUser = UserContext.getCurrentUser();
    }
}