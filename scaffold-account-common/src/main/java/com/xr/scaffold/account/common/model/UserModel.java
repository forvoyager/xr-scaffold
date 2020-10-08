package com.xr.scaffold.account.common.model;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.Version;
import com.baomidou.mybatisplus.annotation.TableId;
import java.time.LocalDateTime;
import java.io.Serializable;

/**
 * <p>
 * 用户表
 * </p>
 *
 * @author forvoyager@outlook.com
 * @since 2020-10-08
 */
@TableName("xr_user")
public class UserModel implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 用户id
     */
    @TableId(value = "user_id", type = IdType.AUTO)
    private Long userId;

    /**
     * 用户昵称
     */
    private String nickName;

    /**
     * 状态 0锁定 1有效
     */
    private Integer status;

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

    public UserModel setUserId(Long userId) {
        this.userId = userId;
        return this;
    }
    public String getNickName() {
        return nickName;
    }

    public UserModel setNickName(String nickName) {
        this.nickName = nickName;
        return this;
    }
    public Integer getStatus() {
        return status;
    }

    public UserModel setStatus(Integer status) {
        this.status = status;
        return this;
    }
    public Integer getVersion() {
        return version;
    }

    public UserModel setVersion(Integer version) {
        this.version = version;
        return this;
    }
    public LocalDateTime getCreateTime() {
        return createTime;
    }

    public UserModel setCreateTime(LocalDateTime createTime) {
        this.createTime = createTime;
        return this;
    }
    public LocalDateTime getUpdateTime() {
        return updateTime;
    }

    public UserModel setUpdateTime(LocalDateTime updateTime) {
        this.updateTime = updateTime;
        return this;
    }

    public static final String USER_ID = "user_id";

    public static final String NICK_NAME = "nick_name";

    public static final String STATUS = "status";

    public static final String VERSION = "version";

    public static final String CREATE_TIME = "create_time";

    public static final String UPDATE_TIME = "update_time";

    @Override
    public String toString() {
        return "UserModel{" +
            "userId=" + userId +
            ", nickName=" + nickName +
            ", status=" + status +
            ", version=" + version +
            ", createTime=" + createTime +
            ", updateTime=" + updateTime +
        "}";
    }
}
