package com.xr.recommend.service.impl;

import com.xr.recommend.common.model.SceneModel;
import com.xr.recommend.common.service.ISceneService;
import com.xr.recommend.mapper.SceneMapper;
import com.xr.base.jdbc.service.impl.BaseServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <b>author</b>: forvoyager@outlook.com
 * <b>time</b>: 2021-08-10 13:19:29 <br>
 * <b>description</b>: 推荐场景 服务实现 <br>
 */
@Service("sceneService")
public class SceneServiceImpl extends BaseServiceImpl<SceneMapper, SceneModel> implements ISceneService {
  @Override
  protected String getPrimaryKeyName() {
    return "scene_id";
  }
}
