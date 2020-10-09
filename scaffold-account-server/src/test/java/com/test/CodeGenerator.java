package com.test;

import com.baomidou.mybatisplus.annotation.DbType;
import com.baomidou.mybatisplus.core.toolkit.StringPool;
import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.InjectionConfig;
import com.baomidou.mybatisplus.generator.config.*;
import com.baomidou.mybatisplus.generator.config.converts.MySqlTypeConvert;
import com.baomidou.mybatisplus.generator.config.po.TableInfo;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;
import com.baomidou.mybatisplus.generator.engine.FreemarkerTemplateEngine;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author: forvoyager@outlook.com
 * @Time: 2020-10-07 21:23:00
 * @Description: 代码生成器
 */
public class CodeGenerator {

  @Test
  public void generate() {

    String outputPath = "E:\\code";
    String projectName = "scaffold-account-server";
    String moduleName = "account";
    String packagePath = "com.xr.scaffold";
    String tablePrefix = "xr_";
    String finalPath = outputPath + "/" + projectName;

    // 需要生成代码的表
    List<String> tables = new ArrayList<>();
    tables.add("xr_user");
    tables.add("xr_user_account");

    // 代码生成器
    AutoGenerator mpg = new AutoGenerator();

    // set freemarker engine
    mpg.setTemplateEngine(new FreemarkerTemplateEngine());

    // 全局配置
    GlobalConfig gc = new GlobalConfig();
    gc.setOutputDir(finalPath + "/src/main/java");
    gc.setAuthor("forvoyager@outlook.com");
    gc.setOpen(false);
//    gc.setSwagger2(true);
    gc.setActiveRecord(false);
    gc.setFileOverride(true);
    gc.setEntityName("%sModel");
    gc.setServiceName("I%sService");
    gc.setServiceImplName("%sServiceImpl");
    gc.setControllerName("%sController");
    gc.setMapperName("%sMapper");
    mpg.setGlobalConfig(gc);

    // 数据源配置
    DataSourceConfig dsc = new DataSourceConfig();
    dsc.setDbType(DbType.MYSQL);
    dsc.setUrl("jdbc:mysql://localhost:3306/xr_scaffold_db?characterEncoding=utf-8&serverTimezone=UTC&allowMultiQueries=true&zeroDateTimeBehavior=convertToNull");
    dsc.setDriverName("com.mysql.cj.jdbc.Driver");
    dsc.setUsername("xr_scaffold");
    dsc.setPassword("123456ey4nGsVL**");
//    dsc.setTypeConvert(new MySqlTypeConvert(){
//      @Override
//      public DbColumnType processTypeConvert(GlobalConfig globalConfig, String fieldType){
//        //tinyint转换成Boolean
//        if (fieldType.toLowerCase().contains("tinyint")){
//            return DbColumnType.BOOLEAN;
//        }
//        //将数据库中datetime转换成date
//        if (fieldType.toLowerCase().contains("datetime")) {
//          return DbColumnType.DATE;
//        }
//        return (DbColumnType) super.processTypeConvert(globalConfig, fieldType);
//      }
//    });
    mpg.setDataSource(dsc);

    // 包配置
    PackageConfig pc = new PackageConfig();
    pc.setModuleName(moduleName);
    pc.setParent(packagePath);
    mpg.setPackageInfo(pc);

    // 自定义配置
    InjectionConfig cfg = new InjectionConfig() {
      @Override
      public void initMap() {
        // to do nothing
      }
    };

    // 如果模板引擎是 freemarker
    String templatePath = "/templates/mapper.xml.ftl";
    // 如果模板引擎是 velocity
    // String templatePath = "/templates/mapper.xml.vm";

    // 自定义输出配置
    List<FileOutConfig> focList = new ArrayList<>();
    // 自定义配置会被优先输出
    focList.add(new FileOutConfig(templatePath) {
      @Override
      public String outputFile(TableInfo tableInfo) {
        // 自定义输出文件名 ， 如果你 Entity 设置了前后缀、此处注意 xml 的名称会跟着发生变化！！
        return finalPath + "/src/main/resources/mybatis/mapper/" + tableInfo.getMapperName() + StringPool.DOT_XML;
      }
    });
    cfg.setFileOutConfigList(focList);
    mpg.setCfg(cfg);

    // 配置模板
    TemplateConfig templateConfig = new TemplateConfig();

    // 配置自定义输出模板
    //指定自定义模板路径，注意不要带上.ftl/.vm, 会根据使用的模板引擎自动识别
    // templateConfig.setEntity("templates/entity2.java");
    // templateConfig.setService();
    // templateConfig.setController();

    templateConfig.setXml(null);
    mpg.setTemplate(templateConfig);

    // 策略配置
    StrategyConfig strategy = new StrategyConfig();
    strategy.setNaming(NamingStrategy.underline_to_camel);
    strategy.setColumnNaming(NamingStrategy.underline_to_camel);
//    strategy.setSuperEntityClass("com.xr.base.core.model.BaseModel");
    strategy.setEntityLombokModel(false);
    strategy.setEntityColumnConstant(true);
    strategy.setRestControllerStyle(true);
    strategy.setVersionFieldName("version");
    strategy.setChainModel(true);
    // 公共父类
    strategy.setSuperControllerClass("");
    // 写于父类中的公共字段
//    strategy.setSuperEntityColumns("create_time", "update_time", "version");
    strategy.setInclude(tables.toArray(new String[]{}));
    strategy.setControllerMappingHyphenStyle(true);
    strategy.setTablePrefix(tablePrefix);
    mpg.setStrategy(strategy);
    mpg.setTemplateEngine(new FreemarkerTemplateEngine());
    mpg.execute();
  }

}
