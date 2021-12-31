package com.xr.recommend.common.model;

import com.xr.base.core.model.BaseModel;

/**
 * <b>author</b>: forvoyager@outlook.com
 * <b>time</b>: 2021-12-09 17:09:28 <br>
 * <b>description</b>: 物品数据 模型 <br>
 */
public class ItemModel extends BaseModel {

  public static final String ID = "id";
  public static final String DATASOURCE_ID = "datasource_id";
  public static final String ITEM_ID = "item_id";
  public static final String TYPE = "type";
  public static final String CATEGORY = "category";
  public static final String STATUS = "status";
  public static final String TITLE = "title";
  public static final String AUTHOR = "author";
  public static final String PIC_URLS = "pic_urls";
  public static final String PRICE = "price";
  public static final String PUBLISH_TIME = "publish_time";
  public static final String WEIGHT = "weight";
  public static final String CONTENT = "content";
  public static final String LOCATION = "location";
  public static final String TAGS = "tags";
  public static final String EXTEND = "extend";
  public static final String TENANT_ID = "tenant_id";

  /**
   * 主键id
   */
  private Long id;
  /**
   * 数据源id
   */
  private Long datasource_id;
  /**
   * 物品id
   */
  private String item_id;
  /**
   * 物品类型 0-item 1-article 2-video 3-audio 4-image
   */
  private Integer type;
  /**
   * 物品类目，支持多级，如：蔬菜/叶菜/白菜
   */
  private String category;
  /**
   * 状态 0可推荐 1不可推荐
   */
  private Integer status;
  /**
   * 标题
   */
  private String title;
  /**
   * 物品作者，如：文章作者、物品所属店铺名称、视频的发布人等
   */
  private String author;
  /**
   * 物品图像地址，多个用,隔开
   */
  private String pic_urls;
  /**
   * 价格
   */
  private Double price;
  /**
   * 物品发布时间，UTC时间，单位：秒
   */
  private Long publish_time;
  /**
   * 物品权重，权重越高越容易被推荐，默认1，取值1-100
   */
  private Integer weight;
  /**
   * 简介/摘要/正文关键片段等
   */
  private String content;
  /**
   * 用户位置信息，例：{"location": {"lat": 181.88, "lon": -23.57}}
   */
  private String location;
  /**
   * 用户标签List[String]，如["抠脚大汉"]
   */
  private String tags;
  /**
   * 扩展字段json, key:value
   */
  private String extend;
  /**
   * 租户id
   */
  private String tenant_id;

  public Long getId() {
    return this.id;
  }

  public ItemModel setId(Long id) {
    this.id = id;
    return this;
  }

  public Long getDatasource_id() {
    return this.datasource_id;
  }

  public ItemModel setDatasource_id(Long datasource_id) {
    this.datasource_id = datasource_id;
    return this;
  }

  public String getItem_id() {
    return this.item_id;
  }

  public ItemModel setItem_id(String item_id) {
    this.item_id = item_id;
    return this;
  }

  public Integer getType() {
    return this.type;
  }

  public ItemModel setType(Integer type) {
    this.type = type;
    return this;
  }

  public String getCategory() {
    return this.category;
  }

  public ItemModel setCategory(String category) {
    this.category = category;
    return this;
  }

  public Integer getStatus() {
    return this.status;
  }

  public ItemModel setStatus(Integer status) {
    this.status = status;
    return this;
  }

  public String getTitle() {
    return this.title;
  }

  public ItemModel setTitle(String title) {
    this.title = title;
    return this;
  }

  public String getAuthor() {
    return this.author;
  }

  public ItemModel setAuthor(String author) {
    this.author = author;
    return this;
  }

  public String getPic_urls() {
    return this.pic_urls;
  }

  public ItemModel setPic_urls(String pic_urls) {
    this.pic_urls = pic_urls;
    return this;
  }

  public Double getPrice() {
    return this.price;
  }

  public ItemModel setPrice(Double price) {
    this.price = price;
    return this;
  }

  public Long getPublish_time() {
    return this.publish_time;
  }

  public ItemModel setPublish_time(Long publish_time) {
    this.publish_time = publish_time;
    return this;
  }

  public Integer getWeight() {
    return this.weight;
  }

  public ItemModel setWeight(Integer weight) {
    this.weight = weight;
    return this;
  }

  public String getContent() {
    return this.content;
  }

  public ItemModel setContent(String content) {
    this.content = content;
    return this;
  }

  public String getLocation() {
    return this.location;
  }

  public ItemModel setLocation(String location) {
    this.location = location;
    return this;
  }

  public String getTags() {
    return this.tags;
  }

  public ItemModel setTags(String tags) {
    this.tags = tags;
    return this;
  }

  public String getExtend() {
    return this.extend;
  }

  public ItemModel setExtend(String extend) {
    this.extend = extend;
    return this;
  }

  public String getTenant_id() {
    return this.tenant_id;
  }

  public ItemModel setTenant_id(String tenant_id) {
    this.tenant_id = tenant_id;
    return this;
  }

}

