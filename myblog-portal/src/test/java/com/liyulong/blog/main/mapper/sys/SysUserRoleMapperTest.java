package com.liyulong.blog.main.mapper.sys;

import com.liylong.blog.portal.MyblogApplication;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = MyblogApplication.class)
public class SysUserRoleMapperTest {

    @Autowired
    private SysUserRoleMapper userRoleMapper;

    @Test
    public void queryRoleIdListByUserId() {
        userRoleMapper.queryRoleIdListByUserId(1);
    }
}