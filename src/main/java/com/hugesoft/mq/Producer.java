package com.hugesoft.mq;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.messaging.Processor;
import org.springframework.messaging.support.MessageBuilder;

@Slf4j
@EnableBinding(Processor.class)
public class Producer {

    @Autowired
    private Processor processor;

    public void sendDelayQueueMessage(String message) {
        log.info("send message {}", message);
        processor.output().send(MessageBuilder.withPayload(message).build());
    }

    public void sendDelayExchangeMessage(String message) {
        log.info("send message {}", message);
        processor.output().send(MessageBuilder.withPayload(message).setHeader("x-delay",20000).build());
    }


}
