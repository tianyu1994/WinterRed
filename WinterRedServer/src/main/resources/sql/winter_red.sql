/*
Navicat MySQL Data Transfer

Source Server         : WinterRed
Source Server Version : 50729
Source Host           : 47.113.113.225:3306
Source Database       : winter_red

Target Server Type    : MYSQL
Target Server Version : 50729
File Encoding         : 65001

Date: 2020-07-27 18:46:40
*/
use winter_red;
SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for ask_user
-- ----------------------------
-- DROP TABLE IF EXISTS `ask_user`;
CREATE TABLE `ask_user` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `email` varchar(45) CHARACTER SET latin1 NOT NULL COMMENT '邮箱',
  `update_on` datetime DEFAULT NULL COMMENT '更新时间',
  `update_by` varchar(50) DEFAULT NULL COMMENT '更新人',
  PRIMARY KEY (`id`),
  UNIQUE KEY `email_UNIQUE` (`email`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Table structure for check_man
-- ----------------------------
-- DROP TABLE IF EXISTS `check_man`;
CREATE TABLE `check_man` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `organization_id` varchar(11) NOT NULL COMMENT '机构id',
  `checkman_name` varchar(50) NOT NULL COMMENT '真实姓名',
  `sex` varchar(10) DEFAULT NULL COMMENT '性别',
  `email` varchar(50) NOT NULL COMMENT '邮箱',
  `user_name` varchar(50) DEFAULT NULL COMMENT '用户名',
  `belong_area` varchar(200) NOT NULL COMMENT '所属领域',
  `update_on` datetime DEFAULT NULL COMMENT '更新时间',
  `update_by` varchar(50) DEFAULT NULL COMMENT '更新人',
  PRIMARY KEY (`id`),
  UNIQUE KEY `uniqu` (`organization_id`,`email`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COMMENT='核查人员表';

-- ----------------------------
-- Table structure for check_plat
-- ----------------------------
-- DROP TABLE IF EXISTS `check_plat`;
CREATE TABLE `check_plat` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `organization_name` varchar(200) NOT NULL COMMENT '机构名称',
  `belong_area` varchar(200) NOT NULL COMMENT '所属地区',
  `update_on` datetime DEFAULT NULL COMMENT '更新时间',
  `update_by` varchar(50) DEFAULT NULL COMMENT '更新人',
  PRIMARY KEY (`id`),
  UNIQUE KEY `unique` (`organization_name`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COMMENT='核查平台表';

-- ----------------------------
-- Table structure for checkman_field_relation
-- ----------------------------
-- DROP TABLE IF EXISTS `checkman_field_relation`;
CREATE TABLE `checkman_field_relation` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `check_man_id` int(11) NOT NULL COMMENT '核查人员id',
  `professional_field_id` int(11) NOT NULL COMMENT '领域id',
  `update_on` datetime DEFAULT NULL COMMENT '更新时间',
  `update_by` varchar(50) DEFAULT NULL COMMENT '更新人',
  PRIMARY KEY (`id`),
  UNIQUE KEY `unique` (`check_man_id`,`professional_field_id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COMMENT='核查人员领域关系表';

-- ----------------------------
-- Table structure for professional_field
-- ----------------------------
-- DROP TABLE IF EXISTS `professional_field`;
CREATE TABLE `professional_field` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `field_name` varchar(200) NOT NULL COMMENT '专业领域名',
  `update_on` datetime DEFAULT NULL COMMENT '更新时间',
  `update_by` varchar(50) DEFAULT NULL COMMENT '更新人',
  PRIMARY KEY (`id`),
  UNIQUE KEY `unique` (`field_name`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COMMENT='领域表';

-- ----------------------------
-- Table structure for rumor_info
-- ----------------------------
-- DROP TABLE IF EXISTS `rumor_info`;
CREATE TABLE `rumor_info` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `title` varchar(200) NOT NULL COMMENT '标题',
  `abstract_info` varchar(500) NOT NULL COMMENT '摘要',
  `quoted_content` mediumtext NOT NULL COMMENT '引用的内容',
  `check_point` varchar(500) NOT NULL COMMENT '查证要点',
  `status` varchar(10) NOT NULL COMMENT '辟谣状态',
  `create_on` datetime NOT NULL COMMENT '录入时间',
  `update_on` datetime NOT NULL COMMENT '更新时间',
  `check_man_id` int(11) NOT NULL COMMENT '核查人员id',
  `professional_field_id` int(11) NOT NULL COMMENT '领域id',
  `ask_user_id` int(11) DEFAULT NULL COMMENT '提问用户id',
  `source` varchar(255) DEFAULT NULL COMMENT '信息来源渠道',
  `update_by` varchar(50) DEFAULT NULL COMMENT '更新人',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=27 DEFAULT CHARSET=utf8mb4 COMMENT='谣言信息表';

-- ----------------------------
-- Table structure for subscribe_user
-- ----------------------------
-- DROP TABLE IF EXISTS `subscribe_user`;
CREATE TABLE `subscribe_user` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `email` varchar(45) CHARACTER SET latin1 NOT NULL COMMENT '邮箱',
  `update_on` datetime DEFAULT NULL COMMENT '更新时间',
  `update_by` varchar(50) DEFAULT NULL COMMENT '更新人',
  PRIMARY KEY (`id`),
  UNIQUE KEY `email_UNIQUE` (`email`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Table structure for user_field_relation
-- ----------------------------
-- DROP TABLE IF EXISTS `user_field_relation`;
CREATE TABLE `user_field_relation` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `user_id` int(11) NOT NULL COMMENT '用户id',
  `professional_field_id` int(11) NOT NULL COMMENT '领域id',
  `update_on` datetime DEFAULT NULL COMMENT '更新时间',
  `update_by` varchar(50) DEFAULT NULL COMMENT '更新人',
  PRIMARY KEY (`id`),
  UNIQUE KEY `unique` (`user_id`,`professional_field_id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4 COMMENT='订阅用户领域关系表';
