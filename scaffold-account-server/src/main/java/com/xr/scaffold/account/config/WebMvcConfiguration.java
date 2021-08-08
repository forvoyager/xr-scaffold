package com.xr.scaffold.account.config;

import com.xr.scaffold.account.annotation.support.LoginUserHandlerMethodArgumentResolver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.List;

/**
 * @Author: forvoyager@outlook.com
 * @Time: 2021-08-08 16:44:00
 * @Description: web mvc config
 */
@Configuration
public class WebMvcConfiguration implements WebMvcConfigurer {

  @Autowired
  private LoginUserHandlerMethodArgumentResolver loginUserHandlerMethodArgumentResolver;

  @Override
  public void addArgumentResolvers(List<HandlerMethodArgumentResolver> resolvers) {
    resolvers.add(loginUserHandlerMethodArgumentResolver);
  }
}
