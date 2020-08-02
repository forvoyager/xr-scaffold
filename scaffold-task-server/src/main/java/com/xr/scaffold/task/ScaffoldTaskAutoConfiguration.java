package com.xr.scaffold.task;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.TaskScheduler;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;

/**
 * <b>@author</b>: forvoyager@outlook.com
 * <b>@time</b>: 2020-07-23 19:56:00
 * <b>@description</b>:
 */
@Configuration
public class ScaffoldTaskAutoConfiguration {
  @Bean
  public TaskScheduler taskScheduler() {
    ThreadPoolTaskScheduler taskScheduler = new ThreadPoolTaskScheduler();
    taskScheduler.setPoolSize(10);
    taskScheduler.setRemoveOnCancelPolicy(true);
    taskScheduler.setThreadNamePrefix("TaskSchedulerThreadPool-");
    return taskScheduler;
  }
}
