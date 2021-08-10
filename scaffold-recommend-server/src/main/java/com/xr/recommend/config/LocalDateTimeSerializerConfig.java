package com.xr.recommend.config;

import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.jackson.Jackson2ObjectMapperBuilderCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * <b>@author</b>: forvoyager@outlook.com
 * <b>@time</b>: 2021-08-08 16:10:00
 * <b>@description</b>: LocalDateTime格式化配置
 */
@Configuration
public class LocalDateTimeSerializerConfig {

  @Value("${spring.jackson.date-format:yyyy-MM-dd HH:mm:ss}")
  private String defaultDatePattern;

  @Bean
  public LocalDateTimeSerializer localDateTimeDeserializer() {
    return new LocalDateTimeSerializer(DateTimeFormatter.ofPattern(defaultDatePattern));
  }

  @Bean
  public Jackson2ObjectMapperBuilderCustomizer jackson2ObjectMapperBuilderCustomizer(LocalDateTimeSerializer localDateTimeSerializer) {
    return builder -> builder.serializerByType(LocalDateTime.class, localDateTimeSerializer);
  }
}
