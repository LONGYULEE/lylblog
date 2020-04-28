package com.liylong.blog.portal.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
//此处配置扫描其他 module 容器中的 bean
@ComponentScan({"com.liyulong.blog.manage.*","com.liyulong.blog.shiro.*","com.liyulong.blog.main.*"})
public class InitConfig {
}
