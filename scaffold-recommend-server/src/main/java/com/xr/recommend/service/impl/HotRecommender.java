package com.xr.recommend.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.xr.base.core.FixedLengthList;
import com.xr.base.core.util.DateUtil;
import com.xr.recommend.common.AbstractRecommender;
import com.xr.recommend.common.Context;
import com.xr.recommend.common.entity.ItemEntity;
import com.xr.recommend.common.enums.ActionType;
import com.xr.recommend.common.enums.SceneType;
import com.xr.recommend.common.service.IActionService;
import com.xr.recommend.common.service.IItemService;
import com.xr.recommend.common.statistics.ActionStatistic;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.*;

/**
 * @author: forvoyager@outlook.com
 * @time: 2021-12-20 19:10:00
 * @description: 热门物品推荐器
 */
@Component
public class HotRecommender extends AbstractRecommender {

  @Autowired
  private IActionService actionService;

  @Autowired
  private IItemService itemService;

  @Override
  protected List<ItemEntity> callbackItem(Context context, FixedLengthList<String> excludeItemList) throws Exception {
    // 按统计信息召回最近的热门商品（点击/看得最多的商品）
    List<ActionStatistic> statistics = actionService.actionStatistic(DateUtil.toSecond(LocalDateTime.now().minusDays(1000)), DateUtil.toSecond(LocalDateTime.now()), ActionType.click, 50);

    // 查询商品信息
    List<String> itemIds = new ArrayList<>();
    statistics.parallelStream().forEach(itm -> {
      itemIds.add(itm.getItemId());
    });
    if(itemIds.size() == 0){ return Collections.EMPTY_LIST; }
    QueryWrapper<ItemEntity> itemQuery = new QueryWrapper<>();
    itemQuery.in(ItemEntity.ITEM_ID, itemIds);
    List<ItemEntity> entities = itemService.list(itemQuery);
    if(entities.size() == 0){ return Collections.EMPTY_LIST; }

    // 按原始热度排序，输出
    Map<String, ItemEntity> itemMap = new HashMap<>();
    entities.parallelStream().forEach(itm -> {
      itemMap.put(itm.getItemId(), itm);
    });
    final List<ItemEntity> callbackItemList = new ArrayList<>();
    statistics.forEach(itm -> {
      callbackItemList.add(itemMap.get(itm.getItemId()));
    });

    return callbackItemList;
  }

  @Override
  protected List<ItemEntity> orderItem(Context context, List<ItemEntity> itemList) throws Exception {
    return itemList;
  }

  @Override
  public List<SceneType> scene() {
    return Arrays.asList(SceneType.hot);
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
