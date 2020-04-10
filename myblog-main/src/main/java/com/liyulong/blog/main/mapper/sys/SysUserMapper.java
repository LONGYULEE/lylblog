package com.liyulong.blog.main.mapper.sys;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.liyulong.blog.main.pojo.sys.SysRole;
import com.liyulong.blog.main.pojo.sys.SysUser;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author lihanlu
 * @since 2020-03-24
 */
@Mapper
public interface SysUserMapper extends BaseMapper<SysUser> {

    /**
     * 查询用户所有的权限
     * @param userId
     * @return
     */
    List<String> queryAllPerms(Integer userId);

    /**
     * 查询用户的menuId
     * @param userId
     * @return
     */
    List<Integer> queryAllMenuId(Integer userId);

    /**
     * 通过用户id查询用户的角色信息
     * @param userId
     */
    List<SysRole> queryUserRole(Integer userId);

    /**
     * 将远程头像地址写入user中
     * @param avatarUrl 头像路径
     */
    void updateAvatarById(@Param("avatarUrl") String avatarUrl, @Param("userId") Integer userId);
}
