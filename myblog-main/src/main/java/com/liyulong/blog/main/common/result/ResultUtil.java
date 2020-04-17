package com.liyulong.blog.main.common.result;

/**
 * 返回结果工具类
 */
public class ResultUtil {

    /**
     * 成功无返回数据
     * @return
     */
    public static Result success(){
        Result result = new Result();
        result.setFlag(true);
        result.setMessage("成功");
        result.setCode(2000);
        return result;
    }

    /**
     * 成功有返回数据
     * @param data 响应数据
     * @return
     */
    public static Result success(Object data){
        Result result = new Result();
        result.setCode(2000);
        result.setFlag(true);
        result.setMessage("成功");
        result.setData(data);
        return result;
    }

    /**
     * 失败不返回数据
     * @return
     */
    public static Result failure(){
        Result result = new Result();
        result.setCode(2001);
        result.setFlag(false);
        result.setMessage("失败");
        return result;
    }

    /**
     * 失败带返回数据
     * @param data 响应数据
     * @return
     */
    public static Result failure(Object data){
        Result result = new Result();
        result.setCode(2001);
        result.setFlag(false);
        result.setMessage("失败");
        result.setData(data);
        return result;
    }

    /**
     * 失败返回自定义消息
     * @param msg 自定义失败消息
     * @return
     */
    public static Result failure(String msg){
        Result result = new Result();
        result.setFlag(false);
        result.setCode(2001);
        result.setMessage(msg);
        return result;
    }

    public static  Result failure(String msg,int code){
        Result result = new Result();
        result.setFlag(false);
        result.setCode(code);
        result.setMessage(msg);
        return result;
    }

}
