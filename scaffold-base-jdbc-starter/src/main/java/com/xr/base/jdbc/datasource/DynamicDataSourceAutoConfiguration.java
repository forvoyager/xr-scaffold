//package com.xr.base.jdbc.datasource;
//
//import com.alibaba.druid.pool.DruidDataSource;
//import com.xr.base.core.enums.Cluster;
//import org.apache.ibatis.session.SqlSessionFactory;
//import org.mybatis.spring.SqlSessionFactoryBean;
//import org.mybatis.spring.transaction.SpringManagedTransactionFactory;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.beans.factory.annotation.Qualifier;
//import org.springframework.boot.context.properties.ConfigurationProperties;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.context.annotation.DependsOn;
//import org.springframework.context.annotation.Primary;
//import org.springframework.core.io.ClassPathResource;
//import org.springframework.core.io.Resource;
//import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
//import org.springframework.jdbc.datasource.DataSourceTransactionManager;
//
//import javax.sql.DataSource;
//import java.util.HashMap;
//import java.util.Map;
//
///**
// * 数据源自动配置
// */
//@Configuration
//public class DynamicDataSourceAutoConfiguration {
//
//  @Bean
//  public DataSourceInterceptor dataSourceInterceptor(){
//    return new DataSourceInterceptor();
//  }
//
//  @Bean
//  @ConfigurationProperties("spring.datasource.druid.master")
//  public DataSource masterDataSource() {
//    /*
//    配置信息会在初始化时注入
//    配置信息来源@ConfigurationProperties
//     */
//    return new DruidDataSource();
//  }
//
//  @Bean
//  @ConfigurationProperties("spring.datasource.druid.slave")
//  public DataSource slaveDataSource() {
//    return new DruidDataSource();
//  }
//
//  @Bean
//  @DependsOn({"masterDataSource", "slaveDataSource"})
//  @Primary
//  public DataSource dataSource(
//          @Qualifier("masterDataSource") DataSource master,
//          @Qualifier("slaveDataSource") DataSource slave
//  ) {
//    DynamicDataSource dynamicDataSource = new DynamicDataSource();
//    dynamicDataSource.setDefaultTargetDataSource(master);
//
//    Map<Object, Object> targetDataSources = new HashMap<>();
//    targetDataSources.put(null, master);
//    targetDataSources.put(Cluster.master, master);
//    targetDataSources.put(Cluster.slave, slave);
//    dynamicDataSource.setTargetDataSources(targetDataSources);
//
//    return dynamicDataSource;
//  }
//
//  @Bean
//  @Primary
//  public DataSourceTransactionManager transactionManager(DataSource dataSource) {
//    return new DataSourceTransactionManager(dataSource);
//  }
//
//  @Bean
//  public SqlSessionFactory sqlSessionFactory(DataSource dataSource) throws Exception {
//    final SqlSessionFactoryBean sessionFactory = new SqlSessionFactoryBean();
//    // set data source
//    sessionFactory.setDataSource(dataSource);
//
//    // set config location
//    sessionFactory.setConfigLocation(new ClassPathResource("/mybatis/mybatis-config.xml"));
//
//    // set mapper xml location
//    Resource[] resources = new PathMatchingResourcePatternResolver().getResources("classpath*:mybatis/mapper/**/*.xml");
//    sessionFactory.setMapperLocations(resources);
//
//    // set tx manager
//    sessionFactory.setTransactionFactory(new SpringManagedTransactionFactory());
//
//    return sessionFactory.getObject();
//  }
//
//  private Logger logger = LoggerFactory.getLogger(getClass());
//
//  public DynamicDataSourceAutoConfiguration(){
//    logger.info("init data source...");
//  }
//}
