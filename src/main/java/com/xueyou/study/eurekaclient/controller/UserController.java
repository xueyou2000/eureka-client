package com.xueyou.study.eurekaclient.controller;

import com.xueyou.study.common.rabbitmq.Producer;
import com.xueyou.study.serviceApi.models.dto.User;
import com.xueyou.study.serviceApi.service.UserService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.*;

/**
 * 创建 by xueyo on 2019/8/14
 */
@RefreshScope
@RestController
public class UserController implements UserService {

    private final Producer producer;

    @Value("${server.port}")
    private Integer port;

    @Value("${from}")
    private String from;

    public UserController(Producer producer) {
        this.producer = producer;
    }

    @Override
    public String hello(@RequestParam String name) {
        return "欢迎 Hello " + name;
    }

    @Override
    public User hello(@RequestHeader String name, @RequestHeader Integer age) {
        User user = new User();
        user.setName(name);
        user.setAge(age);
        return user;
    }

    @Override
    public String loadBalanced() {
        return String.format("api所处端口, port: %s", port);
    }

    @Override
    public String hello(@RequestBody User user) {
        return "欢迎 Hello " + user;
    }

    @ApiOperation(value = "rest", notes = "返回json", httpMethod = "GET")
    @RequestMapping(value = "rest", produces = "application/json;charset=UTF-8")
    public User rest(@RequestParam String name) {
        User user = new User();
        user.setName(name);
        user.setAge(99);
        return user;
    }

    @ApiOperation(value = "test", notes = "获取配置中心的值", httpMethod = "GET")
    @RequestMapping("test")
    public String test() {
        return "从配置中心获取 from = " + from;
    }

    @ApiOperation(value = "login", notes = "发送mq消息测试", httpMethod = "GET")
    @RequestMapping("login")
    public void sendWechatLogin(@RequestParam String key) {
        producer.sendExchangeMsg("topic-exchange", key, "XueYou");
    }


}
