package com.xr.recommend.common.model;

import com.xr.base.core.model.BaseModel;

/**
 * <b>author</b>: forvoyager@outlook.com
 * <b>time</b>: 2021-08-10 13:19:29 <br>
 * <b>description</b>: 用户数据 模型 <br>
 */
public class UserModel extends BaseModel {

  public static final String ID = "id";
  public static final String DATASOURCE_ID = "datasource_id";
  public static final String USER_ID = "user_id";
  public static final String AGE = "age";
  public static final String GENDER = "gender";
  public static final String TAGS = "tags";
  public static final String EXTEND = "extend";

  /**
   * 主键id
   */
  private Long id;
  /**
   * 数据源id
   */
  private Long datasource_id;
  /**
   * 用户id
   */
  private String user_id;
  /**
   * 年龄
   */
  private Integer age;
  /**
   * 性别 0未知 1男 2女
   */
  private Integer gender;
  /**
   * 用户标签List[String]，如["抠脚大汉"]
   */
  private String tags;
  /**
   * 扩展字段json, key:value
   */
  private String extend;

  public Long getId() {
    return this.id;
  }

  public UserModel setId(Long id) {
    this.id = id;
    return this;
  }

  public Long getDatasource_id() {
    return this.datasource_id;
  }

  public UserModel setDatasource_id(Long datasource_id) {
    this.datasource_id = datasource_id;
    return this;
  }

  public String getUser_id() {
    return this.user_id;
  }

  public UserModel setUser_id(String user_id) {
    this.user_id = user_id;
    return this;
  }

  public Integer getAge() {
    return this.age;
  }

  public UserModel setAge(Integer age) {
    this.age = age;
    return this;
  }

  public Integer getGender() {
    return this.gender;
  }

  public UserModel setGender(Integer gender) {
    this.gender = gender;
    return this;
  }

  public String getTags() {
    return this.tags;
  }

  public UserModel setTags(String tags) {
    this.tags = tags;
    return this;
  }

  public String getExtend() {
    return this.extend;
  }

  public UserModel setExtend(String extend) {
    this.extend = extend;
    return this;
  }

}

