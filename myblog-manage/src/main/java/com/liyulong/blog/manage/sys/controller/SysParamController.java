package com.liyulong.blog.manage.sys.controller;


import com.liyulong.blog.manage.sys.service.SysParamService;
import com.liyulong.blog.main.common.result.Result;
import com.liyulong.blog.main.common.result.ResultUtil;
import com.liyulong.blog.main.common.util.PageUtils;
import com.liyulong.blog.main.common.validator.ValidatorUtils;
import com.liyulong.blog.main.pojo.sys.SysParam;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 系统参数 前端控制器
 * </p>
 *
 * @author lihanlu
 * @since 2020-03-24
 */
@RestController
@RequestMapping("/admin/sys/param")
public class SysParamController {

    @Autowired
    private SysParamService paramService;

    /**
     * 分页列表
     * @return
     */
    @GetMapping("/list")
    @RequiresPermissions("sys:param:list")
    public Result getParamList(@RequestParam Map<String,Object> map){
        PageUtils page = paramService.queryPage(map);
        return ResultUtil.success(page);
    }

    /**
     * 查询所有参数
     * @return
     */
    @GetMapping("/all")
    public Result listAll(){
        List<SysParam> list = paramService.list(null);
        return ResultUtil.success(list);
    }

    /**
     * 通过参数id获取参数详情
     * @param id
     * @return
     */
    @GetMapping("/info")
    @RequiresPermissions("sys:param:info")
    public Result info(@RequestParam String id){
        SysParam param = paramService.getById(id);
        return ResultUtil.success(param);
    }

    /**
     * 保存系统参数
     * @param param
     * @return
     */
    @PostMapping("/save")
    @RequiresPermissions("sys:param:save")
    public Result save(@RequestBody SysParam param){
        ValidatorUtils.validateEntity(param);
        paramService.save(param);
        return ResultUtil.success();
    }

    /**
     * 更新系统参数
     * @param param
     * @return
     */
    @PostMapping("/update")
    @RequiresPermissions("sys:param:update")
    public Result update(@RequestBody SysParam param){
        ValidatorUtils.validateEntity(param);
        paramService.updateById(param);
        return ResultUtil.success();
    }

    /**
     * 批量删除系统参数
     * @return
     */
    @DeleteMapping("/delte")
    @RequiresPermissions("sys:param:delete")
    public Result delete(String[] ids){
        paramService.removeByIds(Arrays.asList(ids));
        return ResultUtil.success();
    }

}
