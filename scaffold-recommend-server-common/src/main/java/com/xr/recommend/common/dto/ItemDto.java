package com.xr.recommend.common.dto;

import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * @author: forvoyager@outlook.com
 * @since: 2023-01-02 15:19:20
 * @description: 物品数据 数据传输对象
 */
public class ItemDto implements Serializable {

  @ApiModelProperty(value = "主键id")
  private Long id;

  @ApiModelProperty(value = "数据源id")
  private String datasourceId;

  @ApiModelProperty(value = "物品id")
  private String itemId;

  @ApiModelProperty(value = "物品类型 0-item 1-article 2-video 3-audio 4-image")
  private Integer type;

  @ApiModelProperty(value = "物品类目，支持多级，如：蔬菜/叶菜/白菜")
  private String category;

  @ApiModelProperty(value = "状态 0可推荐 1不可推荐")
  private Integer status;

  @ApiModelProperty(value = "标题")
  private String title;

  @ApiModelProperty(value = "物品作者，如：文章作者、物品所属店铺名称、视频的发布人等")
  private String author;

  @ApiModelProperty(value = "物品图像地址，多个用,隔开")
  private String picUrls;

  @ApiModelProperty(value = "价格")
  private BigDecimal price;

  @ApiModelProperty(value = "物品发布时间，UTC时间，单位：秒")
  private Long publishTime;

  @ApiModelProperty(value = "物品权重，权重越高越容易被推荐，默认1，取值1-100")
  private Integer weight;

  @ApiModelProperty(value = "简介/摘要/正文关键片段等")
  private String content;

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

  public ItemDto setId(Long id) {
    this.id = id;
    return this;
  }

  public String getDatasourceId() {
    return this.datasourceId;
  }

  public ItemDto setDatasourceId(String datasourceId) {
    this.datasourceId = datasourceId;
    return this;
  }

  public String getItemId() {
    return this.itemId;
  }

  public ItemDto setItemId(String itemId) {
    this.itemId = itemId;
    return this;
  }

  public Integer getType() {
    return this.type;
  }

  public ItemDto setType(Integer type) {
    this.type = type;
    return this;
  }

  public String getCategory() {
    return this.category;
  }

  public ItemDto setCategory(String category) {
    this.category = category;
    return this;
  }

  public Integer getStatus() {
    return this.status;
  }

  public ItemDto setStatus(Integer status) {
    this.status = status;
    return this;
  }

  public String getTitle() {
    return this.title;
  }

  public ItemDto setTitle(String title) {
    this.title = title;
    return this;
  }

  public String getAuthor() {
    return this.author;
  }

  public ItemDto setAuthor(String author) {
    this.author = author;
    return this;
  }

  public String getPicUrls() {
    return this.picUrls;
  }

  public ItemDto setPicUrls(String picUrls) {
    this.picUrls = picUrls;
    return this;
  }

  public BigDecimal getPrice() {
    return this.price;
  }

  public ItemDto setPrice(BigDecimal price) {
    this.price = price;
    return this;
  }

  public Long getPublishTime() {
    return this.publishTime;
  }

  public ItemDto setPublishTime(Long publishTime) {
    this.publishTime = publishTime;
    return this;
  }

  public Integer getWeight() {
    return this.weight;
  }

  public ItemDto setWeight(Integer weight) {
    this.weight = weight;
    return this;
  }

  public String getContent() {
    return this.content;
  }

  public ItemDto setContent(String content) {
    this.content = content;
    return this;
  }

  public String getLocation() {
    return this.location;
  }

  public ItemDto setLocation(String location) {
    this.location = location;
    return this;
  }

  public String getTags() {
    return this.tags;
  }

  public ItemDto setTags(String tags) {
    this.tags = tags;
    return this;
  }

  public String getExtend() {
    return this.extend;
  }

  public ItemDto setExtend(String extend) {
    this.extend = extend;
    return this;
  }

  public String getPlatformId() {
    return this.platformId;
  }

  public ItemDto setPlatformId(String platformId) {
    this.platformId = platformId;
    return this;
  }

  public LocalDateTime getCreateTime() {
    return this.createTime;
  }

  public ItemDto setCreateTime(LocalDateTime createTime) {
    this.createTime = createTime;
    return this;
  }

  public LocalDateTime getUpdateTime() {
    return this.updateTime;
  }

  public ItemDto setUpdateTime(LocalDateTime updateTime) {
    this.updateTime = updateTime;
    return this;
  }

}

