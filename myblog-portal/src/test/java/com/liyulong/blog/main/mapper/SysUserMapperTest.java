package com.liyulong.blog.main.mapper;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.liylong.blog.portal.MyblogApplication;
import com.liyulong.blog.main.mapper.sys.SysUserMapper;
import com.liyulong.blog.main.pojo.sys.SysUser;
import org.apache.shiro.crypto.hash.Md5Hash;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@SpringBootTest(classes = MyblogApplication.class)
@RunWith(SpringRunner.class)
public class SysUserMapperTest {

    @Autowired
    private SysUserMapper sysUserMapper;

    /**
     * 生成加密密码
     */
    @Test
    public void testCreatePassword(){

        //加密字段
        String str = "admin";
        //盐值
        String slat = "QJ58UqYg";
        //加密次数
        int i = 2;

        Md5Hash md5 = new Md5Hash(str,slat,i);
        System.out.println(md5);

    }

    /**
     * 当前时间
     */
    @Test
    public void testSystemTime(){
        System.out.println(System.currentTimeMillis());
    }

    @Test
    public void testSelectOne(){
        String username = "admin";
        SysUser user = sysUserMapper.selectOne(new QueryWrapper<SysUser>().lambda().eq(SysUser::getUsername, "admin"));
        System.out.println(user);
    }

}