package com.xr.recommend.recommender.order;

import com.xr.recommend.common.Context;
import com.xr.recommend.common.dto.ItemDto;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @Author: forvoyager@outlook.com
 * @Time: 2023-01-01 21:17:00
 * @Description: 默认粗排 处理器
 */
@Component
public class DefaultOrderProcessor extends AbstractOrderProcessor {
  @Override
  public List<ItemDto> orderItem(Context context, List<ItemDto> itemList) throws Exception {
    // todo
    return itemList;
  }
}
