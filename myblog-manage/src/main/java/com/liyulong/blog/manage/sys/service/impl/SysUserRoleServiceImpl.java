package com.liyulong.blog.manage.sys.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.liyulong.blog.manage.sys.service.SysUserRoleService;
import com.liyulong.blog.main.mapper.sys.SysUserRoleMapper;
import com.liyulong.blog.main.pojo.sys.SysUserRole;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * <p>
 * 用户与角色对应关系 服务实现类
 * </p>
 *
 * @author lihanlu
 * @since 2020-03-24
 */
@Service
public class SysUserRoleServiceImpl extends ServiceImpl<SysUserRoleMapper, SysUserRole> implements SysUserRoleService {

    @Override
    public void deleteBatchByRoleId(Integer[] roleIds) {
        Arrays.stream(roleIds).forEach(roleId -> {
            baseMapper.delete(new QueryWrapper<SysUserRole>().lambda()
                    .eq(roleId != null, SysUserRole::getRoleId, roleId));
        });
    }

    @Override
    public void deleteBatchByUserId(Integer[] userIds) {
        Arrays.stream(userIds).forEach(userId -> {
            baseMapper.delete(new QueryWrapper<SysUserRole>().lambda()
            .eq(userId!=null,SysUserRole::getUserId,userId));
        });
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void saveOrUpdate(Integer userId, List<Integer> roleIdList) {
        //删除用户与角色关系
        baseMapper.delete(new QueryWrapper<SysUserRole>().lambda()
                .eq(userId!=null,SysUserRole::getUserId,userId));
        if(roleIdList.size() == 0){
            return ;
        }
        //保存用户与角色关系
        List<SysUserRole> list = new ArrayList<>(roleIdList.size());
        for(Integer roleId : roleIdList){
            SysUserRole sysUserRole = new SysUserRole();
            sysUserRole.setUserId(userId);
            sysUserRole.setRoleId(roleId);
            list.add(sysUserRole);
        }
        this.saveBatch(list);
    }

    @Override
    public List<Integer> queryRoleIdList(Integer userId) {
        return baseMapper.queryRoleIdListByUserId(userId);
    }
}
