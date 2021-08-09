package com.xr.scaffold.account.controller;

import com.xr.base.core.dto.ResultDto;
import io.swagger.annotations.Api;
import org.apache.kafka.common.errors.IllegalSaslStateException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.config.KafkaListenerEndpointRegistry;
import org.springframework.kafka.listener.MessageListenerContainer;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
* <b>author</b>: yang.changyan@foundbyte.com
* <b>time</b>: 2020-08-07 14:55:01 <br>
* <b>description</b>: 消费者操作 HTTP服务 <br>
*/
@Api(tags = "消费者操作")
@RestController
@RequestMapping("/consumer")
public class ConsumerController {

  @Autowired
  private KafkaListenerEndpointRegistry registry;

  @GetMapping("/start")
  public ResultDto start(@RequestParam String consumerId) throws Exception{
    MessageListenerContainer consumer = registry.getListenerContainer(consumerId);
    if(consumer == null){
      throw new IllegalSaslStateException("消费者不存在");
    }

    if(!consumer.isRunning()){
      consumer.start();
    }

    return ResultDto.successMessage("启动成功");
  }

  @GetMapping("/stop")
  public ResultDto stop(@RequestParam String consumerId) throws Exception{
    MessageListenerContainer consumer = registry.getListenerContainer(consumerId);
    if(consumer == null){
      throw new IllegalSaslStateException("消费者不存在");
    }

    if(consumer.isRunning()){
      consumer.stop();
    }

    return ResultDto.successMessage("关闭成功");
  }

}
