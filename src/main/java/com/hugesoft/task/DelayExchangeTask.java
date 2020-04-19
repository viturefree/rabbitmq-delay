package com.hugesoft.task;

import com.hugesoft.mq.Producer;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@ConditionalOnProperty(prefix = "spring.profiles", name = "active", havingValue = "exchange")
public class DelayExchangeTask {

    @Autowired
    private Producer producer;

    @Scheduled(cron = "0/1 * * * * ?")
    public void task() {
        log.info("DelayExchangeTask task ...");
        producer.sendDelayExchangeMessage("DelayExchangeTask message");
    }


}

