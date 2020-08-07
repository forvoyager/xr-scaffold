package com.xr.scaffold.config;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @Author: yang.changyan@foundbyte.com
 * @Time: 2020-07-25 21:03:00
 * @Description:
 */
@Component
public class WebConfig implements WebMvcConfigurer {
  @Override
  public void addCorsMappings(CorsRegistry registry) {
    // 允许的请求路径
    registry.addMapping("/**")
            // 允许的请求方式
            .allowedMethods("POST", "GET", "PUT", "OPTIONS", "DELETE")
            // 允许的请求源
            .allowedOrigins("*")
            // 允许的请求头
            .allowedHeaders("*")
            // 配置客户端缓存预检请求的响应的时间（以秒为单位）。默认设置为1800秒（30分钟）。
            .maxAge(3600)
            // 浏览器是否应该发送凭据
            .allowCredentials(true);
  }

  @Override
  public void addResourceHandlers(ResourceHandlerRegistry registry) {
    registry.addResourceHandler("swagger-ui.html").addResourceLocations("classpath:/META-INF/resources/");
    registry.addResourceHandler("/webjars/**").addResourceLocations("classpath:/META-INF/resources/webjars/");
  }
}
