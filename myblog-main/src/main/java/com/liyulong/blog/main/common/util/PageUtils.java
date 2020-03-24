package com.liyulong.blog.main.common.util;

import com.baomidou.mybatisplus.core.metadata.IPage;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

@Data
@NoArgsConstructor
public class PageUtils implements Serializable {

    private static final long serialVersionUID = -3322741790868945160L;

    //总记录数
    private long totalCount;

    //每页记录数
    private long pageSize;

    //总页数
    private long totalPage;

    //当前页数
    private long currPage;

    //列表数据
    private List<?> list;

    public PageUtils(List<?> list,int totalCount,int pageSize,int currPage){
        this.list = list;
        this.totalCount = totalCount;
        this.pageSize = pageSize;
        this.currPage = currPage;
        this.totalPage = (int)Math.ceil((double)totalCount/pageSize);
    }

    public PageUtils(IPage<?> page){
        this.list = page.getRecords();
        this.totalCount = (int)page.getTotal();
        this.pageSize = page.getSize();
        this.currPage = page.getCurrent();
        this.totalPage = (int)page.getPages();
    }

}
