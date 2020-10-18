//package com.xr.scaffold.account.config;
//
//import com.alibaba.csp.sentinel.adapter.servlet.callback.UrlBlockHandler;
//import com.alibaba.csp.sentinel.slots.block.BlockException;
//import com.alibaba.csp.sentinel.slots.block.flow.FlowException;
//import com.xr.base.core.dto.ResultDto;
//import com.xr.base.core.util.JsonUtils;
//
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import java.io.IOException;
//
///**
// * <b>@author</b>: forvoyager@outlook.com
// * <b>@time</b>: 2020-08-11 14:14:00
// * <b>@description</b>:
// */
//public class SentinelUrlBlockHandler implements UrlBlockHandler {
//
//  private final String defaultBlockResult = JsonUtils.toJson(ResultDto.failure("系统繁忙，请稍后重试。"));
//
//  @Override
//  public void blocked(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, BlockException e) throws IOException {
//    if(e instanceof FlowException){
//      httpServletResponse.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
//      httpServletResponse.setContentType("application/json;charset=utf-8");
//      httpServletResponse.getWriter().write(defaultBlockResult);
//    }
//  }
//}
