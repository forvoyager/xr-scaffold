package com.xr.recommend.common.model;

import com.xr.base.core.model.BaseModel;

/**
 * <b>author</b>: forvoyager@outlook.com
 * <b>time</b>: 2021-08-10 13:19:29 <br>
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
  public static final String CONTENT = "content";
  public static final String AUTHOR = "author";
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
   * 物品id
   */
  private String item_id;
  /**
   * 物品类型 0-item 1-article 2-video 3-audio 4-image
   */
  private Integer type;
  /**
   * 物品类别，多个用/隔开
   */
  private String category;
  /**
   * 状态 0可推荐 1不可推荐 2置顶
   */
  private Integer status;
  /**
   * 标题
   */
  private String title;
  /**
   * 简介/摘要/正文关键片段等
   */
  private String content;
  /**
   * 作者
   */
  private String author;
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

  public String getContent() {
    return this.content;
  }

  public ItemModel setContent(String content) {
    this.content = content;
    return this;
  }

  public String getAuthor() {
    return this.author;
  }

  public ItemModel setAuthor(String author) {
    this.author = author;
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

}

