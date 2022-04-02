package com.xr.recommend.processor;

import com.xr.base.core.processor.DefaultBatchProcessor;
import com.xr.recommend.common.entity.ActionEntity;
import com.xr.recommend.common.service.IActionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author: forvoyager@outlook.com
 * @time: 2021-12-21 11:18:00
 * @description: 用户行为处理器
 */
@Component
public class ActionProcessor extends DefaultBatchProcessor<ActionEntity> {

  @Autowired
  private IActionService actionService;

  @Override
  protected void doProcess(List<ActionEntity> data) throws Exception {
    actionService.saveBatch(data);
  }
}
