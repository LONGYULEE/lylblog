package com.liyulong.blog.manage.sys.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.liyulong.blog.manage.sys.service.SysRoleService;
import com.liyulong.blog.manage.sys.service.SysUserService;
import com.liyulong.blog.main.common.context.UserContext;
import com.liyulong.blog.main.common.exception.MyException;
import com.liyulong.blog.main.common.util.PageUtils;
import com.liyulong.blog.main.common.util.QiNiuUtil;
import com.liyulong.blog.main.common.util.Query;
import com.liyulong.blog.main.mapper.sys.SysUserMapper;
import com.liyulong.blog.main.pojo.sys.SysRole;
import com.liyulong.blog.main.pojo.sys.SysUser;
import org.apache.commons.lang.RandomStringUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.shiro.crypto.hash.Md5Hash;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.UnsupportedEncodingException;
import java.util.Arrays;
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

    /**
     * SysRoleServiceImpl中引入了SysUserServiceImpl
     * 在初始化bean时spring不知道先创建哪个bean
     * 在其中一个依赖中加上延迟加载
     */
    @Autowired
    @Lazy
    private SysRoleService sysRoleService;

    @Override
    public List<Integer> queryAllMenuId(Integer userId) {
        return baseMapper.queryAllMenuId(userId);
    }

    @Override
    public PageUtils queryPage(Map<String,Object> map) {
        String username = (String) map.get("username");
        //超级管理员查询所有用户
        //其他用户查询自己创建的用户
        if(UserContext.getUserId() == 1){
            IPage<SysUser> userIPage = baseMapper.selectPage(new Query<SysUser>(map).getPage(),
                    new QueryWrapper<SysUser>().lambda()
                            .like(StringUtils.isNotEmpty(username), SysUser::getUsername,username));
            return new PageUtils(userIPage);
        }
        IPage<SysUser> userIPage = baseMapper.selectPage(new Query<SysUser>(map).getPage(),
                new QueryWrapper<SysUser>().lambda()
                        .eq(SysUser::getCreateUserId,UserContext.getUserId())
                        .like(StringUtils.isNotEmpty(username), SysUser::getUsername,username));
        return new PageUtils(userIPage);
    }

    @Override
    public boolean updatePassword(Integer userId, String password, String newPassword) {
        SysUser user = new SysUser();
        user.setPassword(newPassword);
        return this.update(user,new UpdateWrapper<SysUser>().lambda()
                .eq(SysUser::getUserId,userId)
                .eq(SysUser::getPassword,password));
    }

    @Override
    public void deleteBatch(Integer[] userIds) {
        //删除用户与角色关联 todo
        baseMapper.deleteBatchIds(Arrays.asList(userIds));
        sysRoleService.deleteBatchIds(userIds);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void createUser(SysUser user) {
        //生成随机字符串作为盐值
        String salt = RandomStringUtils.randomAlphanumeric(8);
        user.setSalt(salt);
        user.setPassword(encryptPassword(user.getPassword(),salt));
        //上下文获取当前登录人信息
        user.setCreateUserId(UserContext.getCurrentUser().getUserId());
        //判断用户名或邮箱是否重复
        Integer count = baseMapper.selectCount(new QueryWrapper<SysUser>().lambda().eq(SysUser::getUsername, user.getUsername()));
        Integer count1 = baseMapper.selectCount(new QueryWrapper<SysUser>().lambda().eq(SysUser::getEmail,
                user.getEmail()));
        if(count != 0){
            throw new MyException("用户名重复");
        }
        if(count1 != 0){
            throw new MyException("邮箱重复");
        }
        baseMapper.insert(user);
    }

    @Override
    public List<SysRole> queryUserRole(Integer userId) {
        return baseMapper.queryUserRole(userId);
    }

    /**
     * 密码加密
     * @param password
     * @param salt
     * @return
     */
    private String encryptPassword(String password,String salt){
        //密码加密
        Md5Hash md5Hash = new Md5Hash(password,salt,2);
        return md5Hash.toString();
    }
}
