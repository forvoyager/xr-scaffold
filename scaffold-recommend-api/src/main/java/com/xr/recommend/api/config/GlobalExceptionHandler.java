package com.xr.recommend.api.config;

import com.xr.base.core.dto.ResultDto;
import com.xr.base.core.enums.ResultCodeEnum;
import com.xr.base.core.exception.BaseException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * <b>@author</b>: forvoyager@outlook.com
 * <b>@time</b>: 2020-08-08 11:09:00
 * <b>@description</b>:
 */
@ControllerAdvice
public class GlobalExceptionHandler {

  private Logger logger = LoggerFactory.getLogger(getClass());

  /**
   * 处理自定义的业务异常
   *
   * @param be
   * @return
   */
  @ExceptionHandler(value = BaseException.class)
  @ResponseBody
  public ResultDto bizExceptionHandler(BaseException be) {
    logger.error("业务异常，原因是：", be);
    return ResultDto.failure(be.getCode(), be.getMessage(), be.getExtData());
  }

//  /**
//   * 处理空指针的异常
//   * @param npe
//   * @return
//   */
//  @ExceptionHandler(value =NullPointerException.class)
//  @ResponseBody
//  public ResultDto exceptionHandler(NullPointerException npe){
//    logger.error("空指针异常:", npe);
//  }


  /**
   * 处理其他异常
   *
   * @param e
   * @return
   */
  @ExceptionHandler(value = Exception.class)
  @ResponseBody
  public ResultDto exceptionHandler(Exception e) {
    logger.error("未知异常，原因:", e);
    return ResultDto.failure(ResultCodeEnum.UNKNOW_SYSTEM_ERROR.getLabel());
  }

}
