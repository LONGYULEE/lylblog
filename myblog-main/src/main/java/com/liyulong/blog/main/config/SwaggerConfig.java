package com.liyulong.blog.main.config;

import io.swagger.annotations.ApiOperation;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * swagger的配置类
 */
@Configuration
public class SwaggerConfig {

    /**
     * 创建API应用
     * apiInfo() 增加API相关信息
     * 通过select()函数返回一个ApiSelectorBuilder实例,用来控制哪些接口暴露给Swagger来展现，
     * 本例采用指定扫描的包路径来定义指定要建立API的目录。
     * 采用添加@ApiOperation的方式生成文档
     *
     * @return
     */
//    @Bean("admin")
//    public Docket createRestApiAdmin(){
//        return new Docket(DocumentationType.SWAGGER_2)
//                .apiInfo(apiInfo())
//                .select()
//                //在类上添加@APIOperation注解，生成接口文档
//                .apis(RequestHandlerSelectors.basePackage("com.liyulong.blog.manage."))
//                .paths(PathSelectors.any())
//                .build();
//    }
//
//    @Bean("index")
//    public Docket createRestApiIndex(){
//        return new Docket(DocumentationType.SWAGGER_2)
//                .apiInfo(apiInfo())
//                .select()
//                //在类上添加@APIOperation注解，生成接口文档
//                .apis(RequestHandlerSelectors.basePackage("com.liyulong.blog.index.*"))
//                .paths(PathSelectors.any())
//                .build();
//    }

    public Docket createRestApi(){
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .select()
                //在类上添加@APIOperation注解，生成接口文档
                .apis(RequestHandlerSelectors.withMethodAnnotation(ApiOperation.class))
                .paths(PathSelectors.any())
                .build();
    }

    /**
     * 创建该API的基本信息（这些基本信息会展现在文档页面中）
     * 访问地址：http://项目实际地址/swagger-ui.html
     * @return
     */
    private ApiInfo apiInfo(){
        return new ApiInfoBuilder()
                .title("myblog")
                .description("myblog接口文档")
                .build();
    }

//    private List<ApiKey> security(){
//        return newArrayList(new ApiKey("token","token","header"));
//    }

}
