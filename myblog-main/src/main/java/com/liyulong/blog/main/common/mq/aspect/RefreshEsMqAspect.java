package com.liyulong.blog.main.common.mq.aspect;

import com.liyulong.blog.main.common.constant.RabbitMqConstants;
import com.liyulong.blog.main.common.mq.annotation.RefreshEsMqSender;
import com.liyulong.blog.main.common.util.RabbitMqUtils;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.lang.reflect.Method;

/**
 * RefreshEsMqAspect
 *
 * @description
 */
@Aspect
@Component
public class RefreshEsMqAspect {

    @Resource
    private RabbitMqUtils rabbitMqUtils;

    @Pointcut("@annotation(com.liyulong.blog.main.common.mq.annotation.RefreshEsMqSender)")
    public void pointCut() {

    }

    @Around("pointCut()")
    public Object around(ProceedingJoinPoint point) throws Throwable {
        //执行方法
        Object result = point.proceed();
        MethodSignature signature = (MethodSignature) point.getSignature();
        Method method = signature.getMethod();
        RefreshEsMqSender senderAnnotation = method.getAnnotation(RefreshEsMqSender.class);
        // 发送刷新信息
        rabbitMqUtils.send(RabbitMqConstants.REFRESH_ES_INDEX_QUEUE,senderAnnotation.sender()+" "+senderAnnotation.msg());
        return result;
    }
}
