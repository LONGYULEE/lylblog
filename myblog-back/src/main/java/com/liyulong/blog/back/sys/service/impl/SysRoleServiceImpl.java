package com.liyulong.blog.back.sys.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.liyulong.blog.back.sys.service.SysRoleService;
import com.liyulong.blog.main.common.context.UserContext;
import com.liyulong.blog.main.common.exception.MyException;
import com.liyulong.blog.main.common.util.PageUtils;
import com.liyulong.blog.main.mapper.sys.SysRoleMapper;
import com.liyulong.blog.main.pojo.sys.SysRole;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;

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

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void deleteBatchIds(Integer[] userIds) {
        //todo 删除多个user下面的角色信息
    }

    @Override
    public void createRole(SysRole role) {
        Integer count = baseMapper.selectCount(new QueryWrapper<SysRole>().lambda().eq(SysRole::getRoleName, role.getRoleName()));
        if(count != 0){
            throw new MyException("已存在该角色");
        }
        role.setCreateUserId(UserContext.getCurrentUser().getUserId());
        baseMapper.insert(role);
    }

    @Override
    public PageUtils queryRoleByPage() {
        return null;
    }

    @Override
    public PageUtils queryRoleByUserId(Long userId) {
        return null;
    }

    @Override
    public void deleteByIds(Integer[] roleId) {
        baseMapper.deleteBatchIds(Arrays.asList(roleId));
    }
}
