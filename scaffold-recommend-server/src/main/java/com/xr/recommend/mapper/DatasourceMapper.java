package com.xr.recommend.mapper;

import com.xr.recommend.common.model.DatasourceModel;
import com.xr.base.jdbc.mapper.IBaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * <b>author</b>: forvoyager@outlook.com
 * <b>time</b>: 2021-08-10 13:19:29 <br>
 * <b>description</b>: 推荐数据源 mapper操作 <br>
 */
@Mapper
public interface DatasourceMapper extends IBaseMapper<DatasourceModel> {
}
