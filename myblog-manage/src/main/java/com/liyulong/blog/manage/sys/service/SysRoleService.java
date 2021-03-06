package com.liyulong.blog.manage.sys.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.liyulong.blog.main.common.util.PageUtils;
import com.liyulong.blog.main.pojo.sys.SysRole;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 角色 服务类
 * </p>
 *
 * @author lihanlu
 * @since 2020-03-24
 */
public interface SysRoleService extends IService<SysRole> {

    /**
     * 批量删除用户下面的角色信息
     * @param userIds 用户ids
     */
    void deleteBatchIds(Integer[] userIds);

    /**
     * 新增角色
     * @param role 角色
     */
    void createRole(SysRole role);

    /**
     * 更新角色
     * @param role
     */
    void updateRole(SysRole role);

    /**
     * 查询用户角色信息
     * @return
     */
    PageUtils queryRoleByPage(Map<String,Object> map);

    /**
     * 查询user下面的所有角色信息
     * @param userId id
     * @return
     */
    PageUtils queryRoleByUserId(Long userId);

    void deleteByIds(Integer[] roleId) throws Exception;

    SysRole getRoleById(Integer roleId);
}
