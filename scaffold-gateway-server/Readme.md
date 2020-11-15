# 网关

# 动态网关路由配置
> nacos

# 限流
> sentinel
> nacos push模式自动加载配置

## 自定义api分组
```json
[{
	"apiName": "scaffold-account-server-api",
	"predicateItems": [{
		"@type": "com.alibaba.csp.sentinel.adapter.gateway.common.api.ApiPathPredicateItem",
		"pattern": "/api/account/**",
		"matchStrategy": 1
	}]
}]
```

## 网关限流规则
```json
[{
	"resource": "scaffold-account-server-api",
	"resourceMode": 1,
	"grade": 1,
	"count": 1.0,
	"intervalSec": 2,
	"controlBehavior": 0,
	"burst": 1,
	"maxQueueingTimeoutMs": 500,
	"paramItem": {
		"index": 0,
		"parseStrategy": 1,
		"fieldName": null,
		"pattern": null,
		"matchStrategy": 0
	}
}]
```


