package com.xr.scaffold.account.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xr.scaffold.account.common.model.UserModel;
import com.xr.scaffold.account.common.service.IUserService;
import com.xr.scaffold.account.mapper.UserMapper;
import org.springframework.stereotype.Service;

/**
 * <b>author</b>: yang.changyan@foundbyte.com
 * <b>time</b>: 2020-08-07 10:34:52 <br>
 * <b>description</b>: 用户表 服务实现 <br>
 */
@Service("userService")
public class UserServiceImpl extends ServiceImpl<UserMapper, UserModel> implements IUserService {
}
