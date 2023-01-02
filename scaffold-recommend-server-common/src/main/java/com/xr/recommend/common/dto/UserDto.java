package com.xr.recommend.common.dto;

import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * @author: forvoyager@outlook.com
 * @since: 2023-01-02 15:19:20
 * @description: 用户数据 数据传输对象
 */
public class UserDto implements Serializable {

  @ApiModelProperty(value = "主键id")
  private Long id;

  @ApiModelProperty(value = "数据源id")
  private String datasourceId;

  @ApiModelProperty(value = "用户id")
  private String userId;

  @ApiModelProperty(value = "年龄")
  private Integer age;

  @ApiModelProperty(value = "性别 0女 1男 2未知")
  private Integer gender;

  @ApiModelProperty(value = "用户位置信息，例：{\"location\": {\"lat\": 181.88, \"lon\": -23.57}}")
  private String location;

  @ApiModelProperty(value = "用户标签List[String]，如[\"抠脚大汉\"]")
  private String tags;

  @ApiModelProperty(value = "扩展字段json, key:value")
  private String extend;

  @ApiModelProperty(value = "平台id")
  private String platformId;

  @ApiModelProperty(value = "创建时间")
  private LocalDateTime createTime;

  @ApiModelProperty(value = "更新时间")
  private LocalDateTime updateTime;


  public Long getId() {
    return this.id;
  }

  public UserDto setId(Long id) {
    this.id = id;
    return this;
  }

  public String getDatasourceId() {
    return this.datasourceId;
  }

  public UserDto setDatasourceId(String datasourceId) {
    this.datasourceId = datasourceId;
    return this;
  }

  public String getUserId() {
    return this.userId;
  }

  public UserDto setUserId(String userId) {
    this.userId = userId;
    return this;
  }

  public Integer getAge() {
    return this.age;
  }

  public UserDto setAge(Integer age) {
    this.age = age;
    return this;
  }

  public Integer getGender() {
    return this.gender;
  }

  public UserDto setGender(Integer gender) {
    this.gender = gender;
    return this;
  }

  public String getLocation() {
    return this.location;
  }

  public UserDto setLocation(String location) {
    this.location = location;
    return this;
  }

  public String getTags() {
    return this.tags;
  }

  public UserDto setTags(String tags) {
    this.tags = tags;
    return this;
  }

  public String getExtend() {
    return this.extend;
  }

  public UserDto setExtend(String extend) {
    this.extend = extend;
    return this;
  }

  public String getPlatformId() {
    return this.platformId;
  }

  public UserDto setPlatformId(String platformId) {
    this.platformId = platformId;
    return this;
  }

  public LocalDateTime getCreateTime() {
    return this.createTime;
  }

  public UserDto setCreateTime(LocalDateTime createTime) {
    this.createTime = createTime;
    return this;
  }

  public LocalDateTime getUpdateTime() {
    return this.updateTime;
  }

  public UserDto setUpdateTime(LocalDateTime updateTime) {
    this.updateTime = updateTime;
    return this;
  }

}

