/*
Navicat MySQL Data Transfer

Source Server         : localhost
Source Server Version : 50627
Source Host           : 127.0.0.1:3306
Source Database       : helpyou

Target Server Type    : MYSQL
Target Server Version : 50627
File Encoding         : 65001

Date: 2016-09-12 16:38:47
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for admin
-- ----------------------------
DROP TABLE IF EXISTS `admin`;
CREATE TABLE `admin` (
  `adminId` int(10) NOT NULL AUTO_INCREMENT COMMENT '管理员编号',
  `adminname` char(12) NOT NULL COMMENT '管理员账户',
  `password` char(12) NOT NULL COMMENT '密码',
  PRIMARY KEY (`adminId`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of admin
-- ----------------------------
INSERT INTO `admin` VALUES ('1', 'root', 'root');

-- ----------------------------
-- Table structure for message
-- ----------------------------
DROP TABLE IF EXISTS `message`;
CREATE TABLE `message` (
  `messageId` int(10) NOT NULL AUTO_INCREMENT COMMENT '留言编号',
  `senderId` int(10) NOT NULL COMMENT '发送者编号',
  `receiverId` int(10) NOT NULL COMMENT '接受者编号',
  `time` datetime NOT NULL COMMENT '发送时间',
  `content` varchar(140) NOT NULL COMMENT '留言内容',
  PRIMARY KEY (`messageId`),
  KEY `send` (`senderId`),
  KEY `receiver` (`receiverId`),
  CONSTRAINT `receiver` FOREIGN KEY (`receiverId`) REFERENCES `user` (`userId`),
  CONSTRAINT `send` FOREIGN KEY (`senderId`) REFERENCES `user` (`userId`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of message
-- ----------------------------
INSERT INTO `message` VALUES ('1', '2', '1', '2016-09-03 18:33:45', '我已经接受了你的任务，但是我希望用支付宝支付');
INSERT INTO `message` VALUES ('2', '1', '2', '2016-09-03 18:40:35', '我知道了，我会用支付宝支付的');
INSERT INTO `message` VALUES ('3', '1', '2', '2016-09-07 10:41:43', '测试留言1111');
INSERT INTO `message` VALUES ('4', '1', '2', '2016-09-07 10:43:00', '测试留言222');
INSERT INTO `message` VALUES ('5', '1', '2', '2016-09-07 10:44:52', '测试留言3333');

-- ----------------------------
-- Table structure for notice
-- ----------------------------
DROP TABLE IF EXISTS `notice`;
CREATE TABLE `notice` (
  `noticeId` int(10) NOT NULL AUTO_INCREMENT COMMENT '系统通知编号',
  `time` datetime NOT NULL COMMENT '发布时间',
  `title` char(20) NOT NULL COMMENT '通知标题',
  `content` varchar(140) NOT NULL DEFAULT '' COMMENT '通知内容',
  PRIMARY KEY (`noticeId`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of notice
-- ----------------------------
INSERT INTO `notice` VALUES ('1', '2016-09-03 16:28:34', '校帮互助平台开始运行', '各位用户你们好，校帮互助平台从今天开始正式投入使用，欢迎各位的加入');
INSERT INTO `notice` VALUES ('2', '2016-09-08 21:05:13', '通知测试', '测试获取未读信息数量的公告');
INSERT INTO `notice` VALUES ('4', '2016-09-12 15:26:10', '测试公告', '测试公告内容111');

-- ----------------------------
-- Table structure for task
-- ----------------------------
DROP TABLE IF EXISTS `task`;
CREATE TABLE `task` (
  `taskId` int(10) NOT NULL AUTO_INCREMENT COMMENT '任务编号',
  `title` varchar(50) NOT NULL COMMENT '任务标题',
  `type` int(1) NOT NULL COMMENT '任务类型',
  `startTime` datetime NOT NULL COMMENT '任务开始时间',
  `endTime` datetime DEFAULT NULL COMMENT '任务结束时间',
  `peopleNum` int(3) NOT NULL DEFAULT '1' COMMENT '任务人数',
  `reward` int(2) NOT NULL COMMENT '任务报酬',
  `state` int(1) NOT NULL DEFAULT '1' COMMENT '任务状态',
  `content` varchar(400) DEFAULT NULL COMMENT '任务内容',
  PRIMARY KEY (`taskId`),
  KEY `title` (`title`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of task
-- ----------------------------
INSERT INTO `task` VALUES ('1', '帮我带一份晚饭', '2', '2016-09-03 18:00:00', '2016-09-03 19:00:00', '1', '2', '4', '请帮我在二三食堂买一份小米姑娘的杏鲍菇培根饭，饭钱用现金支付，宿舍是....');
INSERT INTO `task` VALUES ('2', '买一个修正带', '2', '2016-09-05 12:31:44', '2016-09-06 12:26:41', '1', '2', '4', '请帮我在大教超或者科教文买一个修正带，我用现金支付，宿舍是....');
INSERT INTO `task` VALUES ('3', '测试任务1', '1', '2016-09-06 16:34:06', '2016-09-07 18:37:06', '1', '2', '4', '测试任务详情1111');
INSERT INTO `task` VALUES ('4', '测试任务2', '2', '2016-09-07 09:06:43', '2016-09-08 00:46:25', '2', '3', '4', '测试任务2222修改');
INSERT INTO `task` VALUES ('5', '测试积分任务', '1', '2016-09-09 22:32:17', '2016-09-10 22:32:17', '1', '5', '4', '用于测试积分变动的任务');
INSERT INTO `task` VALUES ('7', '测试积分任务1', '1', '2016-09-12 09:59:24', '2016-09-12 11:19:30', '1', '5', '4', '测试用户积分变动任务1');

-- ----------------------------
-- Table structure for taskaccept
-- ----------------------------
DROP TABLE IF EXISTS `taskaccept`;
CREATE TABLE `taskaccept` (
  `acceptId` int(10) NOT NULL AUTO_INCREMENT COMMENT '接取编号',
  `userId` int(10) NOT NULL COMMENT '用户编号',
  `taskId` int(10) NOT NULL COMMENT '任务编号',
  `taskTitle` varchar(50) NOT NULL COMMENT '任务标题',
  `acceptTime` datetime NOT NULL COMMENT '任务接受时间',
  `state` int(1) NOT NULL DEFAULT '1' COMMENT '完成情况',
  PRIMARY KEY (`acceptId`),
  KEY `userA` (`userId`),
  KEY `taskA` (`taskId`),
  CONSTRAINT `taskA` FOREIGN KEY (`taskId`) REFERENCES `task` (`taskId`),
  CONSTRAINT `userA` FOREIGN KEY (`userId`) REFERENCES `user` (`userId`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of taskaccept
-- ----------------------------
INSERT INTO `taskaccept` VALUES ('1', '2', '1', '帮我带一份晚饭', '2016-09-03 18:32:00', '3');
INSERT INTO `taskaccept` VALUES ('2', '2', '4', '测试任务2', '2016-09-07 22:35:36', '3');
INSERT INTO `taskaccept` VALUES ('3', '2', '5', '测试积分任务', '2016-09-09 23:01:20', '3');
INSERT INTO `taskaccept` VALUES ('4', '1', '7', '测试积分任务1', '2016-09-12 09:59:40', '3');

-- ----------------------------
-- Table structure for taskpublish
-- ----------------------------
DROP TABLE IF EXISTS `taskpublish`;
CREATE TABLE `taskpublish` (
  `publishId` int(10) NOT NULL AUTO_INCREMENT COMMENT '发布编号',
  `userId` int(10) NOT NULL COMMENT '用户编号',
  `taskId` int(10) NOT NULL COMMENT '任务编号',
  PRIMARY KEY (`publishId`),
  KEY `taskP` (`taskId`),
  KEY `userP` (`userId`),
  CONSTRAINT `taskP` FOREIGN KEY (`taskId`) REFERENCES `task` (`taskId`),
  CONSTRAINT `userP` FOREIGN KEY (`userId`) REFERENCES `user` (`userId`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of taskpublish
-- ----------------------------
INSERT INTO `taskpublish` VALUES ('1', '1', '1');
INSERT INTO `taskpublish` VALUES ('2', '1', '3');
INSERT INTO `taskpublish` VALUES ('3', '1', '4');
INSERT INTO `taskpublish` VALUES ('4', '2', '2');
INSERT INTO `taskpublish` VALUES ('5', '1', '5');
INSERT INTO `taskpublish` VALUES ('6', '2', '7');

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `userId` int(10) NOT NULL AUTO_INCREMENT COMMENT '用户编号',
  `username` char(12) NOT NULL COMMENT '用户名',
  `password` char(12) NOT NULL COMMENT '用户密码',
  `nickname` char(10) NOT NULL COMMENT '昵称',
  `sex` char(2) DEFAULT '' COMMENT '性别',
  `age` int(2) DEFAULT NULL COMMENT '年龄',
  `state` int(1) DEFAULT '1' COMMENT '用户状态',
  `lastLoginTime` datetime DEFAULT NULL COMMENT '上次登录时间',
  `point` int(10) DEFAULT '20' COMMENT '用户积分',
  PRIMARY KEY (`userId`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES ('1', 'zsk', '123456', '张世凯', '男', '20', '1', '2016-09-12 10:04:16', '80');
INSERT INTO `user` VALUES ('2', 'fh', '456789', '方浩', '男', '20', '1', '2016-09-12 11:19:21', '35');
INSERT INTO `user` VALUES ('4', 'testuesr1', '123456', '测试用户11', '男', '20', '1', '2016-09-08 10:26:54', '20');
