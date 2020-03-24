package com.liyulong.blog.main.common.result;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * 返回结果类
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Result implements Serializable {

    private static final long serialVersionUID = -2075436821655419823L;

    //是否返回成功
    private boolean flag;

    //返回状态码
    private Integer  code;

    //返回信息
    private String message;

    //返回数据
    private Object data;

    public Result(boolean flag, Integer code, String message) {
        this.flag = flag;
        this.code = code;
        this.message = message;
    }
}
