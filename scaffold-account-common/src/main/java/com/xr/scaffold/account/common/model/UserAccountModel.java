package com.xr.scaffold.account.common.model;

import com.xr.base.core.model.BaseModel;

/**
 * <b>author</b>: forvoyager@outlook.com
 * <b>time</b>: 2020-08-07 10:34:52 <br>
 * <b>description</b>: 用户账户表 模型 <br>
 */
public class UserAccountModel extends BaseModel {

  public static final String USER_ID = "user_id";
  public static final String AVAILABLE = "available";
  public static final String FROZEN = "frozen";

  /**
   * 用户id
   */
  private Long user_id;
  /**
   * 可用余额
   */
  private Double available;
  /**
   * 冻结金额
   */
  private Double frozen;

  public Long getUser_id() {
    return this.user_id;
  }

  public UserAccountModel setUser_id(Long user_id) {
    this.user_id = user_id;
    return this;
  }

  public Double getAvailable() {
    return this.available;
  }

  public UserAccountModel setAvailable(Double available) {
    this.available = available;
    return this;
  }

  public Double getFrozen() {
    return this.frozen;
  }

  public UserAccountModel setFrozen(Double frozen) {
    this.frozen = frozen;
    return this;
  }

}

