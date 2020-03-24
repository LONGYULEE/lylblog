package com.liyulong.blog.shiro.service.imp;

import com.liyulong.blog.main.common.constant.RedisConstant;
import com.liyulong.blog.main.common.util.RedisUtil;
import com.liyulong.blog.main.mapper.sys.SysUserMapper;
import com.liyulong.blog.main.pojo.sys.SysUser;
import com.liyulong.blog.main.pojo.sys.SysUserToken;
import com.liyulong.blog.shiro.service.ShiroService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

@Service
public class ShiroServiceImp implements ShiroService {

    @Autowired
    private RedisUtil redisUtil;

    @Autowired
    private SysUserMapper userMapper;

    @Override
    public SysUserToken findByToken(String token) {
        String userId = redisUtil.get(RedisConstant.USER_TOKEN + token);
        if(StringUtils.isEmpty(userId)){
            return null;
        }
        SysUserToken userToken = new SysUserToken();
        userToken.setToken(token);
        userToken.setUserId(Integer.parseInt(userId));
        return userToken;
    }

    @Override
    public SysUser queryUser(Integer userId) {
        return userMapper.selectById(userId);
    }

    @Override
    public void refreshToken(Integer userId, String accessToken) {
        redisUtil.updateExpire(RedisConstant.USER_TOKEN + accessToken);
        redisUtil.updateExpire(RedisConstant.USER_TOKEN + userId);
    }

}
