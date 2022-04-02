package com.xr.recommend.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.xr.recommend.common.entity.ActionEntity;
import com.xr.recommend.common.statistics.ActionStatistic;

import java.util.List;

/**
 * <p>
 * 行为数据 Mapper 接口
 * </p>
 *
 * @author forvoyager@outlook.com
 * @since 2022-04-01
 */
public interface ActionMapper extends BaseMapper<ActionEntity> {

  List<ActionStatistic> actionStatistic(long startTime, long endTime, int actionCode);

}
