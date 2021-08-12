package com.xr.recommend.common.model;

import com.xr.base.core.model.BaseModel;

/**
 * <b>author</b>: forvoyager@outlook.com
 * <b>time</b>: 2021-08-12 10:20:19 <br>
 * <b>description</b>: 行为数据 模型 <br>
 */
public class ActionModel extends BaseModel {

  public static final String ACTION_ID = "action_id";
  public static final String DATASOURCE_ID = "datasource_id";
  public static final String USER_ID = "user_id";
  public static final String ITEM_TYPE = "item_type";
  public static final String ITEM_ID = "item_id";
  public static final String ACTION_TYPE = "action_type";
  public static final String ACTION_TIME = "action_time";
  public static final String TRACE_ID = "trace_id";
  public static final String EXTEND = "extend";

  /**
   * 主键id
   */
  private Long action_id;
  /**
   * 数据源id
   */
  private Long datasource_id;
  /**
   * 用户id
   */
  private String user_id;
  /**
   * 物品类型 0-item 1-article 2-video 3-audio 4-image
   */
  private Integer item_type;
  /**
   * 物品id
   */
  private String item_id;
  /**
   * 行为类型 见系统枚举ActionType
   */
  private Integer action_type;
  /**
   * 行为发生时间（UTC），单位秒
   */
  private Integer action_time;
  /**
   * 跟踪id，跟踪被推荐的物品
   */
  private String trace_id;
  /**
   * 扩展字段json, key:value
   */
  private String extend;

  public Long getAction_id() {
    return this.action_id;
  }

  public ActionModel setAction_id(Long action_id) {
    this.action_id = action_id;
    return this;
  }

  public Long getDatasource_id() {
    return this.datasource_id;
  }

  public ActionModel setDatasource_id(Long datasource_id) {
    this.datasource_id = datasource_id;
    return this;
  }

  public String getUser_id() {
    return this.user_id;
  }

  public ActionModel setUser_id(String user_id) {
    this.user_id = user_id;
    return this;
  }

  public Integer getItem_type() {
    return this.item_type;
  }

  public ActionModel setItem_type(Integer item_type) {
    this.item_type = item_type;
    return this;
  }

  public String getItem_id() {
    return this.item_id;
  }

  public ActionModel setItem_id(String item_id) {
    this.item_id = item_id;
    return this;
  }

  public Integer getAction_type() {
    return this.action_type;
  }

  public ActionModel setAction_type(Integer action_type) {
    this.action_type = action_type;
    return this;
  }

  public Integer getAction_time() {
    return this.action_time;
  }

  public ActionModel setAction_time(Integer action_time) {
    this.action_time = action_time;
    return this;
  }

  public String getTrace_id() {
    return this.trace_id;
  }

  public ActionModel setTrace_id(String trace_id) {
    this.trace_id = trace_id;
    return this;
  }

  public String getExtend() {
    return this.extend;
  }

  public ActionModel setExtend(String extend) {
    this.extend = extend;
    return this;
  }

}

