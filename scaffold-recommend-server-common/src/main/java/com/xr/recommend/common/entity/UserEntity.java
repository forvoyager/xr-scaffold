package com.xr.recommend.common.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.Version;
import com.baomidou.mybatisplus.annotation.TableId;
import java.time.LocalDateTime;
import java.io.Serializable;

/**
 * <p>
 * 用户数据
 * </p>
 *
 * @author forvoyager@outlook.com
 * @since 2023-01-02
 */
@TableName("res_user")
public class UserEntity implements Serializable {

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
     * 年龄
     */
    private Integer age;

    /**
     * 性别 0女 1男 2未知
     */
    private Integer gender;

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

    public UserEntity setId(Long id) {
        this.id = id;
        return this;
    }
    public String getDatasourceId() {
        return datasourceId;
    }

    public UserEntity setDatasourceId(String datasourceId) {
        this.datasourceId = datasourceId;
        return this;
    }
    public String getUserId() {
        return userId;
    }

    public UserEntity setUserId(String userId) {
        this.userId = userId;
        return this;
    }
    public Integer getAge() {
        return age;
    }

    public UserEntity setAge(Integer age) {
        this.age = age;
        return this;
    }
    public Integer getGender() {
        return gender;
    }

    public UserEntity setGender(Integer gender) {
        this.gender = gender;
        return this;
    }
    public String getLocation() {
        return location;
    }

    public UserEntity setLocation(String location) {
        this.location = location;
        return this;
    }
    public String getTags() {
        return tags;
    }

    public UserEntity setTags(String tags) {
        this.tags = tags;
        return this;
    }
    public String getExtend() {
        return extend;
    }

    public UserEntity setExtend(String extend) {
        this.extend = extend;
        return this;
    }
    public String getPlatformId() {
        return platformId;
    }

    public UserEntity setPlatformId(String platformId) {
        this.platformId = platformId;
        return this;
    }
    public LocalDateTime getCreateTime() {
        return createTime;
    }

    public UserEntity setCreateTime(LocalDateTime createTime) {
        this.createTime = createTime;
        return this;
    }
    public LocalDateTime getUpdateTime() {
        return updateTime;
    }

    public UserEntity setUpdateTime(LocalDateTime updateTime) {
        this.updateTime = updateTime;
        return this;
    }

    public static final String ID = "id";

    public static final String DATASOURCE_ID = "datasource_id";

    public static final String USER_ID = "user_id";

    public static final String AGE = "age";

    public static final String GENDER = "gender";

    public static final String LOCATION = "location";

    public static final String TAGS = "tags";

    public static final String EXTEND = "extend";

    public static final String PLATFORM_ID = "platform_id";

    public static final String CREATE_TIME = "create_time";

    public static final String UPDATE_TIME = "update_time";

    @Override
    public String toString() {
        return "UserEntity{" +
            "id=" + id +
            ", datasourceId=" + datasourceId +
            ", userId=" + userId +
            ", age=" + age +
            ", gender=" + gender +
            ", location=" + location +
            ", tags=" + tags +
            ", extend=" + extend +
            ", platformId=" + platformId +
            ", createTime=" + createTime +
            ", updateTime=" + updateTime +
        "}";
    }
}
