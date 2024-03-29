package com.xr.recommend.config;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;

/**
 * <b>@author</b>: forvoyager@outlook.com
 * <b>@time</b>: 2020-07-23 19:56:00
 * <b>@description</b>:
 */
@Configuration
@MapperScan({
        "com.xr.recommend.mapper"
})
public class AutoConfiguration {

}
