CREATE SCHEMA `xr_scaffold_db` DEFAULT CHARACTER SET utf8 COLLATE utf8_general_ci ;

USE `xr_scaffold_db`;

create user 'xr_scaffold'@'%' identified by '123456ey4nGsVL**';

-- 分配权限
grant all privileges on xr_scaffold_db.* to 'xr_scaffold'@'%' with grant option;
grant all privileges on xr_scaffold_db.* to 'xr_scaffold'@'localhost' with grant option;
-- 刷新mysql用户权限
flush privileges ;

CREATE TABLE `xr_user` (
  `user_id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '用户id',
  `nick_name` varchar(45) NOT NULL DEFAULT '' COMMENT '用户昵称',
  `status` tinyint(4) NOT NULL DEFAULT 0 COMMENT '状态 0锁定 1有效',
  `version` tinyint(4) NOT NULL DEFAULT 0 COMMENT '版本号',
  `create_time` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '最后更新时间',
  PRIMARY KEY (`user_id`),
  UNIQUE KEY `unq_nick_name` (`nick_name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='用户表';


CREATE TABLE `xr_user_account` (
  `user_id` BIGINT NOT NULL COMMENT '用户id',
  `available` DECIMAL(11, 2) NOT NULL DEFAULT 0.0 COMMENT '可用余额',
  `frozen` DECIMAL(11, 2) NOT NULL DEFAULT 0.0 COMMENT '冻结金额',
  `version` tinyint(4) NOT NULL DEFAULT 0 COMMENT '版本号',
  `create_time` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '最后更新时间',
  PRIMARY KEY (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=UTF8MB4 COMMENT='用户账户表';
