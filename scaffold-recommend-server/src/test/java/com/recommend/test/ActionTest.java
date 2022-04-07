package com.recommend.test;

import com.xr.base.core.util.DateUtil;
import com.xr.recommend.RecommendServerApplication;
import com.xr.recommend.common.enums.ActionType;
import com.xr.recommend.common.service.IActionService;
import com.xr.recommend.common.statistics.ActionStatistic;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDateTime;
import java.util.List;

/**
 * @author: forvoyager@outlook.com
 * @time: 2022-04-06 18:02:00
 * @description:
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = RecommendServerApplication.class)
public class ActionTest {

  @Autowired
  private IActionService actionService;

  @Test
  public void testStatistic(){
    List<ActionStatistic> statistics = actionService.actionStatistic(DateUtil.toSecond(LocalDateTime.now().minusDays(1000)), DateUtil.toSecond(LocalDateTime.now()), ActionType.click, 10);
    System.out.println(statistics);
  }

}
