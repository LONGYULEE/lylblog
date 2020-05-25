package com.liyulong.blog.main.config;

import com.google.common.base.Predicates;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.ApiKey;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

import java.util.List;

import static com.google.common.collect.Lists.newArrayList;

/**
 * swagger的配置类
 */
@Configuration
public class SwaggerConfig implements WebMvcConfigurer {

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        // 解决 SWAGGER 404报错
        registry.addResourceHandler("swagger-ui.html").addResourceLocations("classpath:/META-INF/resources/");
        registry.addResourceHandler("/webjars/**").addResourceLocations("classpath:/META-INF/resources/webjars/");
    }

    /**
     * 创建API应用
     * apiInfo() 增加API相关信息
     * 通过select()函数返回一个ApiSelectorBuilder实例,用来控制哪些接口暴露给Swagger来展现，
     * 本例采用指定扫描的包路径来定义指定要建立API的目录。
     * 采用添加@ApiOperation的方式生成文档
     *
     * @return
     */
    @Bean("admin")
    public Docket createRestApiAdmin(){
        return new Docket(DocumentationType.SWAGGER_2)
                .groupName("admin")
                .apiInfo(manageApiInfo())
                .select()
                //在类上添加@APIOperation注解，生成接口文档
                .apis(Predicates.or(
                        RequestHandlerSelectors.basePackage("com.liyulong.blog.manage.article.controller"),
                        RequestHandlerSelectors.basePackage("com.liyulong.blog.manage.operation.controller"),
                        RequestHandlerSelectors.basePackage("com.liyulong.blog.manage.sys.controller")
                ))
                .paths(PathSelectors.any())
                .build()
                // 配置header参数
                .securitySchemes(security());
    }

    @Bean("index")
    public Docket createRestApiIndex(){
        return new Docket(DocumentationType.SWAGGER_2)
                .groupName("index")
                .apiInfo(indexApiInfo())
                .select()
                //在类上添加@APIOperation注解，生成接口文档
//                .apis(RequestHandlerSelectors.withMethodAnnotation(ApiOperation.class))
                //多包扫描
                .apis(
                        Predicates.or(
                        RequestHandlerSelectors.basePackage("com.liyulong.blog.index.article.controller"),
                        RequestHandlerSelectors.basePackage("com.liyulong.blog.index.operation.controller"),
                        RequestHandlerSelectors.basePackage("com.liyulong.blog.index.timeline.controller")
                ))
                .paths(PathSelectors.any())
                .build();
    }

    /**
     * 创建该API的基本信息（这些基本信息会展现在文档页面中）
     * 访问地址：http://项目实际地址/swagger-ui.html
     * @return
     */
    private ApiInfo manageApiInfo(){
        return new ApiInfoBuilder()
                .title("myblog")
                .description("myblog管理后台接口文档")
                .build();
    }

    private ApiInfo indexApiInfo(){
        return new ApiInfoBuilder()
                .title("myblog")
                .description("myblog博客前台接口文档")
                .build();
    }

    //swagger输入token
    private List<ApiKey> security() {
        return newArrayList(
                new ApiKey("token", "token", "header")
        );
    }

}
