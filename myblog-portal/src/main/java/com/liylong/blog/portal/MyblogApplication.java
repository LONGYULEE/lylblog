package com.liylong.blog.portal;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@MapperScan("com.liyulong.blog.main.mapper")
@EnableSwagger2
public class MyblogApplication {

    public static void main(String[] args) {
        SpringApplication.run(MyblogApplication.class,args);
    }

}
