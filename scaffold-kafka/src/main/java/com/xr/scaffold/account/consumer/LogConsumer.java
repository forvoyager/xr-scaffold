package com.xr.scaffold.account.consumer;

import org.apache.kafka.clients.consumer.Consumer;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

/**
 * @author: yang.changyan@foundbyte.com
 * @time: 2021-05-12 15:30:00
 * @description: 日志记录消费者
 */
@Component
public class LogConsumer extends DefaultConsumer {

  @KafkaListener(
          id = "${spring.kafka.consumer.log.id}",
          topics = "${spring.kafka.consumer.log.topic}"
  )
  @Override
  public void consume(ConsumerRecord message, Consumer consumer) throws Exception {
    logger.info("消费：{}", message);
  }

}
