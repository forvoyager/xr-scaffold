package com.recommend.test;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.fasterxml.jackson.databind.JsonNode;
import com.xr.base.core.util.CollectionUtils;
import com.xr.base.core.util.JsonUtils;
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

import java.io.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.*;

/**
 * @author: forvoyager@outlook.com
 * @time: 2021-08-10 16:19:00
 * @description:
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = RecommendServerApplication.class)
public class DataImportTest {

  @Autowired
  private IUserService userService;

  @Autowired
  private IItemService itemService;

  @Autowired
  private IActionService actionService;

  @Test
  public void importUser() throws Exception{

    InputStream is = getClass().getClassLoader().getResourceAsStream("data/user-tag.json");
    BufferedReader br = new BufferedReader(new InputStreamReader(is));
    String line = br.readLine();
    JsonNode jsonNode = null;
    String tags = null;
    String userId = null;
    List<UserEntity> userList = new ArrayList<>();
    UserEntity userEntity = null;
    LocalDateTime now = LocalDateTime.now();
    int size = 0;
    while(StringUtils.isNotEmpty(line)) {
      jsonNode = JsonUtils.toObject(line);
      tags = jsonNode.findPath("tags").toString();
      userId = jsonNode.findPath("userId").asText("");
      if(StringUtils.isEmpty(tags) || StringUtils.isEmpty(userId)){
        continue;
      }

      userEntity = new UserEntity();
      userEntity.setDatasourceId(CommonConstant.datasource_id);
      userEntity.setUserId(userId);
      userEntity.setAge(0);
      userEntity.setGender(0);
      userEntity.setLocation("");
      userEntity.setTags(tags);
      userEntity.setExtend("");
      userEntity.setPlatformId(CommonConstant.platform_id);
      userEntity.setCreateTime(now);
      userEntity.setUpdateTime(now);
      userList.add(userEntity);
      size++;

      if(size > 200){
        userService.saveBatch(userList);
        size = 0;
        userList.clear();
      }

      line = br.readLine();
    }
  }

  @Test
  public void importItem() throws Exception{

    InputStream is = getClass().getClassLoader().getResourceAsStream("data/item-tag.json");
    BufferedReader br = new BufferedReader(new InputStreamReader(is));
    String line = br.readLine();
    JsonNode jsonNode = null;
    JsonNode extend = null;
    String tags = null;
    List<ItemEntity> list = new ArrayList<>();
    ItemEntity itemEntity = null;
    LocalDateTime now = LocalDateTime.now();
    int size = 0;
    while(StringUtils.isNotEmpty(line)) {
      jsonNode = JsonUtils.toObject(line);
      tags = jsonNode.findPath("tags").toString();

      itemEntity = new ItemEntity();
      itemEntity.setDatasourceId(CommonConstant.datasource_id);
      itemEntity.setItemId(jsonNode.findPath("itemId").asText(""));
      itemEntity.setType(0);
      itemEntity.setCategory(jsonNode.findPath("category").asText(""));
      itemEntity.setStatus(0);
      itemEntity.setTitle(jsonNode.findPath("title").asText(""));
      itemEntity.setAuthor(jsonNode.findPath("author").asText(""));
      itemEntity.setPicUrls("https://images.gogbuy.com"+jsonNode.findPath("item_picture").asText(""));
      itemEntity.setPrice(BigDecimal.ZERO);
      extend = jsonNode.findPath("extend");
      if(extend != null){
        JsonNode price = extend.get("price");
        if(price != null){
          itemEntity.setPrice(BigDecimal.valueOf(price.asDouble(0.0)));
        }
      }
      itemEntity.setPublishTime(now.toEpochSecond(ZoneOffset.of("+8")));
      itemEntity.setWeight(1);
      itemEntity.setContent("");
      itemEntity.setLocation("");
      itemEntity.setTags(tags);
      itemEntity.setExtend("");
      itemEntity.setPlatformId(CommonConstant.platform_id);
      itemEntity.setCreateTime(now);
      itemEntity.setUpdateTime(now);
      list.add(itemEntity);
      size++;

      if(size > 200){
        itemService.saveBatch(list);
        size = 0;
        list.clear();
      }

      line = br.readLine();
    }
  }

  @Test
  public void importAction() throws Exception{

    InputStream is = getClass().getClassLoader().getResourceAsStream("data/user-action.json");
    BufferedReader br = new BufferedReader(new InputStreamReader(is));
    String line = br.readLine();
    JsonNode jsonNode = null;
    List<ActionEntity> actionList = new ArrayList<>();
    ActionEntity actionEntity = null;
    LocalDateTime now = LocalDateTime.now();
    int size = 0;
    String userId = null;
    while(StringUtils.isNotEmpty(line)) {
      jsonNode = JsonUtils.toObject(line);
      userId = jsonNode.findPath("userId").asText("");

      if(StringUtils.isNotEmpty(userId)){
        actionEntity = new ActionEntity();
        actionEntity.setDatasourceId(CommonConstant.datasource_id);
        actionEntity.setUserId(userId);
        actionEntity.setActionCode(0);
        actionEntity.setItemId(jsonNode.findPath("itemId").asText(""));
        ActionType t = getActionType(jsonNode.findPath("actionType").asText(""));
        actionEntity.setActionCode(t.getCode());
        actionEntity.setActionTime(jsonNode.findPath("actionTime").asLong(0));
        actionEntity.setActionScore(t.getScore());
        actionEntity.setTraceId("");
        actionEntity.setExtend("");
        actionEntity.setPlatformId(CommonConstant.platform_id);
        actionEntity.setCreateTime(now);
        actionEntity.setUpdateTime(now);
        actionList.add(actionEntity);

        size++;
        if(size > 200){
          actionService.saveBatch(actionList);
          size = 0;
          actionList.clear();
        }
      }


      line = br.readLine();
    }
  }

  @Test
  public void exportRating() throws Exception{

    String linePattern = "%s,%s,%s,%s";
    BufferedWriter bw = new BufferedWriter(new FileWriter("src/main/resources/data/rating.csv", true));

    // 表头
    bw.write(String.format(linePattern, "userId", "itemId", "rating", "timestamp"));
    bw.newLine();

    int pageNum = 0;
    int pageSize = 100;
    List<ActionEntity> data = null;
    QueryWrapper<ActionEntity> queryWrapper = new QueryWrapper<>();
    queryWrapper.eq(ActionEntity.DATASOURCE_ID, CommonConstant.datasource_id);
    queryWrapper.orderByAsc(ActionEntity.ACTION_ID);
    do{
      pageNum++;
      IPage page = new Page(pageNum, pageSize);
      page = actionService.page(page, queryWrapper);
      data = page.getRecords();
      if (CollectionUtils.isEmpty(data)){ break; }

      for(ActionEntity am : data){
        bw.write(String.format(linePattern, am.getUserId(), am.getItemId(), am.getActionScore(), am.getActionTime()));
        bw.newLine();
      }
    }while (true);

    bw.flush();
    bw.close();
  }

  private ActionType getActionType(String type){
    if(StringUtils.isNotEmpty(type)){
      return ActionType.parseActionType(type);
    }

    return ActionType.click;
  }
}
