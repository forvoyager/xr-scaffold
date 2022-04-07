package com.xr.recommend.common;

/**
 * @author: forvoyager@outlook.com
 * @time: 2021-12-20 15:44:00
 * @description: 推荐上下文
 */
public class Context {
  /**
   * 用户id
   */
  private String userId;

  /**
   * 物品id
   */
  private String itemId;

  /**
   * 排除的物品数量（如：最近推荐过的100个物品不会重复推送）
   */
  private final int excludeItemSize = 1000;
  /**
   * 粗排序物品数量
   */
  private final int orderItemSize = 1000;
  /**
   * 精排序物品数量
   */
  private final int fineOrderItemSize = 100;
  /**
   * 重排序物品数量
   */
  private final int reOrderItemSize = 10;

  public String getUserId() {
    return userId;
  }

  public void setUserId(String userId) {
    this.userId = userId;
  }

  public String getItemId() {
    return itemId;
  }

  public void setItemId(String itemId) {
    this.itemId = itemId;
  }

  public int getExcludeItemSize() {
    return excludeItemSize;
  }

  public int getOrderItemSize() {
    return orderItemSize;
  }

  public int getFineOrderItemSize() {
    return fineOrderItemSize;
  }

  public int getReOrderItemSize() {
    return reOrderItemSize;
  }
}
