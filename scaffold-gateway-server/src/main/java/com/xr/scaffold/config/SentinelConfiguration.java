package com.xr.scaffold.config;

import com.alibaba.csp.sentinel.adapter.gateway.common.api.ApiDefinition;
import com.alibaba.csp.sentinel.adapter.gateway.common.api.GatewayApiDefinitionManager;
import com.alibaba.csp.sentinel.adapter.gateway.common.rule.GatewayFlowRule;
import com.alibaba.csp.sentinel.adapter.gateway.common.rule.GatewayRuleManager;
import com.alibaba.csp.sentinel.adapter.gateway.sc.SentinelGatewayFilter;
import com.alibaba.csp.sentinel.adapter.gateway.sc.callback.GatewayCallbackManager;
import com.alibaba.csp.sentinel.adapter.gateway.sc.exception.SentinelGatewayBlockExceptionHandler;
import com.alibaba.csp.sentinel.datasource.ReadableDataSource;
import com.alibaba.csp.sentinel.datasource.nacos.NacosDataSource;
import com.alibaba.csp.sentinel.init.InitExecutor;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.alibaba.nacos.api.PropertyKeyConst;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.codec.ServerCodecConfigurer;
import org.springframework.web.reactive.result.view.ViewResolver;

import javax.annotation.PostConstruct;
import java.util.Collections;
import java.util.List;
import java.util.Properties;
import java.util.Set;

/**
 * @Author: forvoyager@outlook.com
 * @Time: 2020-11-15 13:32:00
 * @Description: sentinel限流自动配置
 */
@Configuration
public class SentinelConfiguration {

  private final List<ViewResolver> viewResolvers;
  private final ServerCodecConfigurer serverCodecConfigurer;

  @Value("${project.name:${spring.application.name:}}")
  private String projectName;

  @Value("${spring.cloud.nacos.config.server-addr}")
  private String remoteAddress;

  @Value("${spring.cloud.nacos.config.namespace}")
  private String namespace;

  @Value("${spring.cloud.nacos.config.group}")
  private String groupId;

  // sentinel控制台地址
  @Value("${spring.cloud.sentinel.transport.dashboard}")
  private String dashboard;

  // 启动就连接sentinel控制台 默认true
  @Value("${spring.cloud.sentinel.eager:true}")
  private boolean eager;

  // api分组定义 data id
  @Value("${spring.cloud.sentinel.datasource.apiDefinition.nacos.data-id}")
  private String apiDefinitionDataId;

  // 网关流控配置 data id
  @Value("${spring.cloud.sentinel.datasource.gatewayFlow.nacos.data-id}")
  private String gatewayFlowDataId;

  public SentinelConfiguration(ObjectProvider<List<ViewResolver>> viewResolversProvider,
                               ServerCodecConfigurer serverCodecConfigurer) {
    this.viewResolvers = viewResolversProvider.getIfAvailable(Collections::emptyList);
    this.serverCodecConfigurer = serverCodecConfigurer;
  }

  @Bean
  @Order(Ordered.HIGHEST_PRECEDENCE)
  public SentinelGatewayBlockExceptionHandler sentinelGatewayBlockExceptionHandler() {
    // Register the block exception handler for Spring Cloud Gateway.
    return new SentinelGatewayBlockExceptionHandler(viewResolvers, serverCodecConfigurer);
  }

  @Bean
  @Order(-1)
  public GlobalFilter sentinelGatewayFilter() {
    return new SentinelGatewayFilter();
  }

  /**
   * 初始化操作
   */
  @PostConstruct
  public void init() throws Exception {

    // 加载sentinel配置
    System.setProperty("csp.sentinel.app.type", "1"); // 0普通服务 1网关
    System.setProperty("csp.sentinel.app.name", this.projectName);
    System.setProperty("csp.sentinel.dashboard.server", this.dashboard);

    // 自定义限流信息
    GatewayCallbackManager.setBlockHandler(new CustomBlockRequestHandler());

    // 加载自定义规则
    initCustomizedApis();
    initGatewayRules();

    // 连接sentinel控制台
    if (this.eager) {
      InitExecutor.doInit();
    }
  }

  /**
   * 加载api分组规则
   */
  private void initCustomizedApis() {
//    // 自定义api分组
//    Set<ApiDefinition> definitions = new HashSet<>();
//    ApiDefinition gzServer = new ApiDefinition("gz-server-api")
//            .setPredicateItems(new HashSet<ApiPredicateItem>() {{
//              add(new ApiPathPredicateItem().setPattern("/api/server/**")
//                      .setMatchStrategy(SentinelGatewayConstants.URL_MATCH_STRATEGY_PREFIX));
//            }});
//    ApiDefinition manServer = new ApiDefinition("man-server-api")
//            .setPredicateItems(new HashSet<ApiPredicateItem>() {{
//              add(new ApiPathPredicateItem().setPattern("/api/man/**")
//                      .setMatchStrategy(SentinelGatewayConstants.URL_MATCH_STRATEGY_PREFIX));
//            }});
//    definitions.add(gzServer);
//    definitions.add(manServer);
//    GatewayApiDefinitionManager.loadApiDefinitions(definitions);

    // nacos push模式自动更新配置，配置内容见Readme.md
    ReadableDataSource<String, Set<ApiDefinition>> apiDefinitionDataSource = new NacosDataSource<>(
            buildNacosConfig(), groupId, apiDefinitionDataId,
            source -> JSON.parseObject(source, new TypeReference<Set<ApiDefinition>>() {
            })
    );
    GatewayApiDefinitionManager.register2Property(apiDefinitionDataSource.getProperty());
  }

  /**
   * 加载网关流控规则
   */
  private void initGatewayRules() {
//    Set<GatewayFlowRule> rules = new HashSet<>();
//
//    // 创建限流规则
//    GatewayFlowRule rule = new GatewayFlowRule();
//
//    // 根据路由id：RESOURCE_MODE_ROUTE_ID 或 API分组RESOURCE_MODE_CUSTOM_API_NAME 限流
//    rule.setResourceMode(SentinelGatewayConstants.RESOURCE_MODE_CUSTOM_API_NAME);
//    rule.setResource("gz-server-api");
//
//    // 针对请求属性限流
//    rule.setParamItem(new GatewayParamFlowItem()
//            .setParseStrategy(SentinelGatewayConstants.PARAM_PARSE_STRATEGY_HOST)
//    );
//
//    // 阈值类型 QPS:FLOW_GRADE_QPS 线程数:FLOW_GRADE_THREAD
//    rule.setGrade(RuleConstant.FLOW_GRADE_QPS);
//
//    // QPS阈值 或者 线程数量
//    rule.setCount(1);
//
//    // QPS间隔（秒）
//    rule.setIntervalSec(2);
//
//    // 流控方式
//    rule.setControlBehavior(RuleConstant.CONTROL_BEHAVIOR_DEFAULT);
//
//    rule.setBurst(1);
//
//    rules.add(rule);
//
//    GatewayRuleManager.loadRules(rules);

    // nacos push模式自动更新配置，配置内容见Readme.md
    ReadableDataSource<String, Set<GatewayFlowRule>> gatewayFlowDataSource = new NacosDataSource<>(
            buildNacosConfig(), groupId, gatewayFlowDataId,
            source -> JSON.parseObject(source, new TypeReference<Set<GatewayFlowRule>>() {
            })
    );
    GatewayRuleManager.register2Property(gatewayFlowDataSource.getProperty());
  }

  private Properties buildNacosConfig() {
    Properties properties = new Properties();
    properties.setProperty(PropertyKeyConst.SERVER_ADDR, remoteAddress);
    properties.setProperty(PropertyKeyConst.NAMESPACE, namespace);
    return properties;
  }
}
