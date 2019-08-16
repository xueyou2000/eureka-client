package com.xueyou.study.eurekaclient.controller;

import com.xueyou.study.common.rabbitmq.Producer;
import com.xueyou.study.serviceApi.models.dto.User;
import com.xueyou.study.serviceApi.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
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

    @Value("${from}")
    private String from;

    public UserController(Producer producer) {
        this.producer = producer;
    }

    @Override
    public String hello(@RequestParam String name) {
        return "Hello " + name;
    }

    @Override
    public User hello(@RequestHeader String name, @RequestHeader Integer age) {
        User user = new User();
        user.setName(name);
        user.setAge(age);
        return user;
    }

    @Override
    public String hello(@RequestBody User user) {
        return "Hello " + user;
    }

    @RequestMapping("test")
    public String test() {
        return "from = " + from;
    }

    @RequestMapping("login")
    public void sendWechatLogin(@RequestParam String key) {
        producer.sendExchangeMsg("topic-exchange", key, "XueYou");
    }

}
