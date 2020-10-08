package com.xr.scaffold.account.common.model;

import java.math.BigDecimal;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.Version;
import java.time.LocalDateTime;
import java.io.Serializable;

/**
 * <p>
 * 用户账户表
 * </p>
 *
 * @author forvoyager@outlook.com
 * @since 2020-10-08
 */
@TableName("xr_user_account")
public class UserAccountModel implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 用户id
     */
    private Long userId;

    /**
     * 可用余额
     */
    private BigDecimal available;

    /**
     * 冻结金额
     */
    private BigDecimal frozen;

    /**
     * 版本号
     */
    @Version
    private Integer version;

    /**
     * 创建时间
     */
    private LocalDateTime createTime;

    /**
     * 最后更新时间
     */
    private LocalDateTime updateTime;

    public Long getUserId() {
        return userId;
    }

    public UserAccountModel setUserId(Long userId) {
        this.userId = userId;
        return this;
    }
    public BigDecimal getAvailable() {
        return available;
    }

    public UserAccountModel setAvailable(BigDecimal available) {
        this.available = available;
        return this;
    }
    public BigDecimal getFrozen() {
        return frozen;
    }

    public UserAccountModel setFrozen(BigDecimal frozen) {
        this.frozen = frozen;
        return this;
    }
    public Integer getVersion() {
        return version;
    }

    public UserAccountModel setVersion(Integer version) {
        this.version = version;
        return this;
    }
    public LocalDateTime getCreateTime() {
        return createTime;
    }

    public UserAccountModel setCreateTime(LocalDateTime createTime) {
        this.createTime = createTime;
        return this;
    }
    public LocalDateTime getUpdateTime() {
        return updateTime;
    }

    public UserAccountModel setUpdateTime(LocalDateTime updateTime) {
        this.updateTime = updateTime;
        return this;
    }

    public static final String USER_ID = "user_id";

    public static final String AVAILABLE = "available";

    public static final String FROZEN = "frozen";

    public static final String VERSION = "version";

    public static final String CREATE_TIME = "create_time";

    public static final String UPDATE_TIME = "update_time";

    @Override
    public String toString() {
        return "UserAccountModel{" +
            "userId=" + userId +
            ", available=" + available +
            ", frozen=" + frozen +
            ", version=" + version +
            ", createTime=" + createTime +
            ", updateTime=" + updateTime +
        "}";
    }
}
