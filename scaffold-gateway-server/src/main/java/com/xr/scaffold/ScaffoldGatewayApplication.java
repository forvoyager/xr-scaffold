package com.xr.scaffold;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;

/**
 * <b>@author</b>: yang.changyan@foundbyte.com
 * <b>@time</b>: 2020-07-23 17:53:00
 * <b>@description</b>:
 */
@SpringBootApplication
@EnableZuulProxy
@EnableDiscoveryClient
public class ScaffoldGatewayApplication {
  public static void main(String[] args) {
    SpringApplication.run(ScaffoldGatewayApplication.class, args);
  }
}
