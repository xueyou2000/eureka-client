package com.xueyou.study.eurekaclient.rabbitmq;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.ExchangeTypes;
import org.springframework.amqp.rabbit.annotation.Exchange;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.QueueBinding;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * 创建 by xueyo on 2019/8/16
 */
@Component
public class ClientConsumer {

    private static Logger log = LoggerFactory.getLogger(ClientConsumer.class);


    @RabbitListener(bindings = @QueueBinding(
            exchange = @Exchange(value = "topic-exchange", type = ExchangeTypes.TOPIC),
            value = @Queue(value = "wechat-queue-a", durable = "true"),
            key = "wechat.login.#"
    ))
    public void directWechatLogin(String msg) {
        log.info("多播 wechat-queue-a 队列 匹配路径 wechat/login/# 接受消息: -----[{}]-----\n", msg);
    }


    @RabbitListener(bindings = @QueueBinding(
            exchange = @Exchange(value = "topic-exchange", type = ExchangeTypes.TOPIC),
            value = @Queue(value = "wechat-queue-b", durable = "true"),
            key = "wechat.#"
    ))
    public void directWechatExit(String msg) {
        log.info("多播 wechat-queue-b 队列 匹配路径 wechat/# 接受消息: -----[{}]-----\n", msg);
    }


}
