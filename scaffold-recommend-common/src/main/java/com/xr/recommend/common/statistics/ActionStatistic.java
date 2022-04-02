package com.xr.recommend.common.statistics;

import com.xr.recommend.common.enums.ActionType;

/**
 * @author: forvoyager@outlook.com
 * @time: 2022-04-02 13:46:00
 * @description: 行为统计
 */
public class ActionStatistic {

  /**
   * 操作类型
   */
  private ActionType actionType;

  /**
   * 物品id
   */
  private String itemId;

  /**
   * 次数
   */
  private int times = 0;

  public ActionType getActionType() {
    return actionType;
  }

  public void setActionType(ActionType actionType) {
    this.actionType = actionType;
  }

  public String getItemId() {
    return itemId;
  }

  public void setItemId(String itemId) {
    this.itemId = itemId;
  }

  public int getTimes() {
    return times;
  }

  public void setTimes(int times) {
    this.times = times;
  }
}
