package com.test;

import com.xr.scaffold.account.ScaffoldAccountApplication;
import com.zaxxer.hikari.HikariDataSource;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.sql.DataSource;

/**
 * <b>@author</b>: yang.changyan@foundbyte.com
 * <b>@time</b>: 2020-08-06 09:33:00
 * <b>@description</b>:
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = ScaffoldAccountApplication.class)
public class DataSourceTest {
  @Autowired
  DataSource dataSource;

  @Autowired
  HikariDataSource hikariDataSource;

  @Autowired
  DataSourceProperties dataSourceProperties;

  @Test
  public void testDataSource() throws Exception {
    // 查看配置数据源信息
    System.out.println(dataSource);
    System.out.println(dataSource.getClass().getName());
    System.out.println(dataSourceProperties);
  }
}
