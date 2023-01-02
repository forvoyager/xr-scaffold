package com.recommend.test;

import com.xr.recommend.RecommendServerApplication;
import com.xr.recommend.common.Context;
import com.xr.recommend.common.dto.ItemDto;
import com.xr.recommend.recommender.HotRecommender;
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
    context.setPlatformId(CommonConstant.platform_id);
    context.setDatasourceId(CommonConstant.datasource_id);
    context.setUserId("FvvLpDuX3tP1pcHXu3vDSoCbOzVQxBaE");
    context.setRecSize(10);
    List<ItemDto> itemList = hotRecommender.recommend(context);
    itemList.forEach(itemDto -> {
      System.out.println(itemDto.getItemId()+"----"+ itemDto.getTitle());
    });
  }

}
