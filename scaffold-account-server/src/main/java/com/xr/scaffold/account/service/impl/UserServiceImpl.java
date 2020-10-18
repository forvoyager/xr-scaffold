package com.xr.scaffold.account.service.impl;

import com.xr.scaffold.account.common.model.UserModel;
import com.xr.scaffold.account.common.service.IUserService;
import com.xr.scaffold.account.mapper.UserMapper;
import com.xr.base.jdbc.service.impl.BaseServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <b>author</b>: yang.changyan@foundbyte.com
 * <b>time</b>: 2020-08-07 10:34:52 <br>
 * <b>description</b>: 用户表 服务实现 <br>
 */
@Service("userService")
public class UserServiceImpl extends BaseServiceImpl<UserMapper, UserModel> implements IUserService {
  @Override
  protected String getPrimaryKeyName() {
    return "user_id";
  }
}
