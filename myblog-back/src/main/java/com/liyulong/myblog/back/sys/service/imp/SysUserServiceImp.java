package com.liyulong.myblog.back.sys.service.imp;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.liyulong.blog.main.mapper.sys.SysUserMapper;
import com.liyulong.blog.main.pojo.sys.SysUser;
import com.liyulong.myblog.back.sys.service.SysUserService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SysUserServiceImp extends ServiceImpl<SysUserMapper, SysUser> implements SysUserService {
    @Override
    public List<Integer> queryAllMenuId(Integer userId) {
        return null;
    }

    @Override
    public boolean updatePassword(Integer userId, String password, String newPassword) {
        return false;
    }

    @Override
    public void deleteBatch(Integer[] userIds) {

    }
}
