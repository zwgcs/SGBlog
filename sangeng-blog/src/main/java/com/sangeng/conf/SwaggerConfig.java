package com.sangeng.conf;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

/**
 * 对Swagger进行一些配置
 * @Author SQ Email:1718048299@qq.com
 * @Date 2023/4/9 22:10
 * @Since version 1.0
 */
@Configuration
public class SwaggerConfig {
    @Bean
    public Docket customDocket() {
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.sangeng.controller"))
                .build();
    }

    private ApiInfo apiInfo() {
        Contact contact = new Contact("个人打造博客前台", "http://www.my.com", "1718048299@qq.com");
        return new ApiInfoBuilder()
                .title("博客前台系统接口文档")
                .description("本文档详细描述博客前台系统的相关接口")
                //联系方式
                .contact(contact)
                //版本
                .version("1.1.0")
                .build();
    }
}
