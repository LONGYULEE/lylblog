package com.liyulong.blog.main.common.util;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.commons.lang.StringUtils;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

/**
 * 查询参数
 */
@Data
public class Query<T> implements Serializable {

    private static final long serialVersionUID = -5758449672689667798L;
    //mybatis-plus分页参数
    private Page<T> page;

    //当前页
    private int currPage;

    //每页条数
    private int size;

    //有传入currPage和size的构造方法
    public Query(int currPage,int size){
        this.currPage = currPage;
        this.size = size;
        this.page = new Page<>(currPage,size);
    }

    //没有传入currPage和size的构造方法
    public Query(Map<String,Object> map){
//        try {
//            int page = Integer.parseInt((String) map.get("page"));
//            int size = Integer.parseInt((String) map.get("size"));
//            this.currPage = page;
//            this.size = size;
//            this.page = new Page<>(currPage,size);
//        } catch (NullPointerException e) {
//            this.currPage = 1;
//            this.size = 10;
//            this.page = new Page<>(currPage,size);
//        }
        try {
            //获取map中的page与size
            String pageMap = (String) map.get("page");
            String sizeMap = (String) map.get("size");
            int page = Integer.parseInt(pageMap);
            int size = Integer.parseInt(sizeMap);
            this.currPage = page;
            this.size = size;
            this.page = new Page<>(currPage,size);
        } catch (Exception e) {
            this.currPage = 1;
            this.size = 10;
            this.page = new Page<>(currPage,size);
            e.printStackTrace();
        }

    }

}
