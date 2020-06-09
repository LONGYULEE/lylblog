package com.liyulong.blog.index.common.aspect;

import com.liyulong.blog.main.common.util.HttpContextUtils;
import com.liyulong.blog.main.common.util.IPUtils;
import com.liyulong.blog.main.common.util.JsonUtil;
import com.liyulong.blog.main.mapper.article.ArticleMapper;
import com.liyulong.blog.main.mapper.log.LogLikeMapper;
import com.liyulong.blog.index.common.annotation.LogLike;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;
import java.time.LocalDateTime;

@Aspect //把当前类标识为一个切面供容器读取
@Component //注入到 spring 容器
@Slf4j
public class LogLikeAspect {

    @Autowired
    private LogLikeMapper logLikeMapper;

    @Autowired
    private ArticleMapper articleMapper;

    @Pointcut("@annotation(com.liyulong.blog.index.common.annotation.LogLike)")
    public void logPointCut() {

    }

    @Around("logPointCut()")
    @Transactional(rollbackFor = Exception.class)
    public Object around(ProceedingJoinPoint point) throws Throwable {
        long beginTime = System.currentTimeMillis();
        //执行方法
        Object result = point.proceed();
        //执行时长(毫秒)
        long time = System.currentTimeMillis() - beginTime;

        //保存日志
        saveLogLike(point, time);

        return result;
    }

    private void saveLogLike(ProceedingJoinPoint joinPoint, long time) {
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        Method method = signature.getMethod();
        com.liyulong.blog.main.pojo.log.LogLike logLikeEntity = new com.liyulong.blog.main.pojo.log.LogLike();
        LogLike viewLog = method.getAnnotation(LogLike.class);
        //注解上的类型
        String type = viewLog.type();
        logLikeEntity.setType(type);
        //请求的参数
        Object[] args = joinPoint.getArgs();
        String id = JsonUtil.toJson(args[0]);
        // 根据注解类型增加数量
        switch (type) {
            case "article":
                articleMapper.updateLikeNum(Integer.parseInt(id));
                break;
            default:
                break;
        }
        logLikeEntity.setParams(id);
        //获取request
        HttpServletRequest request = HttpContextUtils.getHttpServletRequest();
        //设置IP地址
        logLikeEntity.setIp(IPUtils.getIpAddr(request));
        logLikeEntity.setTime(time);
        logLikeEntity.setCreateDate(LocalDateTime.now());
        logLikeMapper.insert(logLikeEntity);

    }

    /**
     * @Pointcut: Pointcut是植入Advice的触发条件。每个Pointcut的定义包括2部分，一是表达式，二是方法签名。
     * 方法签名必须是 public及void型。可以将Pointcut
     * 中的方法看作是一个被Advice引用的助记符，因为表达式不直观，因此我们可以通过方法签名的方式为 此表达式命名。
     * 因此Pointcut中的方法只需要方法签名，而不需要在方法体内编写实际代码。
     * @Around: 环绕增强，相当于MethodInterceptor
     * @AfterReturning: 后置增强，相当于AfterReturningAdvice，方法正常退出时执行
     * @Before: 标识一个前置增强方法，相当于BeforeAdvice的功能，相似功能的还有
     * @AfterThrowing: 异常抛出增强，相当于ThrowsAdvice
     * @After: final增强，不管是抛出异常或者正常退出都会执行
     */
}
