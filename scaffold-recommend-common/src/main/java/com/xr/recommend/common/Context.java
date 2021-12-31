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
}
