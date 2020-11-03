package com.liyulong.blog.index.article.service;

import com.liylong.blog.portal.MyblogApplication;
import com.liyulong.blog.main.common.util.PageUtils;
import com.liyulong.blog.main.mapper.operation.NumberMapper;
import com.liyulong.blog.main.pojo.article.vo.ArticleVO;
import com.liyulong.blog.main.pojo.operation.Number;
import lombok.experimental.Accessors;
import org.apache.shiro.crypto.hash.Md5Hash;
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
public class ArticleServiceTest {

    @Autowired
    private ArticleService articleService;

    @Autowired
    private NumberMapper numberMapper;

    @Test
    public void queryPageCondition() {
        Map<String,Object> map = new HashMap<>();
        PageUtils utils = articleService.queryPageCondition(map);
        System.out.println(utils);
    }


    @Test
    public void getScret(){
        Md5Hash md5Hash = new Md5Hash("password","salt",2);
        System.out.println(md5Hash.toString());
    }

    @Test
    public void test01(){
        Number number = numberMapper.getNumbers();
        System.out.println(number);
    }
}