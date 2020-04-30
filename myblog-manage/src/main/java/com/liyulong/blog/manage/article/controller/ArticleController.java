package com.liyulong.blog.manage.article.controller;


import com.liyulong.blog.main.common.result.Result;
import com.liyulong.blog.main.common.result.ResultUtil;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

/**
 * <p>
 * 文章 前端控制器
 * </p>
 *
 * @author lihanlu
 * @since 2020-03-24
 */
@RestController
@RequestMapping("/admin/sys/article")
public class ArticleController {

    @PostMapping("/imageUpload")
    public Result imgUpload(HttpServletRequest request){
        System.out.println(request.toString());
        return ResultUtil.success();
    }

}
