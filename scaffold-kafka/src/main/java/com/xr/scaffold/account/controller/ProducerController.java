package com.xr.scaffold.account.controller;

import com.xr.base.core.dto.ResultDto;
import com.xr.base.core.util.JsonUtils;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

/**
* <b>author</b>: yang.changyan@foundbyte.com
* <b>time</b>: 2020-08-07 14:55:01 <br>
* <b>description</b>: 生产者操作 HTTP服务 <br>
*/
@Api(tags = "生产者操作")
@RestController
@RequestMapping("/producer")
public class ProducerController {

  @Value("${spring.kafka.producer.log.topic}")
  private String logTopic;

  @Autowired
  private KafkaTemplate producer;

  @GetMapping("/send")
  public ResultDto send(@RequestParam String message) throws Exception{
    Map<String, String> data = new HashMap<>();
    data.put("id", System.nanoTime()+"");
    data.put("message", message);
    String content = JsonUtils.toJson(data);
    ListenableFuture<SendResult> future = producer.send(logTopic, content);
    SendResult result = future.get();

    return ResultDto.successMessage("发送成功");
  }
}
