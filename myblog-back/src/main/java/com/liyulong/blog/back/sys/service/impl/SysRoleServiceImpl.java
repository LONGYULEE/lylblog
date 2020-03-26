package com.liyulong.blog.back.sys.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.liyulong.blog.back.sys.service.SysRoleService;
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
        baseMapper.deleteBatchIds(Arrays.asList(userIds));
    }
}
