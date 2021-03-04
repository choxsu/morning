/*
 Navicat Premium Data Transfer

 Source Server         : local-conn
 Source Server Type    : MySQL
 Source Server Version : 80022
 Source Host           : localhost:3306
 Source Schema         : morning_db

 Target Server Type    : MySQL
 Target Server Version : 80022
 File Encoding         : 65001

 Date: 04/03/2021 13:54:52
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for mo_account
-- ----------------------------
DROP TABLE IF EXISTS `mo_account`;
CREATE TABLE `mo_account` (
  `id` int NOT NULL AUTO_INCREMENT,
  `nickname` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `username` varchar(150) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `password` varchar(150) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `salt` varchar(150) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `status` int NOT NULL,
  `create_at` datetime NOT NULL,
  `ip` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `avatar` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `like_count` int NOT NULL DEFAULT '0' COMMENT '被赞次数',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=COMPACT;

-- ----------------------------
-- Table structure for mo_article
-- ----------------------------
DROP TABLE IF EXISTS `mo_article`;
CREATE TABLE `mo_article` (
  `id` int NOT NULL AUTO_INCREMENT COMMENT '主键id',
  `account_id` int DEFAULT NULL COMMENT '账户id',
  `title` varchar(150) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '标题',
  `context_type` int NOT NULL DEFAULT '1' COMMENT '内容格式 1-富文本 2-markdown',
  `content` longtext CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '内容',
  `click_count` int NOT NULL DEFAULT '0' COMMENT '点击次数',
  `is_delete` int NOT NULL DEFAULT '0' COMMENT '是否删除 0否 1是',
  `status` int DEFAULT NULL COMMENT '状态 0-草稿 1-发布',
  `category_id` int DEFAULT NULL COMMENT '类型id',
  `create_at` datetime NOT NULL COMMENT '创建时间',
  `update_at` datetime NOT NULL COMMENT '修改时间',
  PRIMARY KEY (`id`) USING BTREE,
  KEY `index_account_id` (`account_id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=COMPACT;

-- ----------------------------
-- Table structure for mo_auth_code
-- ----------------------------
DROP TABLE IF EXISTS `mo_auth_code`;
CREATE TABLE `mo_auth_code` (
  `id` varchar(33) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `account_id` int NOT NULL,
  `expire_at` bigint NOT NULL,
  `type` int NOT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=COMPACT;

-- ----------------------------
-- Table structure for mo_category
-- ----------------------------
DROP TABLE IF EXISTS `mo_category`;
CREATE TABLE `mo_category` (
  `id` int NOT NULL AUTO_INCREMENT COMMENT '主键id',
  `name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '名称',
  `status` int NOT NULL DEFAULT '0' COMMENT '是否有效；0是1否',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=COMPACT COMMENT='类别表';

-- ----------------------------
-- Table structure for mo_config
-- ----------------------------
DROP TABLE IF EXISTS `mo_config`;
CREATE TABLE `mo_config` (
  `key` varchar(255) NOT NULL COMMENT '配置key',
  `value` text NOT NULL COMMENT 'value',
  `status` int DEFAULT '1' COMMENT '是否可用 0 否 1是',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`key`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='配置';

-- ----------------------------
-- Table structure for mo_login_log
-- ----------------------------
DROP TABLE IF EXISTS `mo_login_log`;
CREATE TABLE `mo_login_log` (
  `account_id` int NOT NULL,
  `login_at` datetime NOT NULL,
  `ip` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  KEY `account_id_index` (`account_id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=COMPACT;

-- ----------------------------
-- Table structure for mo_orders
-- ----------------------------
DROP TABLE IF EXISTS `mo_orders`;
CREATE TABLE `mo_orders` (
  `id` int NOT NULL AUTO_INCREMENT,
  `account_id` int DEFAULT NULL COMMENT 'account.id',
  `goods_id` int DEFAULT NULL COMMENT '产品id，不同的业务的产品id',
  `scene` varchar(128) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '订单场景 比如说不同的业务场景，通过此字段来区分',
  `order_sn` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '订单号',
  `pay_status` int DEFAULT '0' COMMENT '支付状态 0-待支付 1-已支付',
  `status` int NOT NULL DEFAULT '0' COMMENT '订单状态 0-待付款 1-待发货 2-待收货 3-待评价 6-退款 7-订单取消 8-订单删除 9-·订单完成',
  `refund_status` int NOT NULL DEFAULT '0' COMMENT '退款状态 0-默认 1-申请中 2-审核中 3-已退款 4-退款失败 5-其他',
  `created` int DEFAULT NULL COMMENT '创建时间',
  `pay_time` int DEFAULT NULL COMMENT '支付时间',
  `send_time` int DEFAULT NULL COMMENT '发货时间',
  `cancel_time` int DEFAULT NULL COMMENT '取消时间',
  `delete_time` int DEFAULT NULL COMMENT '删除时间',
  PRIMARY KEY (`id`) USING BTREE,
  KEY `order_sn` (`order_sn`) USING BTREE,
  KEY `scene` (`scene`) USING BTREE,
  KEY `account_id` (`account_id`) USING BTREE,
  KEY `goods_id` (`goods_id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC;

-- ----------------------------
-- Table structure for mo_session
-- ----------------------------
DROP TABLE IF EXISTS `mo_session`;
CREATE TABLE `mo_session` (
  `id` varchar(33) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `account_id` int NOT NULL,
  `expire_at` bigint NOT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=COMPACT;

-- ----------------------------
-- Table structure for mo_sys_permission
-- ----------------------------
DROP TABLE IF EXISTS `mo_sys_permission`;
CREATE TABLE `mo_sys_permission` (
  `id` int NOT NULL AUTO_INCREMENT,
  `action_key` varchar(512) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '',
  `controller` varchar(512) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '',
  `remark` varchar(1024) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=COMPACT;

-- ----------------------------
-- Table structure for mo_sys_role
-- ----------------------------
DROP TABLE IF EXISTS `mo_sys_role`;
CREATE TABLE `mo_sys_role` (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(128) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '',
  `create_at` datetime NOT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=COMPACT;

-- ----------------------------
-- Table structure for mo_sys_role_permission
-- ----------------------------
DROP TABLE IF EXISTS `mo_sys_role_permission`;
CREATE TABLE `mo_sys_role_permission` (
  `role_id` int NOT NULL,
  `permission_id` int NOT NULL,
  PRIMARY KEY (`role_id`,`permission_id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=COMPACT;

-- ----------------------------
-- Table structure for mo_sys_user
-- ----------------------------
DROP TABLE IF EXISTS `mo_sys_user`;
CREATE TABLE `mo_sys_user` (
  `id` int NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `username` varchar(150) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '用户名',
  `password` varchar(150) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '密码',
  `salt` varchar(150) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '盐',
  `status` int NOT NULL COMMENT '状态 0-禁用 1-正常',
  `ip` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT 'IP',
  `avatar` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '头像',
  `create_at` datetime NOT NULL COMMENT '注册时间',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE KEY `un_username` (`username`) COMMENT '用户名唯一索引'
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=COMPACT;

-- ----------------------------
-- Table structure for mo_sys_user_role
-- ----------------------------
DROP TABLE IF EXISTS `mo_sys_user_role`;
CREATE TABLE `mo_sys_user_role` (
  `user_id` int NOT NULL,
  `role_id` int NOT NULL,
  PRIMARY KEY (`user_id`,`role_id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=COMPACT;

SET FOREIGN_KEY_CHECKS = 1;
