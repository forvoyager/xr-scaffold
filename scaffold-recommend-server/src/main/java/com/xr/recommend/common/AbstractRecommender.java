package com.xr.recommend.common;

import com.xr.base.core.FixedLengthList;
import com.xr.base.core.util.CollectionUtils;
import com.xr.base.core.util.StringUtils;
import com.xr.recommend.common.dto.ItemDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.*;

/**
 * @author: forvoyager@outlook.com
 * @time: 2021-12-08 12:57:00
 * @description: 抽象的推荐器，定义推荐过程
 */
public abstract class AbstractRecommender {

  protected Logger logger = LoggerFactory.getLogger(getClass());

  protected Map<String, FixedLengthList<String>> excludeItemList = new HashMap<>();

  /**
   * 获取推荐结果
   * @param context
   * @return
   * @throws Exception
   */
  public final List<ItemDto> recommend(Context context) throws Exception{

    // 获取需要过滤的物品id
    FixedLengthList excludeItemList = this.getExcludeItemList(context);

    // 召回推荐列表
    List<ItemDto> itemList = this.callbackItem(context, excludeItemList);

    // 推荐列表排序（粗排）
    itemList = this.orderItem(context, itemList);
    itemList = this.subList(itemList, context.getPostOrderItemSize());

    // 推荐列表排序（精排）
    itemList = this.fineOrderItem(context, itemList);
    itemList = this.subList(itemList, context.getPostFineOrderItemSize());

    // 推荐列表排序（重排）
    itemList = this.reOrderItem(context, itemList);
    itemList = this.subList(itemList, context.getPostReOrderItemSize());

    // 更新推荐排除列表（下次推荐时不再推荐列表内的物品）
    this.updateExcludeItemList(context, itemList);

    return itemList;
  }

  /**
   * 截取子列表
   * @param list
   * @param subSize
   * @return
   */
  private List<ItemDto> subList(List<ItemDto> list, int subSize){
    return list.size()>subSize ? list.subList(0, subSize) : list;
  }

  /**
   * 获取需要过滤掉的物品id
   * @param context 推荐上下文
   * @return
   */
  private FixedLengthList getExcludeItemList(Context context){
    return excludeItemList.getOrDefault(context.getUserId(), new FixedLengthList());
  }

  /**
   * 更新需要过滤掉的物品id
   * @param context
   * @param itemList
   * @throws Exception
   */
  private void updateExcludeItemList(Context context, List<ItemDto> itemList){
    // todo 异步处理
    if(CollectionUtils.isEmpty(itemList)){ return; }

    String userId = context.getUserId();
    if(StringUtils.isEmpty(userId)){ return; }

    FixedLengthList<String> exclude = excludeItemList.get(userId);
    if(exclude == null) {
      exclude = new FixedLengthList<>(context.getExcludeItemSize());
      excludeItemList.put(userId, exclude);
    }
    for(ItemDto im : itemList){
      exclude.add(im.getItemId());
    }
  }

  /**
   * 召回推荐列表
   * @param excludeItemList 需要排除的物品列表
   * @param context
   * @return
   * @throws Exception
   */
  protected abstract List<ItemDto> callbackItem(Context context, FixedLengthList<String> excludeItemList) throws Exception;

  /**
   * 推荐列表排序（粗排）
   * @param itemList
   * @param context
   * @return
   * @throws Exception
   */
  protected abstract List<ItemDto> orderItem(Context context, List<ItemDto> itemList) throws Exception;

  /**
   * 推荐列表排序（精排）
   * @param itemList
   * @param context
   * @return
   * @throws Exception
   */
  protected List<ItemDto> fineOrderItem(Context context, List<ItemDto> itemList) throws Exception {
    return itemList;
  }

  /**
   * 推荐列表排序（重排）
   * @param itemList
   * @param context
   * @return
   * @throws Exception
   */
  protected List<ItemDto> reOrderItem(Context context, List<ItemDto> itemList) throws Exception {
    return itemList;
  }

  /**
   * 推荐器名称
   * @return
   */
  public abstract String name();

  /**
   * 推荐器描述
   * @return
   */
  public abstract String description();
}
