package com.xr.recommend.common.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * <p>
 * 推荐数据源
 * </p>
 *
 * @author forvoyager@outlook.com
 * @since 2022-04-02
 */
@TableName("res_datasource")
public class DatasourceEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 数据源id
     */
    @TableId(value = "datasource_id", type = IdType.AUTO)
    private Long datasourceId;

    /**
     * 数据源编码
     */
    private String sourceCode;

    /**
     * 数据源名称
     */
    private String name;

    /**
     * 状态 0无效 1有效
     */
    private Integer status;

    /**
     * 备注
     */
    private String remark;

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

    public Long getDatasourceId() {
        return datasourceId;
    }

    public DatasourceEntity setDatasourceId(Long datasourceId) {
        this.datasourceId = datasourceId;
        return this;
    }
    public String getSourceCode() {
        return sourceCode;
    }

    public DatasourceEntity setSourceCode(String sourceCode) {
        this.sourceCode = sourceCode;
        return this;
    }
    public String getName() {
        return name;
    }

    public DatasourceEntity setName(String name) {
        this.name = name;
        return this;
    }
    public Integer getStatus() {
        return status;
    }

    public DatasourceEntity setStatus(Integer status) {
        this.status = status;
        return this;
    }
    public String getRemark() {
        return remark;
    }

    public DatasourceEntity setRemark(String remark) {
        this.remark = remark;
        return this;
    }
    public String getTenantId() {
        return tenantId;
    }

    public DatasourceEntity setTenantId(String tenantId) {
        this.tenantId = tenantId;
        return this;
    }
    public LocalDateTime getCreateTime() {
        return createTime;
    }

    public DatasourceEntity setCreateTime(LocalDateTime createTime) {
        this.createTime = createTime;
        return this;
    }
    public LocalDateTime getUpdateTime() {
        return updateTime;
    }

    public DatasourceEntity setUpdateTime(LocalDateTime updateTime) {
        this.updateTime = updateTime;
        return this;
    }

    public static final String DATASOURCE_ID = "datasource_id";

    public static final String SOURCE_CODE = "source_code";

    public static final String NAME = "name";

    public static final String STATUS = "status";

    public static final String REMARK = "remark";

    public static final String TENANT_ID = "tenant_id";

    public static final String CREATE_TIME = "create_time";

    public static final String UPDATE_TIME = "update_time";

    @Override
    public String toString() {
        return "DatasourceEntity{" +
            "datasourceId=" + datasourceId +
            ", sourceCode=" + sourceCode +
            ", name=" + name +
            ", status=" + status +
            ", remark=" + remark +
            ", tenantId=" + tenantId +
            ", createTime=" + createTime +
            ", updateTime=" + updateTime +
        "}";
    }
}
