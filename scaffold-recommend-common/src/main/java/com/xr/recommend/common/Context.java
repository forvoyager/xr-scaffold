package com.xr.recommend.common;

/**
 * @author: forvoyager@outlook.com
 * @time: 2021-12-20 15:44:00
 * @description: 推荐上下文
 */
public class Context {
  /**
   * 平台id
   */
  private String platformId;
  /**
   * 数据源id
   */
  private String datasourceId;
  /**
   * 用户id
   */
  private String userId;
  /**
   * 推荐数量
   */
  private int recSize;
  /**
   * 物品id
   */
  private String itemId;

  /**
   * 排除的物品数量（如：最近推荐过的100个物品不会重复推送）
   */
  private int excludeItemSize = 1000;
  /**
   * 召回物品数量
   */
  private int callbackSize = 500;
  /**
   * 粗排序物品数量
   */
  private int orderItemSize = 500;
  /**
   * 精排序物品数量
   */
  private int fineOrderItemSize = 100;
  /**
   * 重排序物品数量
   */
  private int reOrderItemSize = 10;

  public String getPlatformId() {
    return platformId;
  }

  public Context setPlatformId(String platformId) {
    this.platformId = platformId;
    return this;
  }

  public String getDatasourceId() {
    return datasourceId;
  }

  public Context setDatasourceId(String datasourceId) {
    this.datasourceId = datasourceId;
    return this;
  }

  public String getUserId() {
    return userId;
  }

  public Context setUserId(String userId) {
    this.userId = userId;
    return this;
  }

  public int getRecSize() {
    return recSize;
  }

  public Context setRecSize(int recSize) {
    this.recSize = recSize;
    this.reOrderItemSize = recSize;
    this.fineOrderItemSize = recSize * 2;
    this.orderItemSize = recSize * 5;
    this.callbackSize = recSize * 5;
    return this;
  }

  public String getItemId() {
    return itemId;
  }

  public Context setItemId(String itemId) {
    this.itemId = itemId;
    return this;
  }

  public int getExcludeItemSize() {
    return excludeItemSize;
  }

  public int getCallbackSize() {
    return callbackSize;
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
