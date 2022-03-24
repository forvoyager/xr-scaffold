package com.recommend.test;

import com.fasterxml.jackson.databind.JsonNode;
import com.xr.base.core.enums.Cluster;
import com.xr.base.core.page.PageData;
import com.xr.base.core.util.CollectionUtils;
import com.xr.base.core.util.JsonUtils;
import com.xr.base.core.util.StringUtils;
import com.xr.recommend.RecommendServerApplication;
import com.xr.recommend.common.enums.ActionType;
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

import java.io.*;
import java.util.*;

/**
 * @author: forvoyager@outlook.com
 * @time: 2021-08-10 16:19:00
 * @description:
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = RecommendServerApplication.class)
public class CommonTest {

  public static final String tenant_id = "10010";
  public static final Long datasource_id = 100L;

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
      userModel.setDatasource_id(datasource_id);
      userModel.setUser_id(userId);
      userModel.setAge(0);
      userModel.setGender(0);
      userModel.setLocation("");
      userModel.setTags(tags);
      userModel.setExtend("");
      userModel.setTenant_id(tenant_id);
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
      model.setDatasource_id(datasource_id);
      model.setItem_id(jsonNode.findPath("itemId").asText(""));
      model.setType(0);
      model.setCategory(jsonNode.findPath("category").asText(""));
      model.setStatus(0);
      model.setTitle(jsonNode.findPath("title").asText(""));
      model.setAuthor(jsonNode.findPath("author").asText(""));
      model.setPic_urls("https://images.gogbuy.com"+jsonNode.findPath("item_picture").asText(""));
      model.setPrice(0.0);
      extend = jsonNode.findPath("extend");
      if(extend != null){
        JsonNode price = extend.get("price");
        if(price != null){
          model.setPrice(price.asDouble(0.0));
        }
      }
      model.setPublish_time(now.getTime());
      model.setWeight(1);
      model.setContent("");
      model.setLocation("");
      model.setTags(tags);
      model.setExtend("");
      model.setTenant_id(tenant_id);
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
    List<ActionModel> actionList = new ArrayList<>();
    ActionModel actionModel = null;
    Date now = new Date();
    int size = 0;
    String userId = null;
    while(StringUtils.isNotEmpty(line)) {
      jsonNode = JsonUtils.toObject(line);
      userId = jsonNode.findPath("userId").asText("");

      if(StringUtils.isNotEmpty(userId)){
        actionModel = new ActionModel();
        actionModel.setDatasource_id(datasource_id);
        actionModel.setUser_id(userId);
        actionModel.setAction_code(0);
        actionModel.setItem_id(jsonNode.findPath("itemId").asText(""));
        ActionType t = getActionType(jsonNode.findPath("actionType").asText(""));
        actionModel.setAction_code(t.getCode());
        actionModel.setAction_time(jsonNode.findPath("actionTime").asLong(0));
        actionModel.setAction_score(t.getScore());
        actionModel.setTrace_id("");
        actionModel.setExtend("");
        actionModel.setTenant_id(tenant_id);
        actionModel.setCreate_time(now);
        actionModel.setUpdate_time(now);
        actionList.add(actionModel);

        size++;
        if(size > 200){
          actionService.insertBatch(actionList);
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
    List<ActionModel> data = null;
    PageData<ActionModel> pageData = null;
    Map<String, Object> condition = new HashMap<>();
    condition.put(ActionModel.DATASOURCE_ID, datasource_id);
    condition.put("orderBy", "action_id asc");
    do{
      pageNum++;
      pageData = actionService.selectPage(pageNum, pageSize, condition, Cluster.slave);
      data = pageData.getData();
      if (CollectionUtils.isEmpty(data)){ break; }

      for(ActionModel am : data){
        bw.write(String.format(linePattern, am.getUser_id(), am.getItem_id(), am.getAction_score(), am.getAction_time()));
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
