package com.xr.recommend.service.impl;

import com.xr.recommend.common.model.ActionModel;
import com.xr.recommend.common.service.IActionService;
import com.xr.recommend.mapper.ActionMapper;
import com.xr.base.jdbc.service.impl.BaseServiceImpl;
import com.xr.recommend.processor.ActionProcessor;
import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <b>author</b>: forvoyager@outlook.com
 * <b>time</b>: 2021-08-10 13:19:29 <br>
 * <b>description</b>: 行为数据 服务实现 <br>
 */
@DubboService
@Service("actionService")
public class ActionServiceImpl extends BaseServiceImpl<ActionMapper, ActionModel> implements IActionService {

  @Autowired
  private ActionProcessor actionProcessor;

  @Override
  public void upload(ActionModel entity) throws Exception {
    actionProcessor.add(entity);
  }

  @Override
  protected String getPrimaryKeyName() {
    return "action_id";
  }

}
