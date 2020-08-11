package com.xr.scaffold.account.config;

import com.alibaba.csp.sentinel.adapter.servlet.callback.UrlBlockHandler;
import com.alibaba.csp.sentinel.adapter.servlet.config.WebServletConfig;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.annotation.PostConstruct;

/**
 * <b>@author</b>: yang.changyan@foundbyte.com
 * <b>@time</b>: 2020-08-11 14:11:00
 * <b>@description</b>:
 */
@Configuration
public class SentinelConfig {

  @Bean
  public UrlBlockHandler urlBlockHandler(){
    return new SentinelUrlBlockHandler();
  }

  /**
   *
   */
  @PostConstruct
  public void init(){
    WebServletConfig.setBlockPage("http://www.baidu.com");
  }

}
