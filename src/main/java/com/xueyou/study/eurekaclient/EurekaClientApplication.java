package com.xueyou.study.eurekaclient;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Random;

@SpringBootApplication(scanBasePackages = {"com.xueyou.study.eurekaclient", "com.xueyou.study.common"})
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
        return String.format("阿斯顿 Home, port: %s", port);
    }

    public static void main(String[] args) {
        SpringApplication.run(EurekaClientApplication.class, args);
    }

}
