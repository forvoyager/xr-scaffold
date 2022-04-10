package com.xr.recommend.common.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.xr.recommend.common.entity.ActionEntity;

/**
 * <p>
 * 行为数据 服务类
 * </p>
 *
 * @author forvoyager@outlook.com
 * @since 2022-04-01
 */
public interface IActionService extends IService<ActionEntity> {

  /**
   * 上传行为数据
   * @param entity 实体对象
   * @return 主键id
   */
  void upload(ActionEntity entity) throws Exception;

}
