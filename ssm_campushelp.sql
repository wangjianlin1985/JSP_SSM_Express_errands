/*
Navicat MySQL Data Transfer

Source Server         : 127.0.0.1
Source Server Version : 50505
Source Host           : 127.0.0.1:3306
Source Database       : ssm_campushelp

Target Server Type    : MYSQL
Target Server Version : 50505
File Encoding         : 65001

Date: 2020-01-08 19:42:30
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `admin`
-- ----------------------------
DROP TABLE IF EXISTS `admin`;
CREATE TABLE `admin` (
  `aid` int(6) NOT NULL AUTO_INCREMENT,
  `account` varchar(255) NOT NULL,
  `password` varchar(255) NOT NULL DEFAULT '123456',
  `name` varchar(255) NOT NULL DEFAULT 'name',
  `addtime` datetime(6) NOT NULL,
  `state` int(2) NOT NULL DEFAULT 0,
  PRIMARY KEY (`aid`)
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC;

-- ----------------------------
-- Records of admin
-- ----------------------------
INSERT INTO `admin` VALUES ('1', 'admin', '123456', 'admin', '2019-09-06 17:22:31.000000', '0');
INSERT INTO `admin` VALUES ('11', 'user1', '123456', 'user1', '2019-09-06 17:39:51.000000', '0');

-- ----------------------------
-- Table structure for `school`
-- ----------------------------
DROP TABLE IF EXISTS `school`;
CREATE TABLE `school` (
  `schoolid` int(5) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) NOT NULL DEFAULT 'schoolname',
  `addtime` datetime(6) NOT NULL,
  `state` int(2) NOT NULL DEFAULT 0,
  PRIMARY KEY (`schoolid`)
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC;

-- ----------------------------
-- Records of school
-- ----------------------------
INSERT INTO `school` VALUES ('9', '北京大学', '2019-09-06 17:23:37.000000', '0');
INSERT INTO `school` VALUES ('10', '清华大学', '2019-09-06 17:23:55.000000', '0');
INSERT INTO `school` VALUES ('11', '哈佛大学', '2019-09-06 17:24:09.000000', '0');
INSERT INTO `school` VALUES ('12', '蓝翔高级技工学校', '2019-09-06 17:24:22.000000', '0');

-- ----------------------------
-- Table structure for `task`
-- ----------------------------
DROP TABLE IF EXISTS `task`;
CREATE TABLE `task` (
  `taskid` int(8) NOT NULL AUTO_INCREMENT,
  `publish_user_id` varchar(255) NOT NULL COMMENT '发布用户学号',
  `publish_user_name` varchar(255) NOT NULL,
  `publish_school_id` int(6) NOT NULL COMMENT '发布用户学校ID',
  `accept_user_id` int(11) NOT NULL DEFAULT 0,
  `reward` double(30,0) NOT NULL DEFAULT 0 COMMENT '任务奖励',
  `addtime` datetime(6) NOT NULL COMMENT '发布时间',
  `endtime` datetime NOT NULL DEFAULT '0000-00-00 00:00:00' ON UPDATE current_timestamp(),
  `taskname` varchar(255) NOT NULL COMMENT '任务名称',
  `taskcontext` varchar(255) NOT NULL COMMENT '任务描述',
  `state` int(2) NOT NULL DEFAULT 0 COMMENT '任务状态',
  PRIMARY KEY (`taskid`)
) ENGINE=InnoDB AUTO_INCREMENT=39 DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC;

-- ----------------------------
-- Records of task
-- ----------------------------
INSERT INTO `task` VALUES ('38', '7', '张晓彤', '9', '0', '1', '2019-09-06 17:36:54.000000', '2019-09-06 17:36:54', '帮忙拿外卖', '在学校西门口，取餐号1234，我宿舍在23号楼321', '0');

-- ----------------------------
-- Table structure for `user`
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `stuid` int(11) NOT NULL AUTO_INCREMENT,
  `studentid` varchar(20) NOT NULL DEFAULT '' COMMENT '学号',
  `password` varchar(255) NOT NULL COMMENT '密码',
  `schoolid` int(6) NOT NULL,
  `sex` int(2) NOT NULL DEFAULT 0,
  `name` varchar(255) NOT NULL DEFAULT 'name',
  `registertime` datetime(6) NOT NULL COMMENT '注册时间',
  `money` double(20,0) NOT NULL DEFAULT 0,
  `state` int(2) NOT NULL DEFAULT 0,
  PRIMARY KEY (`stuid`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES ('7', '141401', '123456', '9', '0', '张晓彤', '2019-09-06 17:31:47.000000', '1004', '0');
INSERT INTO `user` VALUES ('8', '141402', '123456', '9', '0', '张晓彤搬砖的', '2019-09-06 17:47:49.000000', '0', '0');
