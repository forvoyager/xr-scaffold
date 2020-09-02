//package com.xr.scaffold.account.config;
//
//import com.alibaba.cloud.sentinel.SentinelProperties;
//import com.alibaba.cloud.sentinel.datasource.config.DataSourcePropertiesConfiguration;
//import com.alibaba.cloud.sentinel.datasource.config.NacosDataSourceProperties;
//import com.alibaba.csp.sentinel.adapter.servlet.callback.UrlBlockHandler;
//import com.alibaba.csp.sentinel.datasource.Converter;
//import com.alibaba.csp.sentinel.datasource.ReadableDataSource;
//import com.alibaba.csp.sentinel.datasource.nacos.NacosDataSource;
//import com.alibaba.csp.sentinel.slots.block.flow.FlowRule;
//import com.alibaba.csp.sentinel.slots.block.flow.FlowRuleManager;
//import com.alibaba.fastjson.JSON;
//import com.alibaba.fastjson.TypeReference;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//
//import javax.annotation.PostConstruct;
//import java.util.List;
//import java.util.Map;
//import java.util.Properties;
//
///**
// * <b>@author</b>: yang.changyan@foundbyte.com
// * <b>@time</b>: 2020-08-11 14:11:00
// * <b>@description</b>:
// */
//@Configuration
//public class SentinelConfig {
//
//  @Autowired
//  private SentinelProperties sentinelProperties;
//
//  @Bean
//  public UrlBlockHandler urlBlockHandler(){
//    return new SentinelUrlBlockHandler();
//  }
//
//  /**
//   *
//   */
//  @PostConstruct
//  public void init(){
////    WebServletConfig.setBlockPage("http://www.baidu.com");
//
//    Properties properties = null;
//    NacosDataSourceProperties ndsp = null;
//    Map<String, DataSourcePropertiesConfiguration> dataSource = sentinelProperties.getDatasource();
//    for(Map.Entry<String, DataSourcePropertiesConfiguration> ds : dataSource.entrySet()){
//      ndsp = ds.getValue().getNacos();
//      properties = new Properties();
//      properties.setProperty("namespace", ndsp.getNamespace());
//      properties.setProperty("serverAddr", ndsp.getServerAddr());
//      Converter<String, List<FlowRule>> parser = source -> JSON.parseObject(source,new TypeReference<List<FlowRule>>() {});
//      ReadableDataSource<String, List<FlowRule>> nacosDataSource = new NacosDataSource<>(properties, ndsp.getGroupId(), ndsp.getDataId(), parser);
//      FlowRuleManager.register2Property(nacosDataSource.getProperty());
//    }
//  }
//
//}
