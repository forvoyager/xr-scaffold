package com.xr.scaffold.account.config;

import com.auth0.jwt.exceptions.TokenExpiredException;
import com.xr.base.core.dto.ResultDto;
import com.xr.base.core.exception.BaseException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

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
    return ResultDto.failure(e.getMessage());
  }

  /**
   * 处理登录过期问题，返回401
   * @param tee
   * @return
   */
  @ExceptionHandler(value = TokenExpiredException.class)
  @ResponseStatus(value = HttpStatus.UNAUTHORIZED)
  @ResponseBody
  public ResultDto tokenExpiredHandler(TokenExpiredException tee) {
    logger.error("token过期：", tee);
    return ResultDto.failure("token过期，请重新登录。");
  }
}
