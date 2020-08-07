package com.xr.scaffold.account.common.model;

import com.xr.base.core.model.BaseModel;

/**
 * <b>author</b>: yang.changyan@foundbyte.com
 * <b>time</b>: 2020-08-07 10:34:52 <br>
 * <b>description</b>: 用户表 模型 <br>
 */
public class UserModel extends BaseModel {

  public static final String USER_ID = "user_id";
  public static final String NICK_NAME = "nick_name";
  public static final String STATUS = "status";

  /**
   * 用户id
   */
  private Long user_id;
  /**
   * 用户昵称
   */
  private String nick_name;
  /**
   * 状态 0锁定 1有效
   */
  private Integer status;

  public Long getUser_id() {
    return this.user_id;
  }

  public UserModel setUser_id(Long user_id) {
    this.user_id = user_id;
    return this;
  }

  public String getNick_name() {
    return this.nick_name;
  }

  public UserModel setNick_name(String nick_name) {
    this.nick_name = nick_name;
    return this;
  }

  public Integer getStatus() {
    return this.status;
  }

  public UserModel setStatus(Integer status) {
    this.status = status;
    return this;
  }

}

