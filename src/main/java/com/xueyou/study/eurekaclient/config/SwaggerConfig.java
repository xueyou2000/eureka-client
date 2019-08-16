package com.xueyou.study.eurekaclient.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * 创建 by xueyo on 2019/8/16
 */
@ConfigurationProperties(prefix = "swagger")
@Configuration
@EnableSwagger2
@Data
public class SwaggerConfig {

    private String title;
    private String description;
    private String version;
    private String url;
    private String name;
    private String email;

    @Bean
    public Docket customDocket() {
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.xueyou.study.eurekaclient.controller"))
                .build();
    }

    private ApiInfo apiInfo() {
        Contact contact = new Contact(name, url, email);
        return new ApiInfoBuilder()
                .title(title)
                .description(description)
                .contact(contact)
                .version(version)
                .build();
    }

}
