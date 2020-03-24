package com.liyulong.myblog.back.sys.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.liyulong.blog.main.pojo.sys.SysUser;

import java.util.List;

public interface SysUserService extends IService<SysUser> {

    /**
     * 查询用户菜单
     * @param userId id
     * @return List
     */
    List<Integer> queryAllMenuId(Integer userId);

    /**
     * 分页查询用户信息
     * @param params
     * @return
     */
//    PageUtils queryPage(Map<String, Object> params);

    /**
     * 更新密码
     * @param userId id
     * @param password password
     * @param newPassword newPassword
     * @return flag
     */
    boolean updatePassword(Integer userId, String password, String newPassword);

    /**
     * 批量删除用户
     * @param userIds
     */
    void deleteBatch(Integer[] userIds);

}
