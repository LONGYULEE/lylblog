package com.liyulong.blog.manage.article.controller;


import com.liyulong.blog.main.common.result.Result;
import com.liyulong.blog.main.common.result.ResultUtil;
import com.liyulong.blog.main.common.util.QiNiuUtil;
import org.springframework.web.bind.annotation.*;

import org.springframework.stereotype.Controller;

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

    @GetMapping("/getUpToken")
    public Result getUpToken(){
        String upToken = QiNiuUtil.getUpToken();
        return ResultUtil.success(upToken);
    }

}
