package com.xr.recommend.common.service;

import com.xr.base.core.service.IBaseService;
import com.xr.recommend.common.model.ActionModel;

/**
 * <b>author</b>: forvoyager@outlook.com
 * <b>time</b>: 2021-08-10 13:19:29 <br>
 * <b>description</b>: 行为数据 服务定义 <br>
 */
public interface IActionService extends IBaseService<ActionModel> {

  /**
   * 上传行为数据
   * @param entity 实体对象
   * @return 主键id
   */
  void upload(ActionModel entity) throws Exception;

}
