package com.xr.base.core.model;

import java.beans.Transient;
import java.io.Serializable;
import java.util.Date;

/**
 * <b>author</b>: forvoyager@outlook.com
 * <b>time</b>: 2019-06-18 08:05:38 <br>
 * <b>description</b>: 基础模型数据
 */
public class BaseModel implements Serializable {

  public static final String CREATE_TIME = "create_time";
  public static final String UPDATE_TIME = "update_time";
  public static final String VERSION = "version";
  public static final String WHERE_VERSION = "where_version";

  /**
   * 创建时间
   */
  protected Date create_time;
  /**
   * 最后更新时间
   */
  protected Date update_time;
  /**
   * 版本号
   */
  protected Integer version;
  /**
   * 条件 版本号 （@Transient 不序列化）
   */
  protected Integer where_version;

  public Date getCreate_time() {
    return create_time;
  }

  public void setCreate_time(Date create_time) {
    this.create_time = create_time;
  }

  public Date getUpdate_time() {
    return update_time;
  }

  public void setUpdate_time(Date update_time) {
    this.update_time = update_time;
  }

  public Integer getVersion() {
    return version;
  }

  public void setVersion(Integer version) {
    this.version = version;
  }

  @Transient
  public Integer getWhere_version() {
    return where_version;
  }

  public void setWhere_version(Integer where_version) {
    this.where_version = where_version;
  }
}
