package com.xr.recommend.recommender.order;

import com.xr.recommend.common.Context;
import com.xr.recommend.common.dto.ItemDto;

import java.util.List;

/**
 * @Author: forvoyager@outlook.com
 * @Time: 2023-01-01 21:10:00
 * @Description: 排序 处理器
 */
public abstract class AbstractOrderProcessor {
  /**
   * 推荐列表排序
   * @param itemList
   * @param context
   * @return
   * @throws Exception
   */
  public abstract List<ItemDto> orderItem(Context context, List<ItemDto> itemList) throws Exception;
}
