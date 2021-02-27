/*
 Navicat Premium Data Transfer

 Source Server         : 本机
 Source Server Type    : MySQL
 Source Server Version : 50731
 Source Host           : localhost:3306
 Source Schema         : morning_db

 Target Server Type    : MySQL
 Target Server Version : 50731
 File Encoding         : 65001

 Date: 27/02/2021 18:13:10
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for mo_account
-- ----------------------------
DROP TABLE IF EXISTS `mo_account`;
CREATE TABLE `mo_account`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `nickname` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `username` varchar(150) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `password` varchar(150) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `salt` varchar(150) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `status` int(11) NOT NULL,
  `create_at` datetime(0) NOT NULL,
  `ip` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `avatar` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `like_count` int(11) NOT NULL DEFAULT 0 COMMENT '被赞次数',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Table structure for mo_account_role
-- ----------------------------
DROP TABLE IF EXISTS `mo_account_role`;
CREATE TABLE `mo_account_role`  (
  `account_id` int(11) NOT NULL,
  `role_id` int(11) NOT NULL,
  PRIMARY KEY (`account_id`, `role_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Table structure for mo_article
-- ----------------------------
DROP TABLE IF EXISTS `mo_article`;
CREATE TABLE `mo_article`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键id',
  `account_id` int(11) NULL DEFAULT NULL COMMENT '博客主id',
  `title` varchar(150) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '标题',
  `content` longtext CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '内容',
  `marked_content` longtext CHARACTER SET utf8 COLLATE utf8_general_ci NULL COMMENT '待解析内容',
  `read_count` int(11) NOT NULL DEFAULT 0 COMMENT '点击次数',
  `is_delete` int(11) NOT NULL DEFAULT 0 COMMENT '是否删除 0否1是',
  `category_id` int(11) NULL DEFAULT NULL COMMENT '分类id',
  `status` int(11) NULL DEFAULT 0 COMMENT '状态 0 草稿 1发布',
  `create_time` datetime(0) NOT NULL COMMENT '创建时间',
  `update_time` datetime(0) NOT NULL COMMENT '修改时间',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `title`(`title`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Table structure for mo_article_category
-- ----------------------------
DROP TABLE IF EXISTS `mo_article_category`;
CREATE TABLE `mo_article_category`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键id',
  `name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '名称',
  `p_id` int(11) NULL DEFAULT NULL COMMENT '父id',
  `status` int(4) NOT NULL DEFAULT 0 COMMENT '是否有效；0是1否',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 10 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '类别表' ROW_FORMAT = Compact;

-- ----------------------------
-- Table structure for mo_auth_code
-- ----------------------------
DROP TABLE IF EXISTS `mo_auth_code`;
CREATE TABLE `mo_auth_code`  (
  `id` varchar(33) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `account_id` int(11) NOT NULL,
  `expire_at` bigint(20) NOT NULL,
  `type` int(20) NOT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Table structure for mo_login_log
-- ----------------------------
DROP TABLE IF EXISTS `mo_login_log`;
CREATE TABLE `mo_login_log`  (
  `account_id` int(11) NOT NULL,
  `login_at` datetime(0) NOT NULL,
  `ip` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  INDEX `account_id_index`(`account_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Table structure for mo_orders
-- ----------------------------
DROP TABLE IF EXISTS `mo_orders`;
CREATE TABLE `mo_orders`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `account_id` int(11) NULL DEFAULT NULL COMMENT 'account.id',
  `goods_id` int(11) NULL DEFAULT NULL COMMENT '产品id，不同的业务的产品id',
  `scene` varchar(128) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '订单场景 比如说不同的业务场景，通过此字段来区分',
  `order_sn` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '订单号',
  `pay_status` int(4) NULL DEFAULT 0 COMMENT '支付状态 0-待支付 1-已支付',
  `status` int(4) NOT NULL DEFAULT 0 COMMENT '订单状态 0-待付款 1-待发货 2-待收货 3-待评价 7-订单取消 9-·订单完成',
  `refund_status` int(4) NOT NULL DEFAULT 0 COMMENT '退款状态 0-默认 1-申请中 2-审核中 3-已退款 4-退款失败 5-其他',
  `created` int(11) NULL DEFAULT NULL COMMENT '创建时间',
  `pay_time` int(11) NULL DEFAULT NULL COMMENT '支付时间',
  `send_time` int(11) NULL DEFAULT NULL COMMENT '发货时间',
  `cancel_time` int(11) NULL DEFAULT NULL COMMENT '取消时间',
  `delete_time` int(11) NULL DEFAULT NULL COMMENT '删除时间',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `order_sn`(`order_sn`) USING BTREE,
  INDEX `scene`(`scene`) USING BTREE,
  INDEX `account_id`(`account_id`) USING BTREE,
  INDEX `goods_id`(`goods_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 2 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for mo_permission
-- ----------------------------
DROP TABLE IF EXISTS `mo_permission`;
CREATE TABLE `mo_permission`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `action_key` varchar(512) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '',
  `controller` varchar(512) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '',
  `remark` varchar(1024) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 111 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Table structure for mo_role
-- ----------------------------
DROP TABLE IF EXISTS `mo_role`;
CREATE TABLE `mo_role`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(128) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '',
  `create_at` datetime(0) NOT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 10 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Table structure for mo_role_permission
-- ----------------------------
DROP TABLE IF EXISTS `mo_role_permission`;
CREATE TABLE `mo_role_permission`  (
  `role_id` int(11) NOT NULL,
  `permission_id` int(11) NOT NULL,
  PRIMARY KEY (`role_id`, `permission_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Table structure for mo_session
-- ----------------------------
DROP TABLE IF EXISTS `mo_session`;
CREATE TABLE `mo_session`  (
  `id` varchar(33) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `account_id` int(11) NOT NULL,
  `expire_at` bigint(20) NOT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

SET FOREIGN_KEY_CHECKS = 1;
