package com.xr.recommend.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xr.recommend.common.entity.ActionEntity;
import com.xr.recommend.common.service.IActionService;
import com.xr.recommend.mapper.ActionMapper;
import com.xr.recommend.processor.ActionProcessor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 行为数据 服务实现类
 * </p>
 *
 * @author forvoyager@outlook.com
 * @since 2022-04-02
 */
@Service
public class ActionServiceImpl extends ServiceImpl<ActionMapper, ActionEntity> implements IActionService {

  @Autowired
  private ActionProcessor actionProcessor;

  @Override
  public void upload(ActionEntity entity) throws Exception {
    actionProcessor.add(entity);
  }

}
