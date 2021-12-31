package com.xr.recommend.service.impl;

import com.xr.recommend.common.model.ActionModel;
import org.springframework.stereotype.Component;

import java.util.concurrent.ArrayBlockingQueue;

/**
 * @author: forvoyager@outlook.com
 * @time: 2021-12-21 10:52:00
 * @description: 消息队列管理器
 */
@Component
public class MQManager {
  private final int MQ_SIZE = 20000;
  private ArrayBlockingQueue<ActionModel> actionMQ = new ArrayBlockingQueue<ActionModel>(MQ_SIZE);

  /**
   * 动作行为消息队列
   * @return
   */
  public ArrayBlockingQueue actionMQ(){
    return actionMQ;
  }

}
