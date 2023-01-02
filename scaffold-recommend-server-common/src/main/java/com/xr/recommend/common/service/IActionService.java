package com.xr.recommend.common.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.xr.base.core.FixedLengthList;
import com.xr.recommend.common.entity.ActionEntity;
import com.xr.recommend.common.enums.ActionType;
import com.xr.recommend.common.statistics.ActionStatistic;

import java.util.List;

/**
 * <p>
 * 行为数据 服务类
 * </p>
 *
 * @author forvoyager@outlook.com
 * @since 2022-04-01
 */
public interface IActionService extends IService<ActionEntity> {

  /**
   * 上传行为数据
   * @param entity 实体对象
   * @return 主键id
   */
  void upload(ActionEntity entity) throws Exception;

  /**
   * 根据行为类型统计数据
   * @param startTimeInSeconds 开始时间
   * @param endTimeInSeconds 结束时间
   * @param actionType 行为类型
   * @param n 返回n条数据
   * @param excludeItemList 排除的数据
   * @return
   */
  List<ActionStatistic> actionStatistic(String platformId, String datasourceId, long startTimeInSeconds, long endTimeInSeconds, ActionType actionType, int n, FixedLengthList<String> excludeItemList);

}
