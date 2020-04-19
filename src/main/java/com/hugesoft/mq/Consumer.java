package com.hugesoft.mq;

import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.cloud.stream.messaging.Processor;
import org.springframework.messaging.Message;

@Slf4j
@EnableBinding(Processor.class)
public class Consumer {

    @StreamListener(Processor.INPUT)
    public void receive(Message<String> message) {
        log.info("receive message {}", message.getPayload());
    }

}
