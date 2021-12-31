package com.xr.recommend.service.impl;

import com.xr.recommend.common.AbstractRecommender;
import com.xr.recommend.common.Context;
import com.xr.recommend.common.enums.RecommendSceneType;
import com.xr.recommend.common.model.ItemModel;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;

/**
 * @author: forvoyager@outlook.com
 * @time: 2021-12-20 19:10:00
 * @description: 热门物品推荐器
 */
@Component
public class HotRecommender extends AbstractRecommender {
  @Override
  public List<ItemModel> callItem(Context context, List<String> excludeItemList) throws Exception {
    return null;
  }

  @Override
  public List<ItemModel> orderItem(Context context, List<ItemModel> itemList) throws Exception {
    return itemList;
  }

  @Override
  public List<RecommendSceneType> supportRecommendScene() {
    return Arrays.asList(RecommendSceneType.hot);
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
