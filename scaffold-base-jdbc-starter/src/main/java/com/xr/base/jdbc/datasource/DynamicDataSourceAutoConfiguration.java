package com.xr.base.jdbc.datasource;

import com.github.pagehelper.PageInterceptor;
import com.xr.base.core.enums.Cluster;
import com.zaxxer.hikari.HikariDataSource;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.transaction.SpringManagedTransactionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;
import org.springframework.context.annotation.Primary;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;

/**
 * 数据源自动配置
 */
@Configuration
@ConditionalOnProperty(prefix = "xr.base.data.source", name = "enabled", matchIfMissing = true)
public class DynamicDataSourceAutoConfiguration {

  private Logger logger = LoggerFactory.getLogger(getClass());

  @Value("${spring.datasource.slave.url}")
  private String slaveUrl;

  @Value("${spring.datasource.slave.username}")
  private String slaveUsername;

  @Value("${spring.datasource.slave.password}")
  private String slavePassword;

  public DynamicDataSourceAutoConfiguration(){
    logger.info("init dynamic data source...");
  }

  @Bean
  public DataSourceInterceptor dataSourceInterceptor(){
    return new DataSourceInterceptor();
  }

  @Bean
  @ConfigurationProperties("spring.datasource.hikari")
  public DataSource masterDataSource(DataSourceProperties properties) {
    HikariDataSource dataSource = properties.initializeDataSourceBuilder().type(HikariDataSource.class).build();
    dataSource.setPoolName("dynamic-datasource-master-");
    return dataSource;
  }

  @Bean
  @ConfigurationProperties("spring.datasource.hikari")
  public DataSource slaveDataSource(DataSourceProperties properties) {
    HikariDataSource dataSource = properties.initializeDataSourceBuilder().type(HikariDataSource.class).build();
    dataSource.setPoolName("dynamic-datasource-slave-");
    dataSource.setJdbcUrl(this.slaveUrl);
    dataSource.setUsername(this.slaveUsername);
    dataSource.setPassword(this.slavePassword);
    return dataSource;
  }

  @Bean
  @DependsOn({"masterDataSource", "slaveDataSource"})
  @Primary
  public DataSource dataSource(
          @Qualifier("masterDataSource") DataSource master,
          @Qualifier("slaveDataSource") DataSource slave
  ) {
    DynamicDataSource dynamicDataSource = new DynamicDataSource();
    dynamicDataSource.setDefaultTargetDataSource(master);

    Map<Object, Object> targetDataSources = new HashMap<>();
    targetDataSources.put(null, master);
    targetDataSources.put(Cluster.master, master);
    targetDataSources.put(Cluster.slave, slave);
    dynamicDataSource.setTargetDataSources(targetDataSources);

    return dynamicDataSource;
  }

  @Bean
  @Primary
  public PlatformTransactionManager transactionManager(DataSource dataSource) {
    return new DataSourceTransactionManager(dataSource);
  }

  @Bean
  public SqlSessionFactory sqlSessionFactory(DataSource dataSource) throws Exception {
    final SqlSessionFactoryBean sessionFactory = new SqlSessionFactoryBean();
    // set data source
    sessionFactory.setDataSource(dataSource);

    // set config location
    sessionFactory.setConfigLocation(new ClassPathResource("/mybatis/mybatis-config.xml"));

    // set mapper xml location
    Resource[] resources = new PathMatchingResourcePatternResolver().getResources("classpath*:mybatis/mapper/**/*.xml");
    sessionFactory.setMapperLocations(resources);

    // set plugin
    sessionFactory.setPlugins(pageInterceptor());

    // set tx manager
    sessionFactory.setTransactionFactory(new SpringManagedTransactionFactory());

    return sessionFactory.getObject();
  }

  /**
   * 分页插件
   */
  public PageInterceptor pageInterceptor() {
    return new PageInterceptor();
  }

}
