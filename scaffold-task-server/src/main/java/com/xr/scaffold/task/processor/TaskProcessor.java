package com.xr.scaffold.task.processor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.TaskScheduler;
import org.springframework.stereotype.Component;

import java.util.concurrent.ConcurrentHashMap;

/**
 * @Author: forvoyager@outlook.com
 * @Time: 2020-08-02 21:45:00
 * @Description:
 */
@Component
public class TaskProcessor {
//  private final Map<Runnable, ScheduledTask> scheduledTasks = new ConcurrentHashMap<>(16);

  @Autowired
  private TaskScheduler taskScheduler;

}
