package com.liyulong.blog.manage.operation.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.liyulong.blog.main.common.util.PageUtils;
import com.liyulong.blog.main.pojo.operation.Link;

import java.util.Map;

/**
 * <p>
 * 友链 服务类
 * </p>
 *
 * @author lihanlu
 * @since 2020-03-24
 */
public interface LinkService extends IService<Link> {

    /**
     * 分页查询
     * @param params
     * @return
     */
    PageUtils queryPage(Map<String, Object> params);

}
