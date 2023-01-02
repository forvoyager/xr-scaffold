package com.xr.recommend.api;

import org.apache.dubbo.config.spring.context.annotation.EnableDubbo;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author: forvoyager@outlook.com
 * @time: 2021-11-30 11:02:00
 * @description: 推荐服务API
 */
@EnableDubbo
@SpringBootApplication
public class RecommendApiApplication {
  public static void main(String[] args) {
    System.setProperty("dubbo.application.logger", "slf4j");
    SpringApplication.run(RecommendApiApplication.class, args);
  }
}
