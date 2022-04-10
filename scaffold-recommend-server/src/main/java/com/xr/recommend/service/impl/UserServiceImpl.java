package com.xr.recommend.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xr.recommend.common.entity.UserEntity;
import com.xr.recommend.common.service.IUserService;
import com.xr.recommend.mapper.UserMapper;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 用户数据 服务实现类
 * </p>
 *
 * @author forvoyager@outlook.com
 * @since 2022-04-02
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, UserEntity> implements IUserService {

}
