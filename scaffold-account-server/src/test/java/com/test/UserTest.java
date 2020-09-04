package com.test;

import com.alibaba.druid.support.json.JSONUtils;
import com.github.houbb.junitperf.core.annotation.JunitPerfConfig;
import com.github.houbb.junitperf.core.annotation.JunitPerfRequire;
import com.xr.scaffold.account.ScaffoldAccountApplication;
import com.xr.scaffold.account.common.model.UserModel;
import com.xr.scaffold.account.common.service.IUserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * <b>@author</b>: yang.changyan@foundbyte.com
 * <b>@time</b>: 2020-09-04 19:45:00
 * <b>@description</b>:
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = ScaffoldAccountApplication.class)
@AutoConfigureMockMvc
public class UserTest {

  @Autowired
  private MockMvc mockMvc;

  @Autowired
  private IUserService userService;

  /**
   * 配置：30个线程运行。准备时间：5000ms。运行时间: 60000ms。
   * 要求：最快不可低于 210ms, 最慢不得低于 250ms, 平均不得低于 225ms, 每秒运行次数不得低于 200 次。
   * 70% 的数据不低于 500ms, 90% 的数据不得低于 1000ms;
   *
   * @throws InterruptedException if any
   */
  @Test
  @JunitPerfConfig(threads = 30, warmUp = 5000, duration = 1*60*1000)
  @JunitPerfRequire(min = 100, max = 1000, average = 600, timesPerSecond = 200, percentiles = {"70:500", "90:1000"})
  public void test_insert() throws Exception{
    long current = System.currentTimeMillis();
    UserModel userModel = new UserModel();
    userModel.setNick_name(UUID.randomUUID().toString());
    userModel.setCreate_time(current);
    userModel.setUpdate_time(current);
    userService.insert(userModel);

//    Map map = new HashMap<>();
//    map.put("user_id", 1002);
//    map.put("pay_id", "101"+System.currentTimeMillis());
//
//    mockMvc.perform(post("/compensation/producer/upload")
//            .contentType(MediaType.APPLICATION_JSON)
//            .param("appid", "14210")
//            .param("topic", "topic1")
//            .param("tag", "tag1-1")
//            .param("msgId", "TEST_"+System.currentTimeMillis())
//            .param("content", JSONUtils.toJSONString(map))
//    ).andExpect(status().isOk())
//            .andExpect(MockMvcResultMatchers.jsonPath("$.code").value("200"))
//            .andDo(print());

  }

}
