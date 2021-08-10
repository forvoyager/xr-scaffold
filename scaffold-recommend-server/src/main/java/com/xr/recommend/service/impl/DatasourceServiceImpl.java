package com.xr.recommend.service.impl;

import com.xr.recommend.common.model.DatasourceModel;
import com.xr.recommend.common.service.IDatasourceService;
import com.xr.recommend.mapper.DatasourceMapper;
import com.xr.base.jdbc.service.impl.BaseServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <b>author</b>: forvoyager@outlook.com
 * <b>time</b>: 2021-08-10 13:19:29 <br>
 * <b>description</b>: 推荐数据源 服务实现 <br>
 */
@Service("datasourceService")
public class DatasourceServiceImpl extends BaseServiceImpl<DatasourceMapper, DatasourceModel> implements IDatasourceService {
  @Override
  protected String getPrimaryKeyName() {
    return "datasource_id";
  }
}
