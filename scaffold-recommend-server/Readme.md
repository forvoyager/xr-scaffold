# 推荐系统
推荐系统demo

# 主要技术
* [x] spring boot
* [x] spring gateway
* [x] dubbo
* [x] nacos
* [x] mysql
* [x] pagehelper
* [x] jackson
* [x] swagger

# 主要模块

## 注册中心

## 网关

## 数据库设计
* 数据源
```sql
CREATE TABLE `res_datasource` (
  `datasource_id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '数据源id',
  `source_code` varchar(30) NOT NULL DEFAULT '' COMMENT '数据源编码',
  `name` varchar(30) NOT NULL DEFAULT '' COMMENT '数据源名称',
  `status` tinyint(4) NOT NULL DEFAULT '0' COMMENT '状态 0无效 1有效',
  `remark` varchar(45) NOT NULL DEFAULT '' COMMENT '备注',
  `tenant_id` varchar(30) NOT NULL DEFAULT '' COMMENT '租户id',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`datasource_id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC COMMENT='推荐数据源';
```

* 用户数据
```sql
CREATE TABLE `res_user` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键id',
  `datasource_id` bigint(20) NOT NULL COMMENT '数据源id',
  `user_id` varchar(32) NOT NULL COMMENT '用户id',
  `age` tinyint(4) NOT NULL DEFAULT '0' COMMENT '年龄',
  `gender` tinyint(4) NOT NULL DEFAULT '2' COMMENT '性别 0女 1男 2未知',
  `location` varchar(1024) NOT NULL COMMENT '用户位置信息，例：{"location": {"lat": 181.88, "lon": -23.57}}',
  `tags` varchar(1024) NOT NULL COMMENT '用户标签List[String]，如["抠脚大汉"]',
  `extend` varchar(1024) NOT NULL COMMENT '扩展字段json, key:value',
  `tenant_id` varchar(30) NOT NULL DEFAULT '' COMMENT '租户id',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC COMMENT='用户数据';
```

* 物品数据
```sql
CREATE TABLE `res_item` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键id',
  `datasource_id` bigint(20) NOT NULL COMMENT '数据源id',
  `item_id` varchar(30) NOT NULL COMMENT '物品id',
  `type` tinyint(4) NOT NULL DEFAULT '0' COMMENT '物品类型 0-item 1-article 2-video 3-audio 4-image',
  `category` varchar(100) NOT NULL DEFAULT '' COMMENT '物品类目，支持多级，如：蔬菜/叶菜/白菜',
  `status` tinyint(4) NOT NULL DEFAULT '0' COMMENT '状态 0可推荐 1不可推荐',
  `title` varchar(120) NOT NULL DEFAULT '' COMMENT '标题',
  `author` varchar(30) NOT NULL DEFAULT '' COMMENT '物品作者，如：文章作者、物品所属店铺名称、视频的发布人等',
  `pic_urls` varchar(120) NOT NULL DEFAULT '' COMMENT '物品图像地址，多个用,隔开',
  `price` decimal(10,2) NOT NULL DEFAULT '0.00' COMMENT '价格',
  `publish_time` bigint(20) NOT NULL DEFAULT '0' COMMENT '物品发布时间，UTC时间，单位：秒',
  `weight` tinyint(4) NOT NULL DEFAULT '1' COMMENT '物品权重，权重越高越容易被推荐，默认1，取值1-100',
  `content` text COMMENT '简介/摘要/正文关键片段等',
  `location` varchar(1024) NOT NULL DEFAULT '' COMMENT '用户位置信息，例：{"location": {"lat": 181.88, "lon": -23.57}}',
  `tags` varchar(1024) NOT NULL DEFAULT '' COMMENT '用户标签List[String]，如["抠脚大汉"]',
  `extend` varchar(1024) NOT NULL DEFAULT '' COMMENT '扩展字段json, key:value',
  `tenant_id` varchar(30) NOT NULL DEFAULT '' COMMENT '租户id',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE,
  KEY `idx_item_id` (`tenant_id`,`item_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC COMMENT='物品数据';
```

* 行为数据
```sql
CREATE TABLE `res_action` (
  `action_id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键id',
  `datasource_id` bigint(20) NOT NULL COMMENT '数据源id',
  `user_id` varchar(32) NOT NULL COMMENT '用户id',
  `item_id` varchar(30) NOT NULL COMMENT '物品id',
  `action_code` smallint(4) NOT NULL DEFAULT '0' COMMENT '行为类型编码 见系统枚举ActionType',
  `action_time` bigint(20) NOT NULL DEFAULT '0' COMMENT '行为发生时间（UTC），单位秒',
  `action_score` int(11) NOT NULL DEFAULT '0' COMMENT '行为评分，不设置则使用默认评分',
  `trace_id` varchar(100) NOT NULL DEFAULT '' COMMENT '跟踪id，跟踪被推荐的物品',
  `extend` varchar(1024) NOT NULL DEFAULT '' COMMENT '扩展字段json, key:value',
  `tenant_id` varchar(30) NOT NULL DEFAULT '' COMMENT '租户id',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`action_id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC COMMENT='行为数据';
```

* 场景数据
```sql
CREATE TABLE `res_scene` (
  `scene_id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '场景id',
  `datasource_id` bigint(20) NOT NULL COMMENT '数据源id',
  `name` varchar(30) NOT NULL DEFAULT '' COMMENT '场景名称',
  `remark` varchar(300) NOT NULL DEFAULT '' COMMENT '场景备注',
  `config` varchar(300) NOT NULL DEFAULT '' COMMENT '配置信息，如点击消息跳转xx地址',
  `tenant_id` varchar(30) NOT NULL DEFAULT '' COMMENT '租户id',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`scene_id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC COMMENT='推荐场景';
```

## 基础代码生成
