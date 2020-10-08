package com.xr.scaffold.account.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.xr.scaffold.account.common.model.UserAccountModel;
import org.apache.ibatis.annotations.Mapper;

/**
 * <b>author</b>: yang.changyan@foundbyte.com
 * <b>time</b>: 2020-08-07 10:34:52 <br>
 * <b>description</b>: 用户账户表 mapper操作 <br>
 */
@Mapper
public interface UserAccountMapper extends BaseMapper<UserAccountModel> {
}
