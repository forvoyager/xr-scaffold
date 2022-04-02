package com.xr.recommend.api.controller;

import com.xr.base.core.dto.ResultDto;
import com.xr.base.core.enums.ResultCodeEnum;
import com.xr.base.core.exception.BaseException;
import com.xr.base.core.util.StringUtils;
import com.xr.recommend.api.common.dto.ActionDto;
import com.xr.recommend.api.common.dto.ItemDto;
import com.xr.recommend.common.service.IActionService;
import com.xr.recommend.common.service.IItemService;
import com.xr.recommend.common.service.IUserService;
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
  private IUserService userService;

  @DubboReference
  private IItemService itemService;

  @DubboReference
  private IActionService actionService;

  /**
   * <p>
   * 上传用户数据
   * </p>
   *
   * @param userDto 用户数据
   */
  @PostMapping("/item")
  public ResultDto uploadItem() throws Exception {
    return ResultDto.success();
  }

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
   * @param actionDto 行为数据
   */
  @PostMapping("/action")
  public ResultDto uploadAction(@RequestBody ActionDto actionDto) throws Exception {
    return ResultDto.success();
  }
}
