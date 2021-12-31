package com.xr.recommend.api.common.dto;

import com.xr.recommend.common.enums.ActionType;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;

/**
 * @author: forvoyager@outlook.com
 * @time: 2021-12-09 17:26:00
 * @description: 行为数据
 */
@ApiModel(value = "行为数据")
public class ActionDto implements Serializable {

  @ApiModelProperty(value = "行为id")
  private String actionId;

  @ApiModelProperty(value = "用户id")
  private String userId;

  @ApiModelProperty(value = "物品id")
  private String itemId;

  @ApiModelProperty(value = "行为类型编码 见系统枚举ActionType")
  private ActionType type;

  @ApiModelProperty(value = "行为发生时间（UTC），单位秒")
  private long actionTime;

  @ApiModelProperty(value = "行为评分，不设置则使用默认评分")
  private int actionScore;

  @ApiModelProperty(value = "跟踪id，跟踪被推荐的物品")
  private String traceId;

  @ApiModelProperty(value = "扩展字段json, key:value")
  private String extend;

  public String getActionId() {
    return actionId;
  }

  public void setActionId(String actionId) {
    this.actionId = actionId;
  }

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

  public ActionType getType() {
    return type;
  }

  public void setType(ActionType type) {
    this.type = type;
  }

  public long getActionTime() {
    return actionTime;
  }

  public void setActionTime(long actionTime) {
    this.actionTime = actionTime;
  }

  public int getActionScore() {
    return actionScore;
  }

  public void setActionScore(int actionScore) {
    this.actionScore = actionScore;
  }

  public String getTraceId() {
    return traceId;
  }

  public void setTraceId(String traceId) {
    this.traceId = traceId;
  }

  public String getExtend() {
    return extend;
  }

  public void setExtend(String extend) {
    this.extend = extend;
  }
}
