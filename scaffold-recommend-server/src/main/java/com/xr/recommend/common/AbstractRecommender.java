package com.xr.recommend.common;

import com.xr.recommend.common.enums.RecommendSceneType;
import com.xr.recommend.common.model.ItemModel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Collections;
import java.util.List;

/**
 * @author: forvoyager@outlook.com
 * @time: 2021-12-08 12:57:00
 * @description: 抽象的推荐器，定义推荐过程
 */
public abstract class AbstractRecommender {

  protected Logger logger = LoggerFactory.getLogger(getClass());

  /**
   * 获取推荐结果
   * @param context
   * @return
   * @throws Exception
   */
  public List<ItemModel> doRecommend(Context context) throws Exception{

    // 查询过滤的物品id
    List<String> excludeItemList = getExcludeItemIds(context.getUserId());

    // 召回推荐列表
    List<ItemModel> callItemList = this.callItem(context, excludeItemList);

    // 推荐列表排序（粗排）
    callItemList = this.orderItem(context, callItemList);

    // 推荐列表排序（精排）
    callItemList = this.fineOrderItem(context, callItemList);

    // 推荐列表排序（重排）
    callItemList = this.reOrderItem(context, callItemList);

    return callItemList;
  }

  /**
   * 获取需要过滤掉的物品id
   * @param userId
   * @return
   */
  private List<String> getExcludeItemIds(String userId){

    return Collections.EMPTY_LIST;
  }

  /**
   * 召回推荐列表
   * @param excludeItemList 需要排除的物品列表
   * @param context
   * @return
   * @throws Exception
   */
  public abstract List<ItemModel> callItem(Context context, List<String> excludeItemList) throws Exception;

  /**
   * 推荐列表排序（粗排）
   * @param itemList
   * @param context
   * @return
   * @throws Exception
   */
  public abstract List<ItemModel> orderItem(Context context, List<ItemModel> itemList) throws Exception;

  /**
   * 推荐列表排序（精排）
   * @param itemList
   * @param context
   * @return
   * @throws Exception
   */
  public List<ItemModel> fineOrderItem(Context context, List<ItemModel> itemList) {
    return itemList;
  }

  /**
   * 推荐列表排序（重排）
   * @param itemList
   * @param context
   * @return
   * @throws Exception
   */
  public List<ItemModel> reOrderItem(Context context, List<ItemModel> itemList) {
    return itemList;
  }

  /**
   * 当前推荐器支持的场景
   * @return
   */
  public abstract List<RecommendSceneType> supportRecommendScene();

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
