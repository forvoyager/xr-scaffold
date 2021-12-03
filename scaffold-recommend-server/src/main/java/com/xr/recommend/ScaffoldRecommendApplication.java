package com.xr.recommend;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

/**
 * <b>@author</b>: forvoyager@outlook.com
 * <b>@time</b>: 2021-08-10 13:27:00
 * <b>@description</b>: 推荐服务
 */
@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class})
public class ScaffoldRecommendApplication {
  public static void main(String[] args) {
    SpringApplication.run(ScaffoldRecommendApplication.class, args);
  }
}
