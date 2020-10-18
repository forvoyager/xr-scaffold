package com.xr.scaffold.account.mapper;

import com.xr.scaffold.account.common.model.UserAccountModel;
import com.xr.base.jdbc.mapper.IBaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * <b>author</b>: forvoyager@outlook.com
 * <b>time</b>: 2020-08-07 10:34:52 <br>
 * <b>description</b>: 用户账户表 mapper操作 <br>
 */
@Mapper
public interface UserAccountMapper extends IBaseMapper<UserAccountModel> {
}
