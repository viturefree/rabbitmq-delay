package com.hugesoft;

import com.hugesoft.mq.Producer;
import lombok.extern.slf4j.Slf4j;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class)
public class DelayMessageTest {

    @Autowired
    private Producer producer;

    @Test
    public void testDelayQueue() throws Exception {
        for(int i=0;i<10000;i++) {
            producer.sendDelayQueueMessage("delay queue message");
        }
        Thread.sleep(10000);
    }

    @Test
    public void testDelayExchange() throws Exception {
        for(int i=0;i<100000;i++) {
            producer.sendDelayExchangeMessage("delay queue message");
        }
        Thread.sleep(100000);
    }

    @Before
    public void before() {
        log.info("before...");
    }

    @After
    public void after() {
        log.info("after...");
    }

}
