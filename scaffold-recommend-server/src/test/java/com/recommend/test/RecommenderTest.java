package com.recommend.test;

import com.xr.recommend.RecommendServerApplication;
import com.xr.recommend.common.Context;
import com.xr.recommend.common.entity.ItemEntity;
import com.xr.recommend.service.impl.HotRecommender;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

/**
 * @author: forvoyager@outlook.com
 * @time: 2022-04-06 18:02:00
 * @description:
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = RecommendServerApplication.class)
public class RecommenderTest {

  @Autowired
  private HotRecommender hotRecommender;

  @Test
  public void testStatistic() throws Exception{
    Context context = new Context();
    context.setUserId("11111");
    List<ItemEntity> itemList = hotRecommender.recommend(context);
    System.out.println(itemList);
  }

}
