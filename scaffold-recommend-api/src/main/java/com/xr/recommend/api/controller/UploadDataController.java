package com.xr.recommend.api.controller;

import com.xr.base.core.dto.ResultDto;
import com.xr.recommend.api.common.dto.ItemDto;
import com.xr.recommend.common.service.IActionService;
import com.xr.recommend.common.service.IItemService;
import io.swagger.annotations.Api;
import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
* <b>author</b>: forvoyager@outlook.com
* <b>time</b>: 2021-12-09 17:09:28 <br>
* <b>description</b>: 物品数据 HTTP服务 <br>
*/
@Api(tags = "物品数据相关操作")
@RestController
@RequestMapping("/upload")
public class UploadDataController {

  @DubboReference
  private IItemService itemService;

  @DubboReference
  private IActionService actionService;

  /**
   * <p>
   * 上传物品数据
   * </p>
   *
   * @param itemDto 物品数据
   */
  @PostMapping("/item")
  public ResultDto uploadItem(@RequestBody ItemDto itemDto) throws Exception {
    return ResultDto.success();
  }

  /**
   * <p>
   * 上传行为数据
   * </p>
   *
   * @param entity 实体对象
   */
  @PostMapping("/action")
  public ResultDto uploadAction(@RequestBody ItemDto itemDto) throws Exception {
//    if(itemDto.getItemType() != null){
//      itemDto.setType(itemDto.getItemType().getType());
//    }
//
//    String itemId = itemDto.getItem_id();
//    if(StringUtils.isEmpty(itemId)){
//      throw new BaseException(ResultCodeEnum.ILLEGAL_ARGUMENT, "物品ID不能为空");
//    }
//
//    actionService.upload(itemDto);

    return ResultDto.success();
  }
}
