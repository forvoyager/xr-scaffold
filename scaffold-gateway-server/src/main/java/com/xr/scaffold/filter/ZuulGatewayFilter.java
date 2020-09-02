//package com.xr.scaffold.filter;
//
//import com.netflix.zuul.ZuulFilter;
//import com.netflix.zuul.context.RequestContext;
//import com.netflix.zuul.exception.ZuulException;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.cloud.netflix.zuul.filters.support.FilterConstants;
//import org.springframework.stereotype.Component;
//
//import javax.servlet.http.HttpServletRequest;
//
///**
// * <b>@author</b>: yang.changyan@foundbyte.com
// * <b>@time</b>: 2020-07-23 20:11:00
// * <b>@description</b>:
// */
//@Component
//public class ZuulGatewayFilter extends ZuulFilter {
//  private Logger logger = LoggerFactory.getLogger(this.getClass());
//
//  @Override
//  public String filterType() {
//    return FilterConstants.PRE_TYPE;
//  }
//
//  @Override
//  public int filterOrder() {
//    return FilterConstants.PRE_DECORATION_FILTER_ORDER - 1;
//  }
//
//  @Override
//  public boolean shouldFilter() {
//    return true;
//  }
//
//  @Override
//  public Object run() throws ZuulException {
//    RequestContext ctx = RequestContext.getCurrentContext();
//    HttpServletRequest request = ctx.getRequest();
//    logger.info("request url:{}", request.getRequestURI());
//    return null;
//  }
//}
