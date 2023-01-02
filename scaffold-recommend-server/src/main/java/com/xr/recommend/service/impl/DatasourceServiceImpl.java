package com.xr.recommend.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xr.recommend.common.entity.DatasourceEntity;
import com.xr.recommend.common.service.IDatasourceService;
import com.xr.recommend.mapper.DatasourceMapper;
import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 推荐数据源 服务实现类
 * </p>
 *
 * @author forvoyager@outlook.com
 * @since 2022-04-02
 */
@Service
@DubboService
public class DatasourceServiceImpl extends ServiceImpl<DatasourceMapper, DatasourceEntity> implements IDatasourceService {

}
