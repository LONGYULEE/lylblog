package com.liyulong.blog.main.common.result;

import com.liyulong.blog.main.common.exception.ErrorEnum;

import java.util.HashMap;

/**
 * 返回结果类，map类型
 */
public class ResultMap extends HashMap<String,Object> {

    //默认成功，的构造器
    public ResultMap(){
        put("code",2000);
        put("msg","success");
    }

    /**
     * 默认错误
     * @return 返回未知错误
     */
    public static ResultMap error(){
        return error(ErrorEnum.UNKNOWN);
    }

    /**
     * 返回 ErrorEnum 中已定义的错误
     * @param errorEnum errorEnum对象
     * @return ResultMap
     */
    public static ResultMap error(ErrorEnum errorEnum){
        return new ResultMap().put("code",errorEnum.getCode()).put("msg",errorEnum.getMsg());
    }

    /**
     * 返回带自定义错误消息的 ResultMap
     * @param msg 错误消息
     * @return ResultMap
     */
    public static ResultMap error(String msg){
        return new ResultMap().put("msg",msg).put("code",ErrorEnum.UNKNOWN.getCode());
    }

    /**
     * 返回自定义错误消息和自定义错误代码的 ResultMap
     * @param code 错误代码
     * @param msg 错误消息
     * @return ResultMap
     */
    public static ResultMap error(int code,String msg){
        return new ResultMap().put("msg",msg).put("code",code);
    }

    /**
     * 异常类型的错误 ResultMap
     * @return
     */
    public static ResultMap exception(){
        return exception(ErrorEnum.UNKNOWN);
    }

    /**
     * 已有的 ErrorEnum 异常类型的错误
     * @param errorEnum
     * @return
     */
    public static ResultMap exception(ErrorEnum errorEnum){
        return new ResultMap().put("code",errorEnum.getCode()).put("msg",errorEnum.getMsg());
    }

    /**
     * 封装业务数据
     * @param key 键
     * @param value 值
     * @return HashMap对象本身
     */
    @Override
    public ResultMap put(String key, Object value) {
        super.put(key,value);
        return this;
    }

}
