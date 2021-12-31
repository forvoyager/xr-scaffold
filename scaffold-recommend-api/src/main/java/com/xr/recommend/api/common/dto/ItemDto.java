package com.xr.recommend.api.common.dto;

import com.xr.recommend.common.enums.ItemType;
import com.xr.recommend.common.model.ItemModel;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;

/**
 * @author: forvoyager@outlook.com
 * @time: 2021-12-09 17:26:00
 * @description: 物品数据
 */
@ApiModel(value = "物品数据")
public class ItemDto implements Serializable {

  @ApiModelProperty(value = "物品id")
  private String itemId;

  @ApiModelProperty(value = "物品类型 item-物品 article-文章 video-视频 audio-音频 image-图像")
  private ItemType type;

  @ApiModelProperty(value = "物品类目，支持多级，如：蔬菜/叶菜/白菜")
  private String category;

  @ApiModelProperty(value = "物品状态 0可推荐 1不可推荐")
  private int status = 0;

  @ApiModelProperty(value = "物品标题")
  private String title;

  @ApiModelProperty(value = "物品作者，如：文章作者、物品所属店铺名称、视频的发布人等")
  private String author;

  @ApiModelProperty(value = "物品图像地址，多个用,隔开")
  private String picUrls;

  @ApiModelProperty(value = "物品价格")
  private double price;

  @ApiModelProperty(value = "物品发布时间，UTC时间，单位：秒")
  private long publishTime;

  @ApiModelProperty(value = "物品权重，权重越高越容易被推荐，默认1，取值1-100")
  private int weight = 1;

  @ApiModelProperty(value = "物品简介/摘要/正文关键片段等")
  private String content;

  @ApiModelProperty(value = "物品位置信息，例：{\"location\": {\"lat\": 181.88, \"lon\": -23.57}}")
  private String location;

  @ApiModelProperty(value = "物品标签List[String]，如[\"烧烤季\"]")
  private String tags;

  @ApiModelProperty(value = "扩展字段json, key:value")
  private String extend;

  public String getItemId() {
    return itemId;
  }

  public void setItemId(String itemId) {
    this.itemId = itemId;
  }

  public ItemType getType() {
    return type;
  }

  public void setType(ItemType type) {
    this.type = type;
  }

  public String getCategory() {
    return category;
  }

  public void setCategory(String category) {
    this.category = category;
  }

  public int getStatus() {
    return status;
  }

  public void setStatus(int status) {
    this.status = status;
  }

  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  public String getAuthor() {
    return author;
  }

  public void setAuthor(String author) {
    this.author = author;
  }

  public String getPicUrls() {
    return picUrls;
  }

  public void setPicUrls(String picUrls) {
    this.picUrls = picUrls;
  }

  public double getPrice() {
    return price;
  }

  public void setPrice(double price) {
    this.price = price;
  }

  public long getPublishTime() {
    return publishTime;
  }

  public void setPublishTime(long publishTime) {
    this.publishTime = publishTime;
  }

  public int getWeight() {
    return weight;
  }

  public void setWeight(int weight) {
    this.weight = weight;
  }

  public String getContent() {
    return content;
  }

  public void setContent(String content) {
    this.content = content;
  }

  public String getLocation() {
    return location;
  }

  public void setLocation(String location) {
    this.location = location;
  }

  public String getTags() {
    return tags;
  }

  public void setTags(String tags) {
    this.tags = tags;
  }

  public String getExtend() {
    return extend;
  }

  public void setExtend(String extend) {
    this.extend = extend;
  }
}
