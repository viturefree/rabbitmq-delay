# Rabbitmq Delay Message
Rabbitmq实现延时消息的两种方式

## 1、使用队列的ttl特性【延时队列】
使用ttl特性时，同一个队列的消息过期时间将相同，即使消息本身可能带上了ttl，同样会因队头的消息未过期而无法消费；

## 2、使用延时插件实现【延时交换机】
使用延时交换机实现延时消息更加灵活，可以针对每个消息设置任意的过期时间，交换机中的消息如果过期将路由到绑定的队列中进行消费；


### 赞赏(Donation)


#### 微信(Wechat Pay)

![donation-wechatpay](donate-wechatpay.png)