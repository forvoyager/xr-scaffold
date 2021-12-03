package com.xr.recommend.service.impl;

import com.xr.recommend.common.model.UserModel;
import com.xr.recommend.common.service.IUserService;
import com.xr.recommend.mapper.UserMapper;
import com.xr.base.jdbc.service.impl.BaseServiceImpl;
import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.stereotype.Service;

/**
 * <b>author</b>: forvoyager@outlook.com
 * <b>time</b>: 2021-08-10 13:19:29 <br>
 * <b>description</b>: 用户数据 服务实现 <br>
 */
@DubboService
@Service("userService")
public class UserServiceImpl extends BaseServiceImpl<UserMapper, UserModel> implements IUserService {
  @Override
  protected String getPrimaryKeyName() {
    return "id";
  }
}
