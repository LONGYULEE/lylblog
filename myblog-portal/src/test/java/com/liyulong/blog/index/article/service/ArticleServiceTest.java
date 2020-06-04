package com.liyulong.blog.index.article.service;

import com.liylong.blog.portal.MyblogApplication;
import com.liyulong.blog.main.common.util.PageUtils;
import com.liyulong.blog.main.pojo.article.vo.ArticleVO;
import lombok.experimental.Accessors;
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

    @Test
    public void queryPageCondition() {
        Map<String,Object> map = new HashMap<>();
        PageUtils utils = articleService.queryPageCondition(map);
        System.out.println(utils);
    }
}