# 数据源配置

## 引入依赖包
```xml
<dependency>
  <groupId>com.xr.scaffold</groupId>
  <artifactId>scaffold-base-jdbc-starter</artifactId>
  <version>1.0-SNAPSHOT</version>
</dependency>
```

## 开启配置
默认是开启状态，引入依赖，会自动加载配置。
可以手动关闭自动加载：
```
xr.base.data.source.enabled=false
```

## 配置Mapper扫描路径
通过@MapperScan注解扫描mapper.java，如：
```
@MapperScan({
        "com.xr.scaffold.account.mapper"
})
```
