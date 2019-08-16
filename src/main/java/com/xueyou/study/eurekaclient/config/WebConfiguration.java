package com.xueyou.study.eurekaclient.config;

import com.xueyou.study.eurekaclient.exception.MyErrorAttributes;
import org.springframework.boot.web.servlet.error.DefaultErrorAttributes;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 创建 by xueyo on 2019/8/15
 */
@Configuration
public class WebConfiguration {

    @Bean
    public DefaultErrorAttributes errorAttributes() {
        return new MyErrorAttributes();
    }

}