package com.xr.recommend.common.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * <p>
 * 推荐场景
 * </p>
 *
 * @author forvoyager@outlook.com
 * @since 2022-04-02
 */
@TableName("res_scene")
public class SceneEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 场景id
     */
    @TableId(value = "scene_id", type = IdType.AUTO)
    private Long sceneId;

    /**
     * 数据源id
     */
    private Long datasourceId;

    /**
     * 场景名称
     */
    private String name;

    /**
     * 场景备注
     */
    private String remark;

    /**
     * 配置信息，如点击消息跳转xx地址
     */
    private String config;

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

    public Long getSceneId() {
        return sceneId;
    }

    public SceneEntity setSceneId(Long sceneId) {
        this.sceneId = sceneId;
        return this;
    }
    public Long getDatasourceId() {
        return datasourceId;
    }

    public SceneEntity setDatasourceId(Long datasourceId) {
        this.datasourceId = datasourceId;
        return this;
    }
    public String getName() {
        return name;
    }

    public SceneEntity setName(String name) {
        this.name = name;
        return this;
    }
    public String getRemark() {
        return remark;
    }

    public SceneEntity setRemark(String remark) {
        this.remark = remark;
        return this;
    }
    public String getConfig() {
        return config;
    }

    public SceneEntity setConfig(String config) {
        this.config = config;
        return this;
    }
    public String getTenantId() {
        return tenantId;
    }

    public SceneEntity setTenantId(String tenantId) {
        this.tenantId = tenantId;
        return this;
    }
    public LocalDateTime getCreateTime() {
        return createTime;
    }

    public SceneEntity setCreateTime(LocalDateTime createTime) {
        this.createTime = createTime;
        return this;
    }
    public LocalDateTime getUpdateTime() {
        return updateTime;
    }

    public SceneEntity setUpdateTime(LocalDateTime updateTime) {
        this.updateTime = updateTime;
        return this;
    }

    public static final String SCENE_ID = "scene_id";

    public static final String DATASOURCE_ID = "datasource_id";

    public static final String NAME = "name";

    public static final String REMARK = "remark";

    public static final String CONFIG = "config";

    public static final String TENANT_ID = "tenant_id";

    public static final String CREATE_TIME = "create_time";

    public static final String UPDATE_TIME = "update_time";

    @Override
    public String toString() {
        return "SceneEntity{" +
            "sceneId=" + sceneId +
            ", datasourceId=" + datasourceId +
            ", name=" + name +
            ", remark=" + remark +
            ", config=" + config +
            ", tenantId=" + tenantId +
            ", createTime=" + createTime +
            ", updateTime=" + updateTime +
        "}";
    }
}
