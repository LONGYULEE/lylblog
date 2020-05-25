package com.liyulong.blog.main.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * 跨域配置
 */
@Configuration
public class CorsConfig implements WebMvcConfigurer {

    //跨域支持
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                //允许可访问的域列表
                .allowedOrigins("*")
                .allowCredentials(true)
                .allowedMethods("GET","POST","PUT","DELETE","OPTIONS")
                //准备响应前的缓存持续的最大时间（以秒为单位）
                .maxAge(3600);
    }

}
