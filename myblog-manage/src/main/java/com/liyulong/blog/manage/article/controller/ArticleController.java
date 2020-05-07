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

    /**
     * 获取七牛云上传token
     * @return token
     */
    @GetMapping("/getUpToken")
    public Result getUpToken(){
        String upToken = QiNiuUtil.getUpToken();
        return ResultUtil.success(upToken);
    }

    /**
     * 删除七牛云中的文件
     * @param key 文件名
     * @return 是否删除成功
     */
    @GetMapping("/deleteFile")
    public Result deleteFile(@RequestParam String key){
        Boolean b = QiNiuUtil.deleteFile(key);
        return ResultUtil.success(b);
    }

}
