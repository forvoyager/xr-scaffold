package com.xr.recommend.recommender.callback;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.xr.base.core.FixedLengthList;
import com.xr.base.core.util.DateUtil;
import com.xr.recommend.common.Context;
import com.xr.recommend.common.dto.ItemDto;
import com.xr.recommend.common.entity.ItemEntity;
import com.xr.recommend.common.enums.ActionType;
import com.xr.recommend.common.service.IActionService;
import com.xr.recommend.common.service.IItemService;
import com.xr.recommend.common.statistics.ActionStatistic;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.*;

/**
 * @Author: forvoyager@outlook.com
 * @Time: 2022-12-01 19:38:00
 * @Description: 热门统计（最近访问最多的） 召回
 */
@Component
public class HotStatisticCallbackProcessor extends AbstractCallbackProcessor {

  @Autowired
  private IActionService actionService;

  @Autowired
  private IItemService itemService;

  @Override
  public List<ItemDto> callbackItem(Context context, FixedLengthList<String> excludeItemList) throws Exception {
    // 按统计信息召回最近的热门商品（点击/看得最多的商品）
    List<ActionStatistic> statistics = actionService.actionStatistic(
            context.getPlatformId(), context.getDatasourceId(),
            DateUtil.toSecond(LocalDateTime.now().minusDays(1000)), DateUtil.toSecond(LocalDateTime.now()),
            ActionType.click, context.getCallbackSize(), excludeItemList);
    if(statistics.size() == 0){ return Collections.EMPTY_LIST; }

    // 查询商品信息
    List<String> itemIds = new ArrayList<>();
    statistics.parallelStream().forEach(itm -> {
      itemIds.add(itm.getItemId());
    });
    QueryWrapper<ItemEntity> itemQuery = new QueryWrapper<>();
    itemQuery.in(ItemEntity.ITEM_ID, itemIds);
    List<ItemEntity> entities = itemService.list(itemQuery);
    if(entities.size() == 0){ return Collections.EMPTY_LIST; }

    // 按原始热度排序，输出
    Map<String, ItemEntity> itemMap = new HashMap<>();
    entities.parallelStream().forEach(itm -> {
      itemMap.put(itm.getItemId(), itm);
    });
    final List<ItemDto> callbackItemList = new ArrayList<>();
    statistics.forEach(itm -> {
      ItemDto itemDto = new ItemDto();
      BeanUtils.copyProperties(itemMap.get(itm.getItemId()), itemDto);
      callbackItemList.add(itemDto);
    });

    return callbackItemList;
  }
}
