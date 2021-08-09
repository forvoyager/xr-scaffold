package com.xr.scaffold.account.consumer;

import org.apache.kafka.clients.consumer.Consumer;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.support.Acknowledgment;

import java.util.List;

/**
 * @author: yang.changyan@foundbyte.com
 * @time: 2021-05-12 14:49:00
 * @description: 默认消费者
 */
public abstract class DefaultConsumer {

  protected Logger logger = LoggerFactory.getLogger(getClass());

  public void consume(ConsumerRecord message) throws Exception {

  }

  public void consume(ConsumerRecord message, Acknowledgment acknowledgment) throws Exception {

  }

  public void consume(ConsumerRecord message, Consumer consumer) throws Exception {

  }

  public void consume(ConsumerRecord message, Acknowledgment acknowledgment, Consumer consumer) throws Exception {

  }

  public void consume(List<ConsumerRecord> messages, Consumer consumer) throws Exception {

  }
}
