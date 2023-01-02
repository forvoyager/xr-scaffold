package com.xr.recommend.common.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.Version;
import com.baomidou.mybatisplus.annotation.TableId;
import java.time.LocalDateTime;
import java.io.Serializable;

/**
 * <p>
 * 行为数据
 * </p>
 *
 * @author forvoyager@outlook.com
 * @since 2023-01-02
 */
@TableName("res_action")
public class ActionEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键id
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 数据源id
     */
    private String datasourceId;

    /**
     * 用户id
     */
    private String userId;

    /**
     * 物品id
     */
    private String itemId;

    /**
     * 行为类型编码 见系统枚举ActionType
     */
    private Integer actionCode;

    /**
     * 行为发生时间（UTC），单位秒
     */
    private Long actionTime;

    /**
     * 行为评分，不设置则使用默认评分
     */
    private Integer actionScore;

    /**
     * 跟踪id，跟踪被推荐的物品
     */
    private String traceId;

    /**
     * 扩展字段json, key:value
     */
    private String extend;

    /**
     * 平台id
     */
    private String platformId;

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

    public ActionEntity setId(Long id) {
        this.id = id;
        return this;
    }

    public String getDatasourceId() {
        return datasourceId;
    }

    public ActionEntity setDatasourceId(String datasourceId) {
        this.datasourceId = datasourceId;
        return this;
    }
    public String getUserId() {
        return userId;
    }

    public ActionEntity setUserId(String userId) {
        this.userId = userId;
        return this;
    }
    public String getItemId() {
        return itemId;
    }

    public ActionEntity setItemId(String itemId) {
        this.itemId = itemId;
        return this;
    }
    public Integer getActionCode() {
        return actionCode;
    }

    public ActionEntity setActionCode(Integer actionCode) {
        this.actionCode = actionCode;
        return this;
    }
    public Long getActionTime() {
        return actionTime;
    }

    public ActionEntity setActionTime(Long actionTime) {
        this.actionTime = actionTime;
        return this;
    }
    public Integer getActionScore() {
        return actionScore;
    }

    public ActionEntity setActionScore(Integer actionScore) {
        this.actionScore = actionScore;
        return this;
    }
    public String getTraceId() {
        return traceId;
    }

    public ActionEntity setTraceId(String traceId) {
        this.traceId = traceId;
        return this;
    }
    public String getExtend() {
        return extend;
    }

    public ActionEntity setExtend(String extend) {
        this.extend = extend;
        return this;
    }
    public String getPlatformId() {
        return platformId;
    }

    public ActionEntity setPlatformId(String platformId) {
        this.platformId = platformId;
        return this;
    }
    public LocalDateTime getCreateTime() {
        return createTime;
    }

    public ActionEntity setCreateTime(LocalDateTime createTime) {
        this.createTime = createTime;
        return this;
    }
    public LocalDateTime getUpdateTime() {
        return updateTime;
    }

    public ActionEntity setUpdateTime(LocalDateTime updateTime) {
        this.updateTime = updateTime;
        return this;
    }

    public static final String ID = "id";

    public static final String DATASOURCE_ID = "datasource_id";

    public static final String USER_ID = "user_id";

    public static final String ITEM_ID = "item_id";

    public static final String ACTION_CODE = "action_code";

    public static final String ACTION_TIME = "action_time";

    public static final String ACTION_SCORE = "action_score";

    public static final String TRACE_ID = "trace_id";

    public static final String EXTEND = "extend";

    public static final String PLATFORM_ID = "platform_id";

    public static final String CREATE_TIME = "create_time";

    public static final String UPDATE_TIME = "update_time";

    @Override
    public String toString() {
        return "ActionEntity{" +
            "id=" + id +
            ", datasourceId=" + datasourceId +
            ", userId=" + userId +
            ", itemId=" + itemId +
            ", actionCode=" + actionCode +
            ", actionTime=" + actionTime +
            ", actionScore=" + actionScore +
            ", traceId=" + traceId +
            ", extend=" + extend +
            ", platformId=" + platformId +
            ", createTime=" + createTime +
            ", updateTime=" + updateTime +
        "}";
    }
}
