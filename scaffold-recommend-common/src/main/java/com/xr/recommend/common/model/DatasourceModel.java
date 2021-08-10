package com.xr.recommend.common.model;

import com.xr.base.core.model.BaseModel;

/**
 * <b>author</b>: forvoyager@outlook.com
 * <b>time</b>: 2021-08-10 13:19:29 <br>
 * <b>description</b>: 推荐数据源 模型 <br>
 */
public class DatasourceModel extends BaseModel {

  public static final String DATASOURCE_ID = "datasource_id";
  public static final String SOURCE_CODE = "source_code";
  public static final String NAME = "name";
  public static final String STATUS = "status";

  /**
   * 数据源id
   */
  private Long datasource_id;
  /**
   * 数据源编码
   */
  private String source_code;
  /**
   * 数据源名称
   */
  private String name;
  /**
   * 状态 0无效 1有效
   */
  private Integer status;

  public Long getDatasource_id() {
    return this.datasource_id;
  }

  public DatasourceModel setDatasource_id(Long datasource_id) {
    this.datasource_id = datasource_id;
    return this;
  }

  public String getSource_code() {
    return this.source_code;
  }

  public DatasourceModel setSource_code(String source_code) {
    this.source_code = source_code;
    return this;
  }

  public String getName() {
    return this.name;
  }

  public DatasourceModel setName(String name) {
    this.name = name;
    return this;
  }

  public Integer getStatus() {
    return this.status;
  }

  public DatasourceModel setStatus(Integer status) {
    this.status = status;
    return this;
  }

}

