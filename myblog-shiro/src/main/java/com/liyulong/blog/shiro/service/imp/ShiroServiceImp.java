package com.liyulong.blog.shiro.service.imp;

import com.liyulong.blog.main.common.constant.RedisConstant;
import com.liyulong.blog.main.common.util.RedisUtil;
import com.liyulong.blog.main.mapper.sys.SysMenuMapper;
import com.liyulong.blog.main.mapper.sys.SysUserMapper;
import com.liyulong.blog.main.pojo.sys.SysMenu;
import com.liyulong.blog.main.pojo.sys.SysUser;
import com.liyulong.blog.main.pojo.sys.SysUserToken;
import com.liyulong.blog.shiro.service.ShiroService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class ShiroServiceImp implements ShiroService {

    @Autowired
    private RedisUtil redisUtil;

    @Autowired
    private SysUserMapper userMapper;

    @Autowired
    private SysMenuMapper menuMapper;

    @Override
    public SysUserToken findByToken(String token) {
        token = token.replaceAll("Bearer ","");
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
    public void refreshToken(Integer userId) {
        redisUtil.updateExpire(RedisConstant.USER_TOKEN + userId);
        //获取tokenKey
        String tokenKey = RedisConstant.USER_TOKEN + redisUtil.get(RedisConstant.USER_TOKEN + userId);
        redisUtil.updateExpire(tokenKey);
    }

    @Override
    public Set<String> queryPermissions(Integer userId) {
        List<String> permsList;
        //管理员有全部权限
        if(userId == 1){
            List<SysMenu> menuList = menuMapper.selectList(null);
            permsList = new ArrayList<>(menuList.size());
            menuList.forEach(item -> permsList.add(item.getPerms()));
        }else{
            //获取用户权限
            permsList = userMapper.queryAllPerms(userId);
        }
        //封装数据
        return permsList.stream()
                //过滤空的数据
                .filter(item -> !StringUtils.isEmpty(item))
                // 把小的list合并成大的list
                .flatMap(item -> Arrays.stream(item.split(",")))
                //将list流输出为set
                .collect(Collectors.toSet());
    }

}
