package com.liyulong.blog.manage.sys.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.liyulong.blog.main.pojo.sys.SysRoleMenu;

import java.util.List;

/**
 * <p>
 * 角色与菜单对应关系 服务类
 * </p>
 *
 * @author lihanlu
 * @since 2020-03-24
 */
public interface SysRoleMenuService extends IService<SysRoleMenu> {

    /**
     * 保存或更新角色菜单
     * @param roleId 角色id
     * @param menuIdList 菜单id
     */
    void saveOrUpdate(Integer roleId, List<Integer> menuIdList);

    /**
     * 查询角色菜单id
     * @param roleId 角色id
     * @return
     */
    List<Integer> queryMenuIdList(Integer roleId);

    /**
     * 批量删除角色下面的菜单
     * @param roleIds 角色id
     */
    void deleteBatchByRoleId(Integer[] roleIds);

}
