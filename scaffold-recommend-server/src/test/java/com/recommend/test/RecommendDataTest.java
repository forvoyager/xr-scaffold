package com.recommend.test;

import com.xr.base.core.util.StringUtils;
import com.xr.recommend.RecommendServerApplication;
import com.xr.recommend.common.entity.ActionEntity;
import com.xr.recommend.common.entity.ItemEntity;
import com.xr.recommend.common.entity.UserEntity;
import com.xr.recommend.common.enums.ActionType;
import com.xr.recommend.common.service.IActionService;
import com.xr.recommend.common.service.IItemService;
import com.xr.recommend.common.service.IUserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * @author: forvoyager@outlook.com
 * @time: 2023-01-02 16:19:00
 * @description:
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = RecommendServerApplication.class)
public class RecommendDataTest {
  public static final DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
  public static final LocalDateTime date = LocalDateTime.parse("2023-01-01 00:00:00", dtf);

  @Autowired
  private IActionService actionService;

  @Autowired
  private IUserService userService;

  @Autowired
  private IItemService itemService;

  /**
   * 生成 用户
   * @throws Exception
   */
  @Test
  public void initUser() throws Exception{
    UserEntity userEntity = null;
    List<UserEntity> userEntities = new ArrayList<>();
    for(int i=0;i<10;i++){
      userEntity = new UserEntity();
      userEntity.setDatasourceId(CommonConstant.datasource_id);
      userEntity.setUserId(StringUtils.randomString(32));
      userEntity.setAge(0);
      userEntity.setGender(0);
      userEntity.setLocation("");
      userEntity.setExtend("");
      userEntity.setPlatformId(CommonConstant.platform_id);
      userEntity.setCreateTime(date);
      userEntity.setUpdateTime(date);
      userEntities.add(userEntity);
    }
    userService.saveBatch(userEntities);
  }

  /**
   * 生成 物品
   * @throws Exception
   */
  @Test
  public void initItem() throws Exception{
    Random random = new Random(System.currentTimeMillis());
    ItemEntity itemEntity = null;
    List<ItemEntity> itemEntities = new ArrayList<>();
    for(int i=0;i<20;i++){
      itemEntity = new ItemEntity();
      itemEntity.setDatasourceId(CommonConstant.datasource_id);
      itemEntity.setItemId(StringUtils.randomString(32));
      itemEntity.setType(0);
      itemEntity.setCategory("水果");
      itemEntity.setStatus(0);
      itemEntity.setTitle("水果"+i);
      itemEntity.setAuthor("门店A");
      itemEntity.setPicUrls("https://images.gogbuy.com/"+i+".jpg");
      itemEntity.setPrice(BigDecimal.ZERO);
      itemEntity.setPrice(BigDecimal.valueOf(random.nextDouble()*100));
      itemEntity.setPublishTime(date.toEpochSecond(ZoneOffset.of("+8")));
      itemEntity.setWeight(1);
      itemEntity.setContent("");
      itemEntity.setLocation("");
      itemEntity.setTags("[\"酸酸甜甜\",\"应季水果\"]");
      itemEntity.setExtend("");
      itemEntity.setPlatformId(CommonConstant.platform_id);
      itemEntity.setCreateTime(date);
      itemEntity.setUpdateTime(date);
      itemEntities.add(itemEntity);
    }
    itemService.saveBatch(itemEntities);
  }

  /**
   * 随机生成动作事件
   * @throws Exception
   */
  @Test
  public void initAction() throws Exception{

    List<UserEntity> userList = userService.list();
    List<ItemEntity> itemList = itemService.list();

    List<ActionEntity> actionEntities = new ArrayList<>();
    String userId = null;
    String itemId = null;
    Random random = new Random(System.currentTimeMillis());
    double randomNum = 0.0;
    for(int i = 0;i<100;i++){
      userId = userList.get(random.nextInt(userList.size())).getUserId();
      itemId = itemList.get(random.nextInt(itemList.size())).getItemId();

      // 点击
      actionEntities.add(make(userId, itemId, ActionType.click, date));

      randomNum = random.nextDouble();

      // 收藏
      if(randomNum < 0.8){
        actionEntities.add(make(userId, itemId, ActionType.collect, date));
      }

      // 加购物车
      if(randomNum < 0.6){
        actionEntities.add(make(userId, itemId, ActionType.add_cart, date));
      }

      // 下单
      if(randomNum < 0.4){
        actionEntities.add(make(userId, itemId, ActionType.consume, date));
      }
    }
    actionService.saveBatch(actionEntities);
  }

  public ActionEntity make(String userId, String itemId, ActionType action, LocalDateTime date){
    ActionEntity actionEntity = new ActionEntity();
    actionEntity.setDatasourceId(CommonConstant.datasource_id);
    actionEntity.setUserId(userId);
    actionEntity.setItemId(itemId);
    actionEntity.setActionCode(action.getCode());
    actionEntity.setActionScore(action.getScore());
    actionEntity.setActionTime(date.toEpochSecond(ZoneOffset.of("+8")));
    actionEntity.setTraceId("");
    actionEntity.setExtend("");
    actionEntity.setPlatformId(CommonConstant.platform_id);
    actionEntity.setCreateTime(date);
    actionEntity.setUpdateTime(date);
    return actionEntity;
  }
}
