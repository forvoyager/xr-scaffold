package com.xr.scaffold.account.service.impl;

import com.xr.scaffold.account.common.model.UserAccountModel;
import com.xr.scaffold.account.common.service.IUserAccountService;
import com.xr.scaffold.account.mapper.UserAccountMapper;
import com.xr.base.jdbc.service.impl.BaseServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <b>author</b>: yang.changyan@foundbyte.com
 * <b>time</b>: 2020-08-07 10:34:52 <br>
 * <b>description</b>: 用户账户表 服务实现 <br>
 */
@Service("userAccountService")
public class UserAccountServiceImpl extends BaseServiceImpl<UserAccountMapper, UserAccountModel> implements IUserAccountService {
  @Override
  protected String getPrimaryKeyName() {
    return "user_id";
  }
}
