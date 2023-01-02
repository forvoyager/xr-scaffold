package com.xr.recommend.recommender.callback;

import com.xr.base.core.FixedLengthList;
import com.xr.recommend.common.Context;
import com.xr.recommend.common.dto.ItemDto;

import java.util.List;

/**
 * @Author: forvoyager@outlook.com
 * @Time: 2022-12-01 19:25:00
 * @Description: 召回处理器
 */
public abstract class AbstractCallbackProcessor {
  /**
   * 召回推荐列表
   * @param context
   * @param excludeItemList 需要排除的物品列表
   * @return
   * @throws Exception
   */
  public abstract List<ItemDto> callbackItem(Context context, FixedLengthList<String> excludeItemList) throws Exception;

}
