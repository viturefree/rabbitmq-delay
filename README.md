# Rabbitmq Delay Message
Rabbitmq实现延时消息的两种方式

## 1、使用队列的ttl特性【延时队列】
使用`ttl`特性时，同一个队列的消息过期时间将相同，即使消息本身可能带上了`ttl`，同样会因队头的消息未过期而无法消费；

### 关键配置说明
```
spring:
  cloud:
    stream:
      bindings:
        input:
          destination: delay_message_queue_input
          group: test-service
        output:
          destination: delay_message_queue_output
          producer:
            required-groups: test-service
      rabbit:
        bindings:
          input:
            consumer:
              exchangeType: direct
          output:
            producer:
              ttl: 3000
              autoBindDlq: true
              deadLetterExchange: delay_message_queue_input
              deadLetterQueueName: delay_message_queue_input.test-service
```

定义两个队列`delay_message_queue_output`及`delay_message_queue_input`，并将`delay_message_queue_output`声明为延时队列，设定队列的`ttl`为`3000ms`，
deadLetterExchange及deadLetterQueueName表示若消息过期将转发至此交换机。


## 2、使用延时插件实现【延时交换机】
使用延时交换机实现延时消息更加灵活，可以针对每个消息设置任意的过期时间，交换机中的消息如果过期将路由到绑定的队列中进行消费；

### 关键配置说明
```
spring:
  cloud:
    stream:
      bindings:
        input:
          destination: delay_message_exchange
          group: test-service
        output:
          destination: delay_message_exchange
      rabbit:
        bindings:
          input:
            consumer:
              delayed-exchange: true
          output:
            producer:
              delayed-exchange: true

```

定义两个队列并声明为延时`exchnage`，`delayed-exchange`需`rabbitmq`延时插件支持，在发送消息时带上`x-delay`参数指定过期时间；

```
    public void sendDelayExchangeMessage(String message) {
        log.info("send message {}", message);
        processor.output().send(MessageBuilder.withPayload(message).setHeader("x-delay",20000).build());
    }
```


### 赞赏(Donation)


#### 微信(Wechat Pay)

![donation-wechatpay](donate-wechatpay.png)