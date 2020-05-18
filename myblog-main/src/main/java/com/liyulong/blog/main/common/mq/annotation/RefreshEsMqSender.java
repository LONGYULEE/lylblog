package com.liyulong.blog.main.common.mq.annotation;

import java.lang.annotation.*;

/**
 * RefreshEsMqSender
 *
 * @description
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface RefreshEsMqSender {
    String sender();

    String msg() default "send refresh msg to ElasticSearch";

}
