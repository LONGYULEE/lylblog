package com.liyulong.blog.main.common.exception;

import com.liyulong.blog.main.common.result.Result;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * 统一异常处理类
 */
@RestControllerAdvice
public class MyExceptionHandler {

    /**
     * 处理自定义异常
     * @return
     */
    @ExceptionHandler(MyException.class)
    public Result handlerMyException(MyException e){
        Result result = new Result();
        result.setFlag(false);
        result.setCode(2001);
        result.setMessage(e.getMsg());
        return result;
    }

    /**
     * 处理runtimeexception
     * @param e
     * @return
     */
    @ExceptionHandler(RuntimeException.class)
    public Result handlerRuntimeException(RuntimeException e){
        Result result = new Result();
        result.setFlag(false);
        result.setCode(2001);
        result.setMessage(e.getMessage());
        return result;
    }

}
