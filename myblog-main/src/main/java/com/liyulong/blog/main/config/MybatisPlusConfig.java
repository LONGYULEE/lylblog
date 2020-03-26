package com.liyulong.blog.main.config;

import com.baomidou.mybatisplus.core.config.GlobalConfig;
import com.baomidou.mybatisplus.extension.plugins.PaginationInterceptor;
import com.liyulong.blog.main.common.mybatisfill.MetaHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 配置mybatisplus分页插件
 */
@Configuration
public class MybatisPlusConfig {

    //分页插件
    @Bean
    public PaginationInterceptor paginationInterceptor(){
        return new PaginationInterceptor();
    }

    /**
     * 自动填充插件
     * @return
     */
    @Bean
    public GlobalConfig globalConfig(){
        GlobalConfig globalConfig = new GlobalConfig();
        globalConfig.setMetaObjectHandler(new MetaHandler());
        return globalConfig;
    }

}
