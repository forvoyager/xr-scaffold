package com.xr.recommend.config;

import com.baomidou.mybatisplus.extension.plugins.OptimisticLockerInterceptor;
import com.baomidou.mybatisplus.extension.plugins.PaginationInterceptor;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * @author: forvoyager@outlook.com
 * @time: 2022-04-01 18:58:00
 * @description: mybatis配置
 */
@Configuration
@MapperScan(value = {
        "com.xr.recommend.mapper"
})
@EnableTransactionManagement
public class MybatisPlusConfiguration {
  @Bean
  public PaginationInterceptor paginationInterceptor() {
    return new PaginationInterceptor();
  }

  @Bean
  public OptimisticLockerInterceptor optimisticLockerInterceptor() {
    return new OptimisticLockerInterceptor();
  }

}
