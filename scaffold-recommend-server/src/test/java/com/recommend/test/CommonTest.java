package com.recommend.test;

import com.fasterxml.jackson.databind.JsonNode;
import com.xr.base.core.util.JsonUtils;
import com.xr.base.core.util.StringUtils;
import com.xr.recommend.ScaffoldRecommendApplication;
import com.xr.recommend.common.ActionType;
import com.xr.recommend.common.model.ActionModel;
import com.xr.recommend.common.model.ItemModel;
import com.xr.recommend.common.model.UserModel;
import com.xr.recommend.common.service.IActionService;
import com.xr.recommend.common.service.IItemService;
import com.xr.recommend.common.service.IUserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author: forvoyager@outlook.com
 * @time: 2021-08-10 16:19:00
 * @description:
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = ScaffoldRecommendApplication.class)
public class CommonTest {

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
    List<UserModel> userList = new ArrayList<>();
    UserModel userModel = null;
    Date now = new Date();
    int size = 0;
    while(StringUtils.isNotEmpty(line)) {
      jsonNode = JsonUtils.toObject(line);
      tags = jsonNode.findPath("tags").toString();
      userId = jsonNode.findPath("userId").asText("");
      if(StringUtils.isEmpty(tags) || StringUtils.isEmpty(userId)){
        continue;
      }

      userModel = new UserModel();
      userModel.setDatasource_id(100L);
      userModel.setUser_id(userId);
      userModel.setAge(0);
      userModel.setGender(0);
      userModel.setTags(tags);
      userModel.setExtend("");
      userModel.setCreate_time(now);
      userModel.setUpdate_time(now);
      userList.add(userModel);
      size++;

      if(size > 200){
        userService.insertBatch(userList);
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
    List<ItemModel> list = new ArrayList<>();
    ItemModel model = null;
    Date now = new Date();
    int size = 0;
    while(StringUtils.isNotEmpty(line)) {
      jsonNode = JsonUtils.toObject(line);
      tags = jsonNode.findPath("tags").toString();

      model = new ItemModel();
      model.setDatasource_id(100L);
      model.setItem_id(jsonNode.findPath("itemId").asText(""));
      model.setType(0);
      model.setCategory(jsonNode.findPath("category").asText(""));
      model.setStatus(0);
      model.setPic_url("https://images.gogbuy.com"+jsonNode.findPath("item_picture").asText(""));
      model.setPrice(0.0);
      extend = jsonNode.findPath("extend");
      if(extend != null){
        JsonNode price = extend.get("price");
        if(price != null){
          model.setPrice(price.asDouble(0.0));
        }
      }
      model.setTitle(jsonNode.findPath("title").asText(""));
      model.setContent("");
      model.setAuthor(jsonNode.findPath("author").asText(""));
      model.setTags(tags);
      model.setExtend("");
      model.setCreate_time(now);
      model.setUpdate_time(now);
      list.add(model);
      size++;

      if(size > 200){
        itemService.insertBatch(list);
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
    String tags = null;
    List<ActionModel> list = new ArrayList<>();
    ActionModel model = null;
    Date now = new Date();
    int size = 0;
    while(StringUtils.isNotEmpty(line)) {
      jsonNode = JsonUtils.toObject(line);
      tags = jsonNode.findPath("tags").toString();

      model = new ActionModel();
      model.setDatasource_id(100L);
      model.setUser_id(jsonNode.findPath("userId").asText(""));
      model.setItem_type(0);
      model.setItem_id(jsonNode.findPath("itemId").asText(""));
      ActionType t = getActionType(jsonNode.findPath("actionType").asText(""));
      model.setAction_code(t.getCode());
      model.setAction_time(jsonNode.findPath("actionTime").asInt(0));
      model.setAction_score(t.getScore());
      model.setTrace_id("");
      model.setExtend("");
      model.setCreate_time(now);
      model.setUpdate_time(now);
      list.add(model);
      size++;

      if(size > 200){
        actionService.insertBatch(list);
        size = 0;
        list.clear();
      }

      line = br.readLine();
    }
  }

  private ActionType getActionType(String type){
    if(StringUtils.isNotEmpty(type)){
      return ActionType.parseActionType(type);
    }

    return ActionType.click;
  }
}
