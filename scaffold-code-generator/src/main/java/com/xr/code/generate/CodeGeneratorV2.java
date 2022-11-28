package com.xr.code.generate;

import com.baomidou.mybatisplus.annotation.DbType;
import com.baomidou.mybatisplus.core.toolkit.StringPool;
import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.InjectionConfig;
import com.baomidou.mybatisplus.generator.config.*;
import com.baomidou.mybatisplus.generator.config.po.TableInfo;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;
import com.baomidou.mybatisplus.generator.engine.FreemarkerTemplateEngine;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <b>@author</b>: forvoyager@outlook.com
 * <b>@time</b>: 2020-10-09 11:16:00
 * <b>@description</b>: 代码生成器
 */
public class CodeGeneratorV2 {

  public static void main(String[] args) {
    generate();
  }

  public static void generate() {

    String outputPath = "F:\\xR\\code"; // 代码保存路径
    String projectName = "onebuygz-message-center"; // 项目名称
    String moduleName = "message"; // 模块名称
    String packagePath = "com.onebuygz"; // 包路径
    String tablePrefix = "mc_"; // 表前缀
    String author = "forvoyager@outlook.com"; // 作者
    String db = "localhost:3306/xr_scaffold_db"; // 数据库地址
    String username = "root";
    String password = "123456";

    // 需要生成代码的表
    List<String> tables = new ArrayList<>();
    tables.add("mc_application");
//    tables.add("mc_push_audience");
//    tables.add("mc_push_detail");
//    tables.add("mc_push_device");
    tables.add("mc_push_message");

    // ============ 配置上面的信息即可，下面的内容不用修改 ====================

    String finalPath = outputPath + "/" + projectName;

    // 代码生成器
    AutoGenerator mpg = new AutoGenerator();

    // set freemarker engine
    mpg.setTemplateEngine(new FreemarkerTemplateEngine());

    // 全局配置
    GlobalConfig gc = new GlobalConfig();
    gc.setOutputDir(finalPath + "/src/main/java");
    gc.setAuthor(author);
    gc.setOpen(false);
    gc.setSwagger2(false);
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
    dsc.setUrl("jdbc:mysql://" + db + "?characterEncoding=utf-8&serverTimezone=GMT%2B8&allowMultiQueries=true&zeroDateTimeBehavior=convertToNull");
    dsc.setDriverName("com.mysql.cj.jdbc.Driver");
    dsc.setUsername(username);
    dsc.setPassword(password);
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
        Map map = new HashMap();
        map.put("parentPath", packagePath);
        map.put("datetime", LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
        this.setMap(map);
      }
    };
    mpg.setCfg(cfg);

    // 自定义输出配置
    List<FileOutConfig> focList = new ArrayList<>();
    // 输出mapper xml文件
    focList.add(new FileOutConfig("/templates/mapper.xml.ftl") {
      @Override
      public String outputFile(TableInfo tableInfo) {
        // 自定义输出文件名 ， 如果你 Entity 设置了前后缀、此处注意 xml 的名称会跟着发生变化！！
        return finalPath + "/src/main/resources/mapper/" + tableInfo.getMapperName() + StringPool.DOT_XML;
      }
    });
    // 输出通用dto
    focList.add(new FileOutConfig("tpl/common/XxxDto.ftl") {
      @Override
      public String outputFile(TableInfo tableInfo) {
        StringBuilder sb = new StringBuilder();
        sb.append(outputPath).append("/");
        sb.append(projectName).append("-common/");
        sb.append("src").append("/");
        sb.append("main").append("/");
        sb.append("java").append("/");
        sb.append(packagePath.replace(".", "/")).append("/");
        sb.append(moduleName).append("/");
        sb.append("common").append("/");
        sb.append("dto").append("/");
        sb.append(tableInfo.getEntityName().replace("Model","Dto"));
        return sb.toString() + StringPool.DOT_JAVA;
      }
    });

    // 输出feign client
    focList.add(new FileOutConfig("tpl/client/XxxClient.ftl") {
      @Override
      public String outputFile(TableInfo tableInfo) {
        return finalPath + "/src/main/java/"+packagePath.replace(".", "/")+"/message/client/" + tableInfo.getEntityName().replace("Model","")+"Client" + StringPool.DOT_JAVA;
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
    templateConfig.setController("tpl/MybatisController");

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
