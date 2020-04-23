package com.liyulong.blog.manage.sys.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.liyulong.blog.main.common.util.PageUtils;
import com.liyulong.blog.main.pojo.sys.SysRole;
import com.liyulong.blog.main.pojo.sys.SysUser;

import java.io.UnsupportedEncodingException;
import java.util.List;
import java.util.Map;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author lihanlu
 * @since 2020-03-24
 */
public interface SysUserService extends IService<SysUser> {

    /**
     * 查询用户菜单
     * @param userId id
     * @return
     */
    List<Integer> queryAllMenuId(Integer userId);

    /**
     * 分页查询用户信息
     * @param user
     * @return
     */
    PageUtils queryPage(SysUser user,int page,int size);

    /**
     * 更新密码
     * @param userId
     * @param password
     * @param newPassword
     * @return
     */
    boolean updatePassword(Integer userId,String password,String newPassword);

    /**
     * 批量删除用户
     * @param userIds
     */
    void deleteBatch(Integer[] userIds);

    /**
     * 添加用户
     * @param user 用户
     * @return true
     */
    void createUser(SysUser user);

    /**
     * 查询用户的角色
     * @return
     */
    List<SysRole> queryUserRole(Integer userId);


    /**
     * 上传用户头像
     * @param path 头像路径
     */
    void uploadAvatar(String path) throws UnsupportedEncodingException;
}
