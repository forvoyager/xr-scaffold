package com.xr.recommend.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xr.recommend.common.entity.SceneEntity;
import com.xr.recommend.common.service.ISceneService;
import com.xr.recommend.mapper.SceneMapper;
import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 推荐场景 服务实现类
 * </p>
 *
 * @author forvoyager@outlook.com
 * @since 2022-04-02
 */
@Service
@DubboService
public class SceneServiceImpl extends ServiceImpl<SceneMapper, SceneEntity> implements ISceneService {

}
