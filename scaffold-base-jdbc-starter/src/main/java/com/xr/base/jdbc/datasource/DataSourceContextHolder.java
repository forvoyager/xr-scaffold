package com.xr.base.jdbc.datasource;

import com.xr.base.core.enums.Cluster;

/**
 * <b>author</b>: forvoyager@outlook.com
 * <b>time</b>: 2020-01-15 17:05:00 <br>
 * <b>description</b>:保存上下文Data Source类型（线程安全）
 */
public class DataSourceContextHolder {

  private static ThreadLocal<Cluster> contextHolder = new ThreadLocal<Cluster>();

  public static void setMaster(){
    contextHolder.set(Cluster.master);
  }
  public static void setSlave(){
    contextHolder.set(Cluster.slave);
  }

  public static Cluster getDataSourceType() {
    return contextHolder.get();
  }

  public static void clear() {
    contextHolder.remove();
  }
}
