package com.xr.base.jdbc.datasource;

import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.support.http.StatViewServlet;
import com.alibaba.druid.support.http.WebStatFilter;
import com.alibaba.druid.support.spring.stat.DruidStatInterceptor;
import com.github.pagehelper.PageInterceptor;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.transaction.SpringManagedTransactionFactory;
import org.springframework.aop.support.DefaultPointcutAdvisor;
import org.springframework.aop.support.JdkRegexpMethodPointcut;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.Scope;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;

/**
 * 数据源自动配置
 */
@Configuration
@ConditionalOnProperty(prefix = "xr.base.data.source", name = "enabled", matchIfMissing = true)
@EnableTransactionManagement
public class DruidDataSourceAutoConfiguration {

  public DruidDataSourceAutoConfiguration(){
    System.out.println("init data source...");
  }

  @Bean
  @Primary
  @ConfigurationProperties("spring.datasource.druid")
  public DataSource dataSource() {
    return new DruidDataSource();
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

  @Bean
  public PlatformTransactionManager transactionManager(DataSource dataSource) {
    return new DataSourceTransactionManager(dataSource);
  }

  /**
   * 分页插件
   */
  public PageInterceptor pageInterceptor() {
    return new PageInterceptor();
  }

  @Bean
  public ServletRegistrationBean druidServlet() {
    ServletRegistrationBean servletRegistrationBean = new ServletRegistrationBean(
            new StatViewServlet(), "/druid/*");
    servletRegistrationBean.addInitParameter("allow", "127.0.0.1");
    servletRegistrationBean.addInitParameter("loginUsername", "admin");
    servletRegistrationBean.addInitParameter("loginPassword", "admin");
    // 是否可以重置数据源，禁用HTML页面上的“Reset All”功能
    servletRegistrationBean.addInitParameter("resetEnable", "false");
    return servletRegistrationBean ;
  }

  @Bean
  public FilterRegistrationBean filterRegistrationBean() {
    FilterRegistrationBean filterRegistrationBean = new FilterRegistrationBean() ;
    filterRegistrationBean.setFilter(new WebStatFilter());
    filterRegistrationBean.addUrlPatterns("/*");
    filterRegistrationBean.addInitParameter("exclusions", "*.js,*.gif,*.jpg,*.css,/druid/*");
    return filterRegistrationBean ;
  }

//  @Bean
//  public DruidStatInterceptor druidStatInterceptor() {
//    return new DruidStatInterceptor();
//  }
//
//  @Bean
//  @Scope("prototype")
//  public JdkRegexpMethodPointcut druidStatPointcut() {
//    String[] patterns = new String[]{
//            "com.xr..*.service.*",
//            "com.xr..*.mapper.*"
//    };
//    JdkRegexpMethodPointcut pointcut = new JdkRegexpMethodPointcut();
//    pointcut.setPatterns(patterns);
//    return pointcut;
//  }
//
//  @Bean
//  public DefaultPointcutAdvisor druidStatAdvisor(DruidStatInterceptor druidStatInterceptor, JdkRegexpMethodPointcut druidStatPointcut) {
//    DefaultPointcutAdvisor defaultPointAdvisor = new DefaultPointcutAdvisor();
//    defaultPointAdvisor.setPointcut(druidStatPointcut);
//    defaultPointAdvisor.setAdvice(druidStatInterceptor);
//    return defaultPointAdvisor;
//  }
}
