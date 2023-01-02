package com.xr.recommend.recommender;

import com.xr.base.core.FixedLengthList;
import com.xr.recommend.common.AbstractRecommender;
import com.xr.recommend.common.Context;
import com.xr.recommend.common.dto.ItemDto;
import com.xr.recommend.recommender.callback.HotStatisticCallbackProcessor;
import com.xr.recommend.recommender.order.DefaultOrderProcessor;
import com.xr.recommend.recommender.order.fineOrder.DefaultFineOrderProcessor;
import com.xr.recommend.recommender.order.reOrder.DefaultReOrderProcessor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author: forvoyager@outlook.com
 * @time: 2021-12-20 19:10:00
 * @description: 热门物品推荐器
 */
@Component
public class HotRecommender extends AbstractRecommender {

  @Autowired
  private HotStatisticCallbackProcessor hotStatisticCallbackProcessor;

  @Autowired
  private DefaultOrderProcessor orderProcessor;

  @Autowired
  private DefaultFineOrderProcessor fineOrderProcessor;

  @Autowired
  private DefaultReOrderProcessor reOrderProcessor;

  @Override
  protected List<ItemDto> callbackItem(Context context, FixedLengthList<String> excludeItemList) throws Exception {
    return hotStatisticCallbackProcessor.callbackItem(context, excludeItemList);
  }

  @Override
  protected List<ItemDto> orderItem(Context context, List<ItemDto> itemList) throws Exception {
    return orderProcessor.orderItem(context, itemList);
  }

  @Override
  protected List<ItemDto> fineOrderItem(Context context, List<ItemDto> itemList) throws Exception {
    return fineOrderProcessor.orderItem(context, itemList);
  }

  @Override
  protected List<ItemDto> reOrderItem(Context context, List<ItemDto> itemList) throws Exception {
    return reOrderProcessor.orderItem(context, itemList);
  }

  @Override
  public String name() {
    return "热门推荐";
  }

  @Override
  public String description() {
    return "基于历史数据统计，为用户推荐热门物品。";
  }
}
