package com.liyulong.blog.main.common.exception;

/**
 * 自定义异常类
 */
public class MyException extends RuntimeException {
    private static final long serialVersionUID = -8900034483217765018L;

    //异常消息
    private String msg;

    public MyException(String msg){
        this.msg = msg;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
