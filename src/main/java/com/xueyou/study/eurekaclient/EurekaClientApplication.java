package com.xueyou.study.eurekaclient;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.autoconfigure.AutoConfigurationExcludeFilter;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.TypeExcludeFilter;
import org.springframework.cloud.client.SpringCloudApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FilterType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Random;

@SpringBootApplication(scanBasePackages = {"com.xueyou.study.eurekaclient", "com.xueyou.study.common"})
@EnableCircuitBreaker
@EnableEurekaClient
@RestController
public class EurekaClientApplication {

    @Value("${server.port}")
    private Integer port;

    @RequestMapping("/home")
    public String home() throws InterruptedException {
        // 让线程等待几秒钟， 模拟超时, Hystrix默认超时为2000豪秒
        int sleepTime = new Random().nextInt(800);
        System.out.println("模拟超时时间: " + sleepTime);
        Thread.sleep(sleepTime);
        return String.format("Hello Home, port: %s", port);
    }

    public static void main(String[] args) {
        SpringApplication.run(EurekaClientApplication.class, args);
    }

}
