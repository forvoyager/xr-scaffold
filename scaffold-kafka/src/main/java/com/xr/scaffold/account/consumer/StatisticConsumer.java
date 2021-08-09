package com.xr.scaffold.account.consumer;

import org.apache.kafka.clients.consumer.Consumer;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author: yang.changyan@foundbyte.com
 * @time: 2021-05-12 15:30:00
 * @description: 信息统计消费者
 */
@Component
public class StatisticConsumer extends DefaultConsumer {

  @KafkaListener(
          id = "${spring.kafka.consumer.statstic.id}",
          topics = "${spring.kafka.consumer.statstic.topic}"
  )
  @Override
  public void consume(List<ConsumerRecord> messages, Consumer consumer) throws Exception {
    logger.info("消费：{}", messages);
  }

}
