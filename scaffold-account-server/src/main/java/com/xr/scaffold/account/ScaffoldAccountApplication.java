package com.xr.scaffold.account;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * <b>@author</b>: yang.changyan@foundbyte.com
 * <b>@time</b>: 2020-07-23 18:10:00
 * <b>@description</b>:
 */
@SpringBootApplication
@EnableDiscoveryClient
public class ScaffoldAccountApplication {
  public static void main(String[] args) {
    SpringApplication.run(ScaffoldAccountApplication.class, args);
  }
}
