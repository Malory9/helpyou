--  存在helpyou数据库时将其删除重新创建 
DROP DATABASE IF EXISTS helpyou;
CREATE DATABASE helpyou;
USE helpyou;

--  使用UTF-8编码
/*
	等于使用以下命令
	SET character_set_client = utf8;
	SET character_set_results = utf8;
	SET character_set_connection = utf8;
*/
SET NAMES UTF8;

--  取消外键约束
SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for admin
-- ----------------------------
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
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of message
-- ----------------------------
INSERT INTO `message` VALUES ('1', '2', '1', '2016-09-03 18:33:45', '我已经接受了你的任务，但是我希望用支付宝支付');

-- ----------------------------
-- Table structure for notice
-- ----------------------------
CREATE TABLE `notice` (
  `noticeId` int(10) NOT NULL COMMENT '系统通知编号',
  `time` datetime NOT NULL COMMENT '发布时间',
  `title` char(20) NOT NULL COMMENT '通知标题',
  `content` varchar(140) NOT NULL DEFAULT '' COMMENT '通知内容',
  PRIMARY KEY (`noticeId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of notice
-- ----------------------------
INSERT INTO `notice` VALUES ('1', '2016-09-03 16:28:34', '校帮互助平台开始运行', '各位用户你们好，校帮互助平台从今天开始正式投入使用，欢迎各位的加入');

-- ----------------------------
-- Table structure for task
-- ----------------------------
CREATE TABLE `task` (
  `taskId` int(10) NOT NULL AUTO_INCREMENT COMMENT '任务编号',
  `title` varchar(50) NOT NULL COMMENT '任务标题',
  `type` int(1) NOT NULL COMMENT '任务类型',
  `startTime` datetime NOT NULL COMMENT '任务开始时间',
  `endTime` datetime NOT NULL COMMENT '任务结束时间',
  `peopleNum` int(3) NOT NULL DEFAULT '1' COMMENT '任务人数',
  `reward` int(2) NOT NULL COMMENT '任务报酬',
  `state` int(1) NOT NULL DEFAULT '1' COMMENT '任务状态',
  `content` varchar(400) DEFAULT NULL COMMENT '任务内容',
  PRIMARY KEY (`taskId`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of task
-- ----------------------------
INSERT INTO `task` VALUES ('1', '帮我带一份晚饭', '2', '2016-09-03 18:00:00', '2016-09-03 19:00:00', '1', '2', '1', '请帮我在二三食堂买一份小米姑娘的杏鲍菇培根饭，饭钱用现金支付');

-- ----------------------------
-- Table structure for task_accept
-- ----------------------------
CREATE TABLE `task_accept` (
  `userId` int(10) NOT NULL COMMENT '用户编号',
  `taskId` int(10) NOT NULL COMMENT '任务编号',
  `acceptTime` datetime NOT NULL COMMENT '任务接受时间',
  KEY `userA` (`userId`),
  KEY `taskA` (`taskId`),
  CONSTRAINT `taskA` FOREIGN KEY (`taskId`) REFERENCES `task` (`taskId`),
  CONSTRAINT `userA` FOREIGN KEY (`userId`) REFERENCES `user` (`userId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of task_accept
-- ----------------------------
INSERT INTO `task_accept` VALUES ('2', '1', '2016-09-03 18:32:00');

-- ----------------------------
-- Table structure for task_publish
-- ----------------------------
CREATE TABLE `task_publish` (
  `userId` int(10) NOT NULL COMMENT '用户编号',
  `taskId` int(10) NOT NULL COMMENT '任务编号',
  KEY `taskP` (`taskId`),
  KEY `userP` (`userId`),
  CONSTRAINT `taskP` FOREIGN KEY (`taskId`) REFERENCES `task` (`taskId`),
  CONSTRAINT `userP` FOREIGN KEY (`userId`) REFERENCES `user` (`userId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of task_publish
-- ----------------------------
INSERT INTO `task_publish` VALUES ('1', '1');

-- ----------------------------
-- Table structure for user
-- ----------------------------
CREATE TABLE `user` (
  `userId` int(10) NOT NULL AUTO_INCREMENT COMMENT '用户编号',
  `username` char(12) NOT NULL COMMENT '用户名',
  `password` char(12) NOT NULL COMMENT '用户密码',
  `nickname` char(10) NOT NULL COMMENT '昵称',
  `sex` char(1) DEFAULT NULL COMMENT '性别',
  `age` int(2) DEFAULT NULL COMMENT '年龄',
  `state` int(1) DEFAULT '1' COMMENT '用户状态',
  `lastLoginTime` datetime DEFAULT NULL COMMENT '上次登录时间',
  `point` int(10) DEFAULT '0' COMMENT '用户积分',
  PRIMARY KEY (`userId`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES ('1', 'zsk', '123456', '张世凯', '男', '20', '1', '2016-09-03 16:22:38', '100');
INSERT INTO `user` VALUES ('2', 'fh', '456789', '方浩', '男', '20', '1', '2016-09-03 18:30:00', '10');
