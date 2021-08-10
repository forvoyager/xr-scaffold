package com.xr.recommend.service.impl;

import com.xr.recommend.common.model.ItemModel;
import com.xr.recommend.common.service.IItemService;
import com.xr.recommend.mapper.ItemMapper;
import com.xr.base.jdbc.service.impl.BaseServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <b>author</b>: forvoyager@outlook.com
 * <b>time</b>: 2021-08-10 13:19:29 <br>
 * <b>description</b>: 物品数据 服务实现 <br>
 */
@Service("itemService")
public class ItemServiceImpl extends BaseServiceImpl<ItemMapper, ItemModel> implements IItemService {
  @Override
  protected String getPrimaryKeyName() {
    return "id";
  }
}
