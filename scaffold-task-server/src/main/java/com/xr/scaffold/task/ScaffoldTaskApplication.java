package com.xr.scaffold.task;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 * @Author: forvoyager@outlook.com
 * @Time: 2020-08-02 21:40:00
 * @Description:
 */
@SpringBootApplication
@EnableScheduling
public class ScaffoldTaskApplication {
  public static void main(String[] args) {
    SpringApplication.run(ScaffoldTaskApplication.class, args);
  }
}
