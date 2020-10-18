# 模板工程
包括基础代码，用于加快开发速度。

# 主要技术
* [x] spring cloud
* [x] spring boot
* [x] spring gateway
* [x] nacos
* [x] mysql
* [x] pagehelper
* [x] jackson
* [x] swagger

# 主要模块

## 注册中心
> nacos discovery

## 网关
> scaffold-gateway-server

基于nacos config的动态网关路由，使用前需要在nacos中配置网关路由规则，如下：
```json
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
```

## jdbc通用模块
封装了jdbc基本操作（见com.xr.base.jdbc.service.impl.BaseServiceImpl）、自动数据源配置。使用时引入该模块即可：
```xml
<dependency>
  <groupId>com.xr.scaffold</groupId>
  <artifactId>scaffold-base-jdbc-starter</artifactId>
  <version>1.0-SNAPSHOT</version>
</dependency>
```
## 基础代码生成
[传送门](https://github.com/forvoyager/code-generator)
