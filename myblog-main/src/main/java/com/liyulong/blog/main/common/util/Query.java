package com.liyulong.blog.main.common.util;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.liyulong.blog.main.common.filter.SQLFilter;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.commons.lang.StringUtils;

import java.util.HashMap;
import java.util.Map;

/**
 * 查询参数
 */
@Data
public class Query<T> extends HashMap<String,Object> {

    //mybatis-plus分页参数
    private Page<T> page;

    //当前页
    private long currPage = 1;

    //每页条数
    private int limit = 10;

    public Query(Map<String,Object> map){
        this.putAll(map);

        //分页参数
        if(map.get("page") != null){
            currPage = Integer.parseInt((String) map.get("page"));
        }
        if(map.get("limit") != null){
            limit = Integer.parseInt((String) map.get("limit"));
        }
        this.put("offset",(currPage - 1) * limit);
        this.put("page",currPage);
        this.put("limit",limit);

        //防止SQL注入（因为sidx、order是通过拼接SQL实现排序的，会有SQL注入风险）
        String sidx = SQLFilter.sqlInject((String) map.get("sidx"));
        String order = SQLFilter.sqlInject((String) map.get("order"));
        this.put("sidx",sidx);
        this.put("order",order);

        //mybatis-plus分页
        this.page = new Page<>(currPage,limit);
        //排序
        if(StringUtils.isNotBlank(sidx) && StringUtils.isNotBlank(order)){
            if("Asc".equalsIgnoreCase(order)){
                this.page.setAsc(sidx);
            }else {
                this.page.setDesc(sidx);
            }
        }
    }

}
