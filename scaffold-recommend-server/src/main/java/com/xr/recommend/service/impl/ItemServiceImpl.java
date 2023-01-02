package com.xr.recommend.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xr.recommend.common.entity.ItemEntity;
import com.xr.recommend.common.service.IItemService;
import com.xr.recommend.mapper.ItemMapper;
import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 物品数据 服务实现类
 * </p>
 *
 * @author forvoyager@outlook.com
 * @since 2022-04-02
 */
@Service
@DubboService
public class ItemServiceImpl extends ServiceImpl<ItemMapper, ItemEntity> implements IItemService {

}
