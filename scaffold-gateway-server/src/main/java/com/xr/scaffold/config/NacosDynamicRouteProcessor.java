package com.xr.scaffold.config;

import com.alibaba.fastjson.JSON;
import com.alibaba.nacos.api.NacosFactory;
import com.alibaba.nacos.api.config.ConfigService;
import com.alibaba.nacos.api.config.listener.Listener;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.gateway.event.RefreshRoutesEvent;
import org.springframework.cloud.gateway.route.RouteDefinition;
import org.springframework.cloud.gateway.route.RouteDefinitionWriter;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.ApplicationEventPublisherAware;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

import javax.annotation.PostConstruct;
import java.util.List;
import java.util.Properties;
import java.util.concurrent.Executor;

/**
 * <b>@author</b>: yang.changyan@foundbyte.com
 * <b>@time</b>: 2020-09-02 14:30:00
 * <b>@description</b>: 基于nacos的动态路由处理
 */
@Component
public class NacosDynamicRouteProcessor implements ApplicationEventPublisherAware {

  @Value("${spring.cloud.nacos.config.server-addr}")
  private String serverAddr;

  @Value("${spring.cloud.nacos.config.namespace}")
  private String namespace;

  @Value("${spring.cloud.nacos.config.data-id}")
  private String dataId;

  @Value("${spring.cloud.nacos.config.group}")
  private String group;

  private Logger logger = LoggerFactory.getLogger(getClass());

  private ApplicationEventPublisher publisher;

  private ConfigService configService;

  @Autowired
  private RouteDefinitionWriter routeDefinitionWriter;

  @PostConstruct
  public void init() throws Exception {
    logger.info("nacos dynamic route init...");

    /*
    nacos配置中心路由信息配置，例：
    [{
        "id": "scaffold-account-server",
        "uri": "lb://scaffold-account-server",
        "predicates": [{
            "name": "Path",
            "args": {
                "_genkey_0": "/api/account/**"
            }
        }],
        "filters": [{
            "name": "StripPrefix",
            "args": {
                "_genkey_0": 2
            }
        }]
    }]
     */

    this.configService = initConfigService();
    String configInfo = this.configService.getConfig(this.dataId, this.group, 3000);
    logger.info("fetch route config:\r\n{}", configInfo);
    List<RouteDefinition> definitionList = JSON.parseArray(configInfo, RouteDefinition.class);
    for (RouteDefinition definition : definitionList) {
      logger.info("update route : {}", definition.toString());
      this.add(definition);
    }

    this.configService.addListener(this.dataId, this.group, new Listener() {
      @Override
      public Executor getExecutor() {
        return null;
      }

      @Override
      public void receiveConfigInfo(String routeInfo) {
        logger.info("nacos dynamic route update:\n\r{}", routeInfo);
        List<RouteDefinition> definitionList = JSON.parseArray(routeInfo, RouteDefinition.class);
        for (RouteDefinition definition : definitionList) {
          // 更新路由信息
          update(definition);
        }
      }
    });

  }

  /**
   * 删除路由
   *
   * @param id
   * @return
   */
  public void delete(String id) {
    logger.info("delete route id {}", id);
    this.routeDefinitionWriter.delete(Mono.just(id));
  }

  /**
   * 更新路由
   *
   * @param definition
   * @return
   */
  public void update(RouteDefinition definition) {
    logger.info("update route {}", definition);
    this.routeDefinitionWriter.delete(Mono.just(definition.getId()));

    routeDefinitionWriter.save(Mono.just(definition)).subscribe();
    this.publisher.publishEvent(new RefreshRoutesEvent(this));
  }

  /**
   * 增加路由
   *
   * @param definition
   * @return
   */
  public void add(RouteDefinition definition) {
    logger.info("add route {}", definition);
    routeDefinitionWriter.save(Mono.just(definition)).subscribe();
    this.publisher.publishEvent(new RefreshRoutesEvent(this));
  }

  @Override
  public void setApplicationEventPublisher(ApplicationEventPublisher applicationEventPublisher) {
    this.publisher = applicationEventPublisher;
  }

  private ConfigService initConfigService() throws Exception {
    Properties properties = new Properties();
    properties.setProperty("serverAddr", this.serverAddr);
    properties.setProperty("namespace", this.namespace);
    return NacosFactory.createConfigService(properties);
  }
}
