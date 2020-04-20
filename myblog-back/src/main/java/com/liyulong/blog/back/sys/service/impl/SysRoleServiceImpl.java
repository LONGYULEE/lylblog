package com.liyulong.blog.back.sys.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.liyulong.blog.back.sys.service.SysRoleMenuService;
import com.liyulong.blog.back.sys.service.SysRoleService;
import com.liyulong.blog.back.sys.service.SysUserService;
import com.liyulong.blog.main.common.context.UserContext;
import com.liyulong.blog.main.common.exception.MyException;
import com.liyulong.blog.main.common.util.PageUtils;
import com.liyulong.blog.main.common.util.Query;
import com.liyulong.blog.main.mapper.sys.SysRoleMapper;
import com.liyulong.blog.main.pojo.sys.SysRole;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 角色 服务实现类
 * </p>
 *
 * @author lihanlu
 * @since 2020-03-24
 */
@Service
public class SysRoleServiceImpl extends ServiceImpl<SysRoleMapper, SysRole> implements SysRoleService {

    @Autowired
    private SysUserService userService;

    @Autowired
    private SysRoleMenuService roleMenuService;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void deleteBatchIds(Integer[] userIds) {
        //todo 删除多个user下面的角色信息
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void createRole(SysRole role) {
        Integer count = baseMapper.selectCount(new QueryWrapper<SysRole>().lambda().eq(SysRole::getRoleName, role.getRoleName()));
        if(count != 0){
            throw new MyException("已存在该角色");
        }
        //此处抛出错误，回滚事务
        checkPrems(role);
        role.setCreateUserId(UserContext.getCurrentUser().getUserId());
        //保存角色
        baseMapper.insert(role);
        //保存角色与菜单关系
        roleMenuService.saveOrUpdate(role.getRoleId(),role.getMenuIdList());
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void updateRole(SysRole role) {
        baseMapper.updateById(role);
        //此处抛出错误，回滚事务
        checkPrems(role);
        role.setCreateUserId(UserContext.getCurrentUser().getUserId());
        //保存角色与菜单关系
        roleMenuService.saveOrUpdate(role.getRoleId(),role.getMenuIdList());
    }

    @Override
    public PageUtils queryRoleByPage(String roleName,Integer page,Integer size) {
        //管理员查询所有角色信息
        if(UserContext.getUserId() == 1){
            IPage<SysRole> iPage = baseMapper.selectPage(new Query<SysRole>(page,size).getPage(),
                    new QueryWrapper<SysRole>().lambda()
                            .like(StringUtils.isNotBlank(roleName),SysRole::getRoleName,roleName));
            return new PageUtils(iPage);
        }
        IPage<SysRole> iPage = baseMapper.selectPage(new Query<SysRole>(page,size).getPage(),
                new QueryWrapper<SysRole>().lambda()
                        .eq(SysRole::getCreateUserId,UserContext.getUserId()));
        return new PageUtils(iPage);
    }

    @Override
    public PageUtils queryRoleByUserId(Long userId) {
        return null;
    }

    @Override
    public void deleteByIds(Integer[] roleId) throws Exception {
        if(Arrays.asList(roleId).contains(1)){
            throw new Exception("不能删除管理员账户");
        }
        baseMapper.deleteBatchIds(Arrays.asList(roleId));
    }

    /**
     * 判断所创建或更改的角色是否超过自己的权限
     * @param role 创建或更改的角色信息
     */
    private void checkPrems(SysRole role){
        //如果不是管理员，需要判断角色的权限是否超出自身的权限
        if(role.getCreateUserId() == 1){
            return ;
        }
        //查询用户所拥有的菜单列表
        List<Integer> list = userService.queryAllMenuId(role.getCreateUserId());
        //判断是否越权
        if(list.containsAll(role.getMenuIdList())){
            throw new MyException("新增角色权限超过自身权限范围");
        }
    }

}
