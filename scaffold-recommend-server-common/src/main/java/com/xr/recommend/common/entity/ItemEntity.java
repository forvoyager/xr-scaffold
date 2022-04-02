package com.xr.recommend.common.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * <p>
 * 物品数据
 * </p>
 *
 * @author forvoyager@outlook.com
 * @since 2022-04-02
 */
@TableName("res_item")
public class ItemEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键id
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 数据源id
     */
    private Long datasourceId;

    /**
     * 物品id
     */
    private String itemId;

    /**
     * 物品类型 0-item 1-article 2-video 3-audio 4-image
     */
    private Integer type;

    /**
     * 物品类目，支持多级，如：蔬菜/叶菜/白菜
     */
    private String category;

    /**
     * 物品状态 0可推荐 1不可推荐
     */
    private Integer status;

    /**
     * 物品标题
     */
    private String title;

    /**
     * 物品作者，如：文章作者、物品所属店铺名称、视频的发布人等
     */
    private String author;

    /**
     * 物品图像地址，多个用,隔开
     */
    private String picUrls;

    /**
     * 物品价格
     */
    private BigDecimal price;

    /**
     * 物品发布时间，UTC时间，单位：秒
     */
    private Long publishTime;

    /**
     * 物品权重，权重越高越容易被推荐，默认1，取值1-100
     */
    private Integer weight;

    /**
     * 物品简介/摘要/正文关键片段等
     */
    private String content;

    /**
     * 物品位置信息，例：{"location": {"lat": 181.88, "lon": -23.57}}
     */
    private String location;

    /**
     * 物品标签List[String]，如["烧烤季"]
     */
    private String tags;

    /**
     * 扩展字段json, key:value
     */
    private String extend;

    /**
     * 租户id
     */
    private String tenantId;

    /**
     * 创建时间
     */
    private LocalDateTime createTime;

    /**
     * 更新时间
     */
    private LocalDateTime updateTime;

    public Long getId() {
        return id;
    }

    public ItemEntity setId(Long id) {
        this.id = id;
        return this;
    }
    public Long getDatasourceId() {
        return datasourceId;
    }

    public ItemEntity setDatasourceId(Long datasourceId) {
        this.datasourceId = datasourceId;
        return this;
    }
    public String getItemId() {
        return itemId;
    }

    public ItemEntity setItemId(String itemId) {
        this.itemId = itemId;
        return this;
    }
    public Integer getType() {
        return type;
    }

    public ItemEntity setType(Integer type) {
        this.type = type;
        return this;
    }
    public String getCategory() {
        return category;
    }

    public ItemEntity setCategory(String category) {
        this.category = category;
        return this;
    }
    public Integer getStatus() {
        return status;
    }

    public ItemEntity setStatus(Integer status) {
        this.status = status;
        return this;
    }
    public String getTitle() {
        return title;
    }

    public ItemEntity setTitle(String title) {
        this.title = title;
        return this;
    }
    public String getAuthor() {
        return author;
    }

    public ItemEntity setAuthor(String author) {
        this.author = author;
        return this;
    }
    public String getPicUrls() {
        return picUrls;
    }

    public ItemEntity setPicUrls(String picUrls) {
        this.picUrls = picUrls;
        return this;
    }
    public BigDecimal getPrice() {
        return price;
    }

    public ItemEntity setPrice(BigDecimal price) {
        this.price = price;
        return this;
    }
    public Long getPublishTime() {
        return publishTime;
    }

    public ItemEntity setPublishTime(Long publishTime) {
        this.publishTime = publishTime;
        return this;
    }
    public Integer getWeight() {
        return weight;
    }

    public ItemEntity setWeight(Integer weight) {
        this.weight = weight;
        return this;
    }
    public String getContent() {
        return content;
    }

    public ItemEntity setContent(String content) {
        this.content = content;
        return this;
    }
    public String getLocation() {
        return location;
    }

    public ItemEntity setLocation(String location) {
        this.location = location;
        return this;
    }
    public String getTags() {
        return tags;
    }

    public ItemEntity setTags(String tags) {
        this.tags = tags;
        return this;
    }
    public String getExtend() {
        return extend;
    }

    public ItemEntity setExtend(String extend) {
        this.extend = extend;
        return this;
    }
    public String getTenantId() {
        return tenantId;
    }

    public ItemEntity setTenantId(String tenantId) {
        this.tenantId = tenantId;
        return this;
    }
    public LocalDateTime getCreateTime() {
        return createTime;
    }

    public ItemEntity setCreateTime(LocalDateTime createTime) {
        this.createTime = createTime;
        return this;
    }
    public LocalDateTime getUpdateTime() {
        return updateTime;
    }

    public ItemEntity setUpdateTime(LocalDateTime updateTime) {
        this.updateTime = updateTime;
        return this;
    }

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

    public static final String CREATE_TIME = "create_time";

    public static final String UPDATE_TIME = "update_time";

    @Override
    public String toString() {
        return "ItemEntity{" +
            "id=" + id +
            ", datasourceId=" + datasourceId +
            ", itemId=" + itemId +
            ", type=" + type +
            ", category=" + category +
            ", status=" + status +
            ", title=" + title +
            ", author=" + author +
            ", picUrls=" + picUrls +
            ", price=" + price +
            ", publishTime=" + publishTime +
            ", weight=" + weight +
            ", content=" + content +
            ", location=" + location +
            ", tags=" + tags +
            ", extend=" + extend +
            ", tenantId=" + tenantId +
            ", createTime=" + createTime +
            ", updateTime=" + updateTime +
        "}";
    }
}
