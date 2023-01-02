package com.onebuygz.message;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Configuration;

/**
 * <b>author</b>: ${author}
 * <b>time</b>: ${time}
 * <b>description</b>: ${comments} feign客户端自启动配置
 */
@ConditionalOnProperty(prefix = "${moduleName}.client", name = "enabled", matchIfMissing = true)
@Configuration
@EnableFeignClients
public class StarterAutoConfiguration {

  public static final String SERVER_NAME = "${moduleName}-server";

}
