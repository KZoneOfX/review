/*
Navicat MySQL Data Transfer

Source Server         : localhost
Source Server Version : 50710
Source Host           : localhost:53306
Source Database       : review

Target Server Type    : MYSQL
Target Server Version : 50710
File Encoding         : 65001

Date: 2016-02-23 10:24:53
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for i_config
-- ----------------------------
DROP TABLE IF EXISTS `i_config`;
CREATE TABLE `i_config` (
  `id` bigint(8) NOT NULL,
  `config_name` varchar(32) DEFAULT NULL COMMENT '属性名称',
  `config_sign` varchar(32) DEFAULT NULL COMMENT '属性  在系统中显示 标签',
  `description` varchar(512) DEFAULT NULL COMMENT '对于属性的描述',
  `state` int(2) DEFAULT NULL COMMENT '属性状态',
  `config_type` varchar(32) DEFAULT NULL COMMENT '系统属性的类型',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='系统属性相关';

-- ----------------------------
-- Table structure for i_paper
-- ----------------------------
DROP TABLE IF EXISTS `i_paper`;
CREATE TABLE `i_paper` (
  `id` bigint(16) NOT NULL AUTO_INCREMENT,
  `paper_name` varchar(128) DEFAULT NULL COMMENT '论文 名称',
  `paper_path` varchar(512) DEFAULT NULL COMMENT '论文文件 路径',
  `paper_comment_path` varchar(512) DEFAULT NULL COMMENT '论文评论文件 路径',
  `paper_result` varchar(128) DEFAULT NULL COMMENT '论文结果',
  `paper_score` varchar(512) DEFAULT NULL COMMENT '论文成绩',
  `paper_create_time` datetime DEFAULT NULL COMMENT '论文提交时间',
  `paper_submit_person_id` bigint(16) NOT NULL COMMENT '论文提交人id',
  `std_id` bigint(16) NOT NULL COMMENT '学生ID',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='系统论文 表';

-- ----------------------------
-- Table structure for i_permission
-- ----------------------------
DROP TABLE IF EXISTS `i_permission`;
CREATE TABLE `i_permission` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT '权限id',
  `permission_name` varchar(32) DEFAULT NULL COMMENT '权限名',
  `permission_sign` varchar(128) DEFAULT NULL COMMENT '权限标识,程序中判断使用,如"user:create"',
  `description` varchar(256) DEFAULT NULL COMMENT '权限描述,UI界面显示使用',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8 COMMENT='权限表';

-- ----------------------------
-- Table structure for i_role
-- ----------------------------
DROP TABLE IF EXISTS `i_role`;
CREATE TABLE `i_role` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT '角色id',
  `role_name` varchar(32) DEFAULT NULL COMMENT '角色名',
  `role_sign` varchar(128) DEFAULT NULL COMMENT '角色标识,程序中判断使用,如"admin"',
  `description` varchar(256) DEFAULT NULL COMMENT '角色描述,UI界面显示使用',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8 COMMENT='角色表';

-- ----------------------------
-- Table structure for i_role_permission
-- ----------------------------
DROP TABLE IF EXISTS `i_role_permission`;
CREATE TABLE `i_role_permission` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT '表id',
  `role_id` bigint(20) unsigned DEFAULT NULL COMMENT '角色id',
  `permission_id` bigint(20) unsigned DEFAULT NULL COMMENT '权限id',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8 COMMENT='角色与权限关联表';

-- ----------------------------
-- Table structure for i_tch_paper
-- ----------------------------
DROP TABLE IF EXISTS `i_tch_paper`;
CREATE TABLE `i_tch_paper` (
  `id` bigint(16) NOT NULL AUTO_INCREMENT,
  `tch_id` bigint(16) DEFAULT NULL,
  `paper_id` bigint(16) DEFAULT NULL,
  `tch_paper_status` varchar(128) CHARACTER SET gbk DEFAULT NULL,
  `tch_paper_result` varchar(128) CHARACTER SET gbk DEFAULT NULL,
  `tch_paper_comment` text CHARACTER SET gbk,
  `tch_review_time` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for i_user
-- ----------------------------
DROP TABLE IF EXISTS `i_user`;
CREATE TABLE `i_user` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT '用户id',
  `username` varchar(50) DEFAULT NULL COMMENT '用户名  学生学号、教学工号、管理员工号',
  `password` char(64) DEFAULT NULL COMMENT '密码',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8 COMMENT='用户表';

-- ----------------------------
-- Table structure for i_user_info
-- ----------------------------
DROP TABLE IF EXISTS `i_user_info`;
CREATE TABLE `i_user_info` (
  `id` bigint(16) unsigned NOT NULL AUTO_INCREMENT COMMENT '用户id',
  `real_name` varchar(32) DEFAULT NULL COMMENT '真实姓名 学生姓名、教师姓名、管理员名称',
  `place` varchar(16) DEFAULT NULL COMMENT '所属教学点',
  `email` varchar(32) DEFAULT NULL COMMENT '邮件',
  `phone` varchar(16) DEFAULT NULL COMMENT '手机号',
  `state` int(32) DEFAULT NULL COMMENT '状态   0账号已被删除，1账号正在使用',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `create_person_id` bigint(16) NOT NULL COMMENT '创建账号  管理员的ID',
  `last_login_time` datetime DEFAULT NULL COMMENT '最后登陆时间',
  `stu_review_no` varchar(16) DEFAULT NULL COMMENT '学生盲评编号',
  `stu_paper_status` int(1) DEFAULT NULL COMMENT '学生论文 状态 0未提交，1已提交第一次提交，2已提交第二次提交',
  `stu_paper_submit_time_start` datetime DEFAULT NULL COMMENT '学生 论文提交时间',
  `stu_paper_submit_time_over` datetime DEFAULT NULL COMMENT '学生 论文提交结束时间',
  `stu_tch_id` bigint(16) DEFAULT NULL COMMENT '学生的 指导教师Id',
  `std_admin_id` bigint(16) DEFAULT NULL COMMENT '学生的 管理员Id',
  `tch_job_title` varchar(16) DEFAULT NULL COMMENT '教师职称',
  `tch_department` varchar(32) DEFAULT NULL COMMENT '教师 所属研究所',
  `tch_office_phone` varchar(16) DEFAULT NULL COMMENT '教室 办公室电话',
  `tch_work_place` varchar(32) DEFAULT NULL COMMENT '教师 所属 教学点',
  `tch_paper_review_time_start` datetime DEFAULT NULL COMMENT '教师 评审论文开始时间',
  `tch_paper_review_time_over` datetime DEFAULT NULL COMMENT '教师 评审论文开始时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC COMMENT='用户表';

-- ----------------------------
-- Table structure for i_user_role
-- ----------------------------
DROP TABLE IF EXISTS `i_user_role`;
CREATE TABLE `i_user_role` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT '表id',
  `user_id` bigint(20) unsigned DEFAULT NULL COMMENT '用户id',
  `role_id` bigint(20) unsigned DEFAULT NULL COMMENT '角色id',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8 COMMENT='用户与角色关联表';
