package com.xr.scaffold.account.annotation.support;

import com.xr.scaffold.account.annotation.LoginUser;
import com.xr.scaffold.account.service.impl.UserTokenManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.MethodParameter;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

/**
 * <b>@author</b>: forvoyager@outlook.com
 * <b>@time</b>: 2021-08-08 16:10:00
 * <b>@description</b>: 登录用户注解实现
 */
@Component("loginUserHandlerMethodArgumentResolver")
public class LoginUserHandlerMethodArgumentResolver implements HandlerMethodArgumentResolver {

  @Value("${jwt.header.key}")
  private String headerKey;

  @Autowired
  private UserTokenManager userTokenManager;

  @Override
  public boolean supportsParameter(MethodParameter methodParameter) {
    return methodParameter.getParameterType().isAssignableFrom(Long.class) && methodParameter.hasParameterAnnotation(LoginUser.class);
  }

  @Override
  public Object resolveArgument(MethodParameter methodParameter, ModelAndViewContainer modelAndViewContainer, NativeWebRequest nativeWebRequest, WebDataBinderFactory webDataBinderFactory) throws Exception {
    // 从request header获取token信息，计算用户信息
    return this.userTokenManager.getUserId(nativeWebRequest.getHeader(this.headerKey));
  }
}
