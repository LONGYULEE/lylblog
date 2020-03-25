package com.liyulong.blog.back.sys.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.liyulong.blog.main.common.util.PageUtils;
import com.liyulong.blog.main.mapper.sys.SysUserMapper;
import com.liyulong.blog.main.pojo.sys.SysUser;
import com.liyulong.blog.back.sys.service.SysUserService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author lihanlu
 * @since 2020-03-24
 */
@Service
public class SysUserServiceImpl extends ServiceImpl<SysUserMapper, SysUser> implements SysUserService {

    @Override
    public List<Integer> queryAllMenuId(Integer userId) {

        return null;
    }

    @Override
    public PageUtils queryPage(Map<String, Object> map) {
        return null;
    }

    @Override
    public boolean updatePassword(Integer userId, String password, String newPassword) {
        return false;
    }

    @Override
    public void deleteBatch(Integer[] userIds) {

    }

    @Override
    public void createUser(SysUser user) {
        baseMapper.insert(user);
    }
}
