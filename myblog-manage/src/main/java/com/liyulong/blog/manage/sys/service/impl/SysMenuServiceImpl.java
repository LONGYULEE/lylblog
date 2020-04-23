package com.liyulong.blog.manage.sys.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.liyulong.blog.manage.sys.service.SysMenuService;
import com.liyulong.blog.manage.sys.service.SysRoleMenuService;
import com.liyulong.blog.manage.sys.service.SysUserService;
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

    @Autowired
    private SysUserService userService;

    @Override
    public List<SysMenu> listUserMenu(Integer userId) {
        //系统管理员，拥有最高权限
        if(userId == 1){
            //查询管理员所有的菜单
            return getAllMenuList(null);
        }
        //查询用户所有的菜单id
        List<Integer> menuIdList = userService.queryAllMenuId(userId);
        //查询用户所有的菜单
        return getAllMenuList(menuIdList);
    }

    /**
     * 根据父菜单id查询子菜单
     * @param parentId 父菜单ID
     * @param menuIdList  用户菜单ID
     * @return
     */
    @Override
    public List<SysMenu> queryListParentId(Integer parentId, List<Integer> menuIdList) {
        List<SysMenu> menuList = queryListParentId(parentId);
        if(menuIdList == null){
            //admin 账户
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
        //管理员，拥有所有菜单权限
        if(userId == 1){
            return getAllMenuList(null);
        }
        //用户菜单列表
        List<Integer> menuIdList = userService.queryAllMenuId(userId);
        return getAllMenuList(menuIdList);
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
        //一次菜单id为0
        List<SysMenu> menuList = queryListParentId(0,menuIdList);
        //生成指定格式的菜单数据
        getMenuTreeList(menuList,menuIdList);
        return menuList;
    }

    //生成指定格式得菜单数据
    private List<SysMenu> getMenuTreeList(List<SysMenu> menuList,List<Integer> menuIdList){
        List<SysMenu> subMenuList = new ArrayList<>();
        for (SysMenu item : menuList) {
            //目录  菜单类型：0：目录，1：菜单，2：按钮
            if(item.getType() == 0){
                //如果是一级菜单，去获取当前一级菜单下的所有菜单，并填充到 menu 对象的 list 属性中
                item.setList(getMenuTreeList(queryListParentId(item.getMenuId(),menuIdList),menuIdList));
            }
            //将拼接好的菜单信息添加到新的 list 中
            subMenuList.add(item);
        }
        return subMenuList;
    }
}
