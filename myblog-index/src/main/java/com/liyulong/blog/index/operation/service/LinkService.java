package com.liyulong.blog.index.operation.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.liyulong.blog.main.pojo.operation.Link;

import java.util.List;

public interface LinkService extends IService<Link> {

    /**
     * 获取 link 列表
     * @return
     */
    List<Link> listLink();

}
