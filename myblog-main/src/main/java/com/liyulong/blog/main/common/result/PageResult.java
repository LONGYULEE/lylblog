package com.liyulong.blog.main.common.result;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PageResult implements Serializable {

    private static final long serialVersionUID = -3724034926437870199L;
    //是否返回成功
    private boolean flag;
    //返回状态码
    private Integer  code;
    //返回信息
    private String message;
    //当前第几页
    private int page;
    //每页数量
    private int size;
    //返回数据
    private Object data;

}
