package com.liyulong.blog.back.sys.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.liyulong.blog.back.sys.service.SysMenuService;
import com.liyulong.blog.back.sys.service.SysRoleMenuService;
import com.liyulong.blog.main.mapper.sys.SysMenuMapper;
import com.liyulong.blog.main.pojo.sys.SysMenu;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 菜单管理 服务实现类
 * </p>
 *
 * @author lihanlu
 * @since 2020-03-24
 */
@Service
public class SysMenuServiceImpl extends ServiceImpl<SysMenuMapper, SysMenu> implements SysMenuService {

    @Autowired
    private SysRoleMenuService roleMenuService;

    @Override
    public List<SysMenu> listUserMenu(Integer userId) {
        return null;
    }

    @Override
    public List<SysMenu> queryListParentId(Integer parentId, List<Integer> menuIdList) {
        List<SysMenu> menuList = queryListParentId(parentId);
        if(menuIdList == null){
            return menuList;
        }
        List<SysMenu> userMenuList = new ArrayList<>();
        for(SysMenu menu:menuList){
            if(menuIdList.contains(menu.getMenuId())){
                userMenuList.add(menu);
            }
        }
        return userMenuList;
    }

    @Override
    public List<SysMenu> queryListParentId(Integer parentId) {
        return baseMapper.queryListParentId(parentId);
    }

    @Override
    public List<SysMenu> queryNotButtonList() {
        return baseMapper.queryNotButtonList();
    }

    @Override
    public List<SysMenu> getUserMenuList(Integer userId) {
        return null;
    }

    @Override
    public void delete(Integer menuId) {
        Map<String,Object> map = new HashMap<>();
        map.put("menuId",menuId);
        roleMenuService.removeByMap(map);
    }

    /**
     * 获取所有的menu
     * @param menuIdList
     * @return
     */
    private List<SysMenu> getAllMenuList(List<Integer> menuIdList){
        //查询用户所属所有目录
        List<SysMenu> menuList = queryListParentId(0,menuIdList);
        return null;
    }
}
