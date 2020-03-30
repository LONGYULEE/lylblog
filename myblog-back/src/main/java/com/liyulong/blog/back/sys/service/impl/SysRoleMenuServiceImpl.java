package com.liyulong.blog.back.sys.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.liyulong.blog.back.sys.service.SysRoleMenuService;
import com.liyulong.blog.main.mapper.sys.SysRoleMenuMapper;
import com.liyulong.blog.main.pojo.sys.SysRoleMenu;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * <p>
 * 角色与菜单对应关系 服务实现类
 * </p>
 *
 * @author lihanlu
 * @since 2020-03-24
 */
@Service
public class SysRoleMenuServiceImpl extends ServiceImpl<SysRoleMenuMapper, SysRoleMenu> implements SysRoleMenuService {

    @Override
    public void saveOrUpdate(Integer roleId, List<Integer> menuIdList) {
        //删除角色对应的菜单
        baseMapper.delete(new QueryWrapper<SysRoleMenu>().lambda()
                .eq(roleId!=null,SysRoleMenu::getRoleId,roleId));
        if(menuIdList.size() == 0){
            return ;
        }
        //保存角色菜单关系
        List<SysRoleMenu> list = new ArrayList<>(menuIdList.size());
        for (Integer menuId: menuIdList) {
            SysRoleMenu roleMenu = new SysRoleMenu();
            roleMenu.setMenuId(menuId);
            roleMenu.setRoleId(roleId);
            list.add(roleMenu);
        }
        this.saveBatch(list);
    }

    @Override
    public List<Integer> queryMenuIdList(Integer roleId) {
        return baseMapper.queryMenuIdListByRoleId(roleId);
    }

    @Override
    public void deleteBatchByRoleId(Integer[] roleIds) {
        Arrays.stream(roleIds).forEach(roleId -> {
            baseMapper.delete(new UpdateWrapper<SysRoleMenu>().lambda()
                    .eq(roleId!=null,SysRoleMenu::getRoleId,roleId));
        });
    }
}
