package com.liyulong.blog.back.sys.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.liyulong.blog.back.sys.service.SysParamService;
import com.liyulong.blog.main.common.util.PageUtils;
import com.liyulong.blog.main.common.util.Query;
import com.liyulong.blog.main.mapper.sys.SysParamMapper;
import com.liyulong.blog.main.pojo.sys.SysParam;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * <p>
 * 系统参数 服务实现类
 * </p>
 *
 * @author lihanlu
 * @since 2020-03-24
 */
//todo
@Service
public class SysParamServiceImpl extends ServiceImpl<SysParamMapper, SysParam> implements SysParamService {
    @Override
    public PageUtils queryPage(Map<String, Object> params) {
//        String menuUrl = (String) params.get("menuUrl");
//        String type = (String) params.get("type");
//        IPage<SysParam> page = baseMapper.selectPage(new Query<SysParam>(params).getPage(),
//                new QueryWrapper<SysParam>().lambda()
//                        .eq(StringUtils.isNotBlank(menuUrl),SysParam::getMenuUrl,menuUrl)
//                        .like(StringUtils.isNotBlank(type),SysParam::getType,type));
//        return new PageUtils(page);
        return null;
    }
}
