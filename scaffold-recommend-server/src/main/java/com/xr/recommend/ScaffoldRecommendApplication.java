package com.xr.recommend;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * <b>@author</b>: forvoyager@outlook.com
 * <b>@time</b>: 2021-08-10 13:27:00
 * <b>@description</b>:
 */
@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class})
@EnableDiscoveryClient
public class ScaffoldRecommendApplication {
  public static void main(String[] args) {
    SpringApplication.run(ScaffoldRecommendApplication.class, args);
  }
}
