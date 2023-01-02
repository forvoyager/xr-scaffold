package com.xr.recommend.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xr.base.core.FixedLengthList;
import com.xr.recommend.common.entity.ActionEntity;
import com.xr.recommend.common.enums.ActionType;
import com.xr.recommend.common.service.IActionService;
import com.xr.recommend.common.statistics.ActionStatistic;
import com.xr.recommend.mapper.ActionMapper;
import com.xr.recommend.processor.ActionProcessor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

  @Override
  public List<ActionStatistic> actionStatistic(String platformId, String datasourceId, long startTimeInSeconds, long endTimeInSeconds, ActionType actionType, int n, FixedLengthList<String> excludeItemList) {
    Map<String, Object> condition = new HashMap<>();
    condition.put("platformId", platformId);
    condition.put("datasourceId", datasourceId);
    condition.put("startTimeInSeconds", startTimeInSeconds);
    condition.put("endTimeInSeconds", endTimeInSeconds);
    condition.put("actionCode", actionType.getCode());
    condition.put("size", n);
    condition.put("excludeItemList", excludeItemList);
    List<ActionStatistic> statistics = getBaseMapper().actionStatistic(condition);
    statistics.parallelStream().forEach(itm->{
      itm.setActionType(actionType);
    });
    return statistics;
  }

}
