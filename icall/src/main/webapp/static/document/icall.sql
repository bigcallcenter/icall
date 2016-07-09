/*
Navicat MySQL Data Transfer

Source Server         : icall
Source Server Version : 50713
Source Host           : localhost:3306
Source Database       : icall

Target Server Type    : MYSQL
Target Server Version : 50713
File Encoding         : 65001

Date: 2016-07-05 18:14:49
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for phone_his
-- ----------------------------
DROP TABLE IF EXISTS `phone_his`;
CREATE TABLE `phone_his` (
  `id` varchar(20) NOT NULL COMMENT '主键',
  `call_time` datetime DEFAULT NULL COMMENT '呼叫时间',
  `establish_time` datetime DEFAULT NULL COMMENT '接通时间',
  `release_time` datetime DEFAULT NULL COMMENT '结束时间',
  `call_type` varchar(20) DEFAULT NULL COMMENT '呼叫类型，in为呼入，out为呼出',
  `agent_id` datetime DEFAULT NULL COMMENT '坐席工号',
  `customer_id` varchar(20) DEFAULT NULL COMMENT '客户ID',
  `phone` varchar(20) DEFAULT NULL COMMENT '号码',
  `qc_score` int(3) DEFAULT NULL COMMENT '得分',
  `org_id` varchar(20) DEFAULT NULL COMMENT '接听坐席所属机构',
  `create_by` varchar(20) DEFAULT NULL COMMENT '接听坐席user_id',
  `create_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for t_customer
-- ----------------------------
DROP TABLE IF EXISTS `t_customer`;
CREATE TABLE `t_customer` (
  `id` varchar(20) NOT NULL COMMENT '主键',
  `phone` varchar(11) DEFAULT NULL COMMENT '号码',
  `cu_name` varchar(20) DEFAULT NULL COMMENT '客户姓名',
  `address` varchar(200) DEFAULT NULL COMMENT '地址',
  `user_id` varchar(20) DEFAULT NULL COMMENT '创建人',
  `org_id` varchar(20) DEFAULT NULL COMMENT '岗位ID',
  `create_date` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for t_ob_task
-- ----------------------------
DROP TABLE IF EXISTS `t_ob_task`;
CREATE TABLE `t_ob_task` (
  `id` varchar(20) NOT NULL,
  `task_name` varchar(200) DEFAULT NULL COMMENT '任务名称',
  `start_time` datetime DEFAULT NULL COMMENT '开始时间',
  `end_time` datetime DEFAULT NULL COMMENT '结束时间',
  `is_open` char(1) DEFAULT '1' COMMENT '是否启用，0为未启用，1为启用',
  `script` varchar(300) DEFAULT NULL COMMENT '话术说明',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `create_by` varchar(20) DEFAULT NULL COMMENT '创建人',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for t_sys_menu
-- ----------------------------
DROP TABLE IF EXISTS `t_sys_menu`;
CREATE TABLE `t_sys_menu` (
  `id` varchar(20) NOT NULL COMMENT '主键',
  `parent_menu` varchar(20) DEFAULT NULL COMMENT '父菜单ID',
  `menu_name` varchar(200) DEFAULT NULL COMMENT '菜单名称',
  `menu_type` char(1) DEFAULT NULL COMMENT '菜单类型',
  `url` varchar(400) DEFAULT NULL COMMENT '菜单地址',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='菜单表';

-- ----------------------------
-- Table structure for t_sys_org
-- ----------------------------
DROP TABLE IF EXISTS `t_sys_org`;
CREATE TABLE `t_sys_org` (
  `id` varchar(20) NOT NULL COMMENT '主键',
  `org_name` varchar(200) DEFAULT NULL COMMENT '组织名称',
  `parent_org` varchar(20) DEFAULT NULL COMMENT '上级组织ID',
  `is_position` char(1) DEFAULT '0' COMMENT '是否为岗位，1为岗位，0位部门',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for t_sys_org_data_auth
-- ----------------------------
DROP TABLE IF EXISTS `t_sys_org_data_auth`;
CREATE TABLE `t_sys_org_data_auth` (
  `org_id` varchar(20) NOT NULL COMMENT '岗位ID',
  `org_data_id` varchar(20) NOT NULL COMMENT '生产数据的部门ID'
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for t_sys_org_menu
-- ----------------------------
DROP TABLE IF EXISTS `t_sys_org_menu`;
CREATE TABLE `t_sys_org_menu` (
  `org_id` varchar(20) NOT NULL COMMENT '组织机构ID',
  `menu_id` varchar(20) NOT NULL COMMENT '菜单ID'
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for t_sys_seat
-- ----------------------------
DROP TABLE IF EXISTS `t_sys_seat`;
CREATE TABLE `t_sys_seat` (
  `id` varchar(20) NOT NULL COMMENT '主键',
  `agent_id` varchar(20) DEFAULT NULL COMMENT '坐席工号',
  `agent_pwd` varchar(200) DEFAULT NULL COMMENT '坐席密码',
  `cti_url` varchar(200) DEFAULT NULL COMMENT 'CTI服务地址',
  `cti_port` varchar(20) DEFAULT NULL COMMENT 'CTI服务器端口',
  `create_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for t_sys_skill
-- ----------------------------
DROP TABLE IF EXISTS `t_sys_skill`;
CREATE TABLE `t_sys_skill` (
  `id` varchar(20) NOT NULL COMMENT '技能ID',
  `skill_name` varchar(200) DEFAULT NULL COMMENT '技能名称',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for t_sys_skill_seat
-- ----------------------------
DROP TABLE IF EXISTS `t_sys_skill_seat`;
CREATE TABLE `t_sys_skill_seat` (
  `skill_id` varchar(20) NOT NULL COMMENT '技能ID',
  `agent_id` varchar(20) NOT NULL COMMENT '坐席工号'
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for t_sys_user
-- ----------------------------
DROP TABLE IF EXISTS `t_sys_user`;
CREATE TABLE `t_sys_user` (
  `id` varchar(20) NOT NULL,
  `loginName` varchar(20) NOT NULL COMMENT '登陆名',
  `password` varchar(50) DEFAULT NULL,
  `userName` varchar(20) DEFAULT NULL COMMENT '用户名称',
  `is_locked` char(1) DEFAULT '0' COMMENT '是否锁定，0未锁定，1锁定',
  `agent_id` varchar(20) DEFAULT NULL COMMENT '坐席工号',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `create_by` varchar(20) NOT NULL COMMENT '创建人',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for t_sys_user_data_atuh
-- ----------------------------
DROP TABLE IF EXISTS `t_sys_user_data_atuh`;
CREATE TABLE `t_sys_user_data_atuh` (
  `user_id` varchar(20) NOT NULL COMMENT '用户ID',
  `org_data_id` varchar(20) NOT NULL COMMENT '生产数据的部门ID'
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for t_sys_user_menu
-- ----------------------------
DROP TABLE IF EXISTS `t_sys_user_menu`;
CREATE TABLE `t_sys_user_menu` (
  `user_id` varchar(20) NOT NULL COMMENT '用户ID',
  `menu_id` varchar(20) NOT NULL COMMENT '菜单ID'
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for t_sys_user_org
-- ----------------------------
DROP TABLE IF EXISTS `t_sys_user_org`;
CREATE TABLE `t_sys_user_org` (
  `user_id` varchar(20) NOT NULL COMMENT '用户ID',
  `org_id` varchar(20) NOT NULL COMMENT '组织机构ID'
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
