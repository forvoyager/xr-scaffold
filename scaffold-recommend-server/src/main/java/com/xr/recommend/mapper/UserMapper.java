package com.xr.recommend.mapper;

import com.xr.recommend.common.model.UserModel;
import com.xr.base.jdbc.mapper.IBaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * <b>author</b>: forvoyager@outlook.com
 * <b>time</b>: 2021-08-10 13:19:29 <br>
 * <b>description</b>: 用户数据 mapper操作 <br>
 */
@Mapper
public interface UserMapper extends IBaseMapper<UserModel> {
}
