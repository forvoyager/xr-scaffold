package com.xr.recommend.common.dto;

import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * @author: forvoyager@outlook.com
 * @since: 2023-01-02 15:19:20
 * @description: 行为数据 数据传输对象
 */
public class ActionDto implements Serializable {

  @ApiModelProperty(value = "主键id")
  private Long actionId;

  @ApiModelProperty(value = "数据源id")
  private String datasourceId;

  @ApiModelProperty(value = "用户id")
  private String userId;

  @ApiModelProperty(value = "物品id")
  private String itemId;

  @ApiModelProperty(value = "行为类型编码 见系统枚举ActionType")
  private Integer actionCode;

  @ApiModelProperty(value = "行为发生时间（UTC），单位秒")
  private Long actionTime;

  @ApiModelProperty(value = "行为评分，不设置则使用默认评分")
  private Integer actionScore;

  @ApiModelProperty(value = "跟踪id，跟踪被推荐的物品")
  private String traceId;

  @ApiModelProperty(value = "扩展字段json, key:value")
  private String extend;

  @ApiModelProperty(value = "平台id")
  private String platformId;

  @ApiModelProperty(value = "创建时间")
  private LocalDateTime createTime;

  @ApiModelProperty(value = "更新时间")
  private LocalDateTime updateTime;


  public Long getActionId() {
    return this.actionId;
  }

  public ActionDto setActionId(Long actionId) {
    this.actionId = actionId;
    return this;
  }

  public String getDatasourceId() {
    return this.datasourceId;
  }

  public ActionDto setDatasourceId(String datasourceId) {
    this.datasourceId = datasourceId;
    return this;
  }

  public String getUserId() {
    return this.userId;
  }

  public ActionDto setUserId(String userId) {
    this.userId = userId;
    return this;
  }

  public String getItemId() {
    return this.itemId;
  }

  public ActionDto setItemId(String itemId) {
    this.itemId = itemId;
    return this;
  }

  public Integer getActionCode() {
    return this.actionCode;
  }

  public ActionDto setActionCode(Integer actionCode) {
    this.actionCode = actionCode;
    return this;
  }

  public Long getActionTime() {
    return this.actionTime;
  }

  public ActionDto setActionTime(Long actionTime) {
    this.actionTime = actionTime;
    return this;
  }

  public Integer getActionScore() {
    return this.actionScore;
  }

  public ActionDto setActionScore(Integer actionScore) {
    this.actionScore = actionScore;
    return this;
  }

  public String getTraceId() {
    return this.traceId;
  }

  public ActionDto setTraceId(String traceId) {
    this.traceId = traceId;
    return this;
  }

  public String getExtend() {
    return this.extend;
  }

  public ActionDto setExtend(String extend) {
    this.extend = extend;
    return this;
  }

  public String getPlatformId() {
    return this.platformId;
  }

  public ActionDto setPlatformId(String platformId) {
    this.platformId = platformId;
    return this;
  }

  public LocalDateTime getCreateTime() {
    return this.createTime;
  }

  public ActionDto setCreateTime(LocalDateTime createTime) {
    this.createTime = createTime;
    return this;
  }

  public LocalDateTime getUpdateTime() {
    return this.updateTime;
  }

  public ActionDto setUpdateTime(LocalDateTime updateTime) {
    this.updateTime = updateTime;
    return this;
  }

}

