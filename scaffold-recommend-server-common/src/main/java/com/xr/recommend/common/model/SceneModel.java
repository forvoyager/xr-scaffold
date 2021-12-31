package com.xr.recommend.common.model;

import com.xr.base.core.model.BaseModel;

/**
 * <b>author</b>: forvoyager@outlook.com
 * <b>time</b>: 2021-12-09 17:09:28 <br>
 * <b>description</b>: 推荐场景 模型 <br>
 */
public class SceneModel extends BaseModel {

  public static final String SCENE_ID = "scene_id";
  public static final String DATASOURCE_ID = "datasource_id";
  public static final String NAME = "name";
  public static final String REMARK = "remark";
  public static final String CONFIG = "config";
  public static final String TENANT_ID = "tenant_id";

  /**
   * 场景id
   */
  private Long scene_id;
  /**
   * 数据源id
   */
  private Long datasource_id;
  /**
   * 场景名称
   */
  private String name;
  /**
   * 场景备注
   */
  private String remark;
  /**
   * 配置信息，如点击消息跳转xx地址
   */
  private String config;
  /**
   * 租户id
   */
  private String tenant_id;

  public Long getScene_id() {
    return this.scene_id;
  }

  public SceneModel setScene_id(Long scene_id) {
    this.scene_id = scene_id;
    return this;
  }

  public Long getDatasource_id() {
    return this.datasource_id;
  }

  public SceneModel setDatasource_id(Long datasource_id) {
    this.datasource_id = datasource_id;
    return this;
  }

  public String getName() {
    return this.name;
  }

  public SceneModel setName(String name) {
    this.name = name;
    return this;
  }

  public String getRemark() {
    return this.remark;
  }

  public SceneModel setRemark(String remark) {
    this.remark = remark;
    return this;
  }

  public String getConfig() {
    return this.config;
  }

  public SceneModel setConfig(String config) {
    this.config = config;
    return this;
  }

  public String getTenant_id() {
    return this.tenant_id;
  }

  public SceneModel setTenant_id(String tenant_id) {
    this.tenant_id = tenant_id;
    return this;
  }

}

