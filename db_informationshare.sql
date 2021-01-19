/*
SQLyog Ultimate v12.09 (64 bit)
MySQL - 5.6.50-log : Database - db_informationshare
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`db_informationshare` /*!40100 DEFAULT CHARACTER SET utf8 */;

USE `db_informationshare`;

/*Table structure for table `t_article` */

DROP TABLE IF EXISTS `t_article`;

CREATE TABLE `t_article` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '编号',
  `name` varchar(50) CHARACTER SET utf8mb4 DEFAULT NULL COMMENT '文章名称',
  `publishDate` datetime DEFAULT NULL COMMENT '发布日期',
  `userId` int(11) DEFAULT NULL COMMENT '发布人id',
  `articleTypeId` int(11) DEFAULT NULL COMMENT '文章类型id',
  `points` int(11) DEFAULT NULL COMMENT '下载所需积分',
  `summary` text COMMENT '摘要',
  `content` text CHARACTER SET utf8mb4 COMMENT '内容',
  `downloadLink` varchar(50) CHARACTER SET utf8mb4 DEFAULT NULL COMMENT '百度云链接',
  `password` varchar(50) CHARACTER SET utf8mb4 DEFAULT NULL COMMENT '链接密码',
  `isHot` int(11) DEFAULT NULL COMMENT '是否热门 0代表不是 1代表是',
  `state` int(11) DEFAULT NULL COMMENT '状态 1代表未审核 2代表审核通过 3代表审核未通过',
  `reason` varchar(50) CHARACTER SET utf8mb4 DEFAULT NULL COMMENT '审核未通过理由',
  `checkDate` datetime DEFAULT NULL COMMENT '审核时间',
  `isUseful` int(11) DEFAULT NULL COMMENT '链接是否失效 0代表未失效 1代表已经失效',
  `click` int(11) DEFAULT NULL COMMENT '点击次数',
  PRIMARY KEY (`id`),
  KEY `id` (`id`),
  KEY `articleTypeId` (`articleTypeId`),
  KEY `userId` (`userId`),
  CONSTRAINT `t_article_ibfk_2` FOREIGN KEY (`userId`) REFERENCES `t_user` (`id`) ON DELETE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=93 DEFAULT CHARSET=utf8 COMMENT='t_article';

/*Data for the table `t_article` */

insert  into `t_article`(`id`,`name`,`publishDate`,`userId`,`articleTypeId`,`points`,`summary`,`content`,`downloadLink`,`password`,`isHot`,`state`,`reason`,`checkDate`,`isUseful`,`click`) values (57,'java编程思想','2021-01-10 22:58:56',34,1,2,'java编程思想\n','<p><a href=\"http://localhost:8080/article/23\" title=\"java编程思想\">java编程思想</a></p>\r\n','111','111',1,3,'链接失效','2021-01-17 21:42:54',0,19),(58,'拳皇版超级玛丽13可执行文件','2021-01-10 23:00:53',34,1,4,'拳皇版超级玛丽13可执行文件\n','<p>拳皇版超级玛丽13可执行文件</p>\r\n','11','11',1,2,NULL,NULL,0,11),(59,'2020年java常见笔试面试题汇总(附答案)','2021-01-10 23:01:14',34,1,5,'2020年java常见笔试面试题汇总(附答案)\n','<p>2020年java常见笔试面试题汇总(附答案)</p>\r\n','111','11',1,2,NULL,NULL,1,28),(60,'常用日期函数','2021-01-10 23:01:36',34,2,5,'常用日期函数\n','<p>常用日期函数</p>\r\n','11','11',1,2,NULL,NULL,1,14),(61,'王二暖Oracle视频教程 下载','2021-01-10 23:02:24',34,2,3,'王二暖Oracle视频教程 下载\n','<p>王二暖Oracle视频教程 下载</p>\r\n','11','11',1,2,NULL,NULL,0,20),(62,'Mysql从入门到精通视频教程（共29集）下载','2021-01-10 23:02:56',34,2,5,'Mysql从入门到精通视频教程（共29集）下载\n','<p>Mysql从入门到精通视频教程（共29集）下载</p>\r\n','11','11',1,2,NULL,NULL,1,29),(63,'Postgresql数据库优化视频教程 下载','2021-01-10 23:03:13',34,2,5,'Postgresql数据库优化视频教程 下载\n','<p>Postgresql数据库优化视频教程 下载</p>\r\n','1','1',1,2,NULL,NULL,1,21),(64,'Oracle Rachel操作手册','2021-01-10 23:03:46',34,2,4,'Oracle Rachel操作手册\n','<p>Oracle Rachel操作手册</p>\r\n','1','1',1,1,NULL,NULL,1,0),(65,'mysql高级mycat集群视频&redis视频视频教程 下载','2021-01-10 23:04:06',34,2,3,'mysql高级mycat集群视频&amp;redis视频视频教程 下载\n','<p>mysql高级mycat集群视频&amp;redis视频视频教程 下载</p>\r\n','1','1',1,3,'内容包含广告','2021-01-17 21:14:16',1,1),(66,'50份优秀Java求职者简历','2021-01-10 23:04:53',35,1,4,'50份优秀Java求职者简历\n','<p>50份优秀Java求职者简历</p>\r\n','1','1',1,2,NULL,NULL,1,29),(67,'高级架构师进阶24天视频教程','2021-01-10 23:05:07',35,1,4,'高级架构师进阶24天视频教程\n','<p>高级架构师进阶24天视频教程</p>\r\n','1','1',1,2,NULL,NULL,1,31),(68,'从零开始学java','2021-01-10 23:05:19',35,1,5,'从零开始学java\n','<p>从零开始学java</p>\r\n','1','1',1,2,NULL,NULL,1,43),(69,'刘道成MySQL系列教程','2021-01-10 23:05:34',35,2,4,'刘道成MySQL系列教程\n','<p>刘道成MySQL系列教程</p>\r\n','1','1',1,2,NULL,NULL,1,33),(70,'mybatis教程pdf合集','2021-01-10 23:05:51',35,2,3,'mybatis教程pdf合集\n','<p>mybatis教程pdf合集</p>\r\n','11','1',1,2,NULL,NULL,1,44),(71,'黑马JavaEE 57期','2021-01-10 23:06:15',35,4,4,'黑马JavaEE 57期\n','<p>黑马JavaEE 57期</p>\r\n','1','1',1,2,NULL,NULL,1,39),(72,'SSM整合案例【企业权限管理系统】视频教程','2021-01-10 23:06:30',35,4,4,'SSM整合案例【企业权限管理系统】视频教程\n','<p><a href=\"http://download.java1234.com/article/1942\" target=\"_blank\" title=\"SSM整合案例【企业权限管理系统】视频教程\">SSM整合案例【企业权限管理系统】视频教程</a></p>\r\n','1','1',1,2,NULL,NULL,1,55),(73,'Vue2.0+Node.js+MongoDB全栈打造商城系统视','2021-01-10 23:07:29',35,3,2,'Vue2.0+Node.js+MongoDB全栈打造商城系统视\n','<p>Vue2.0+Node.js+MongoDB全栈打造商城系统视</p>\r\n','1','1',1,2,NULL,NULL,1,44),(74,'Vue项目实战教程','2021-01-10 23:07:42',35,3,5,'Vue项目实战教程\n','<p>Vue项目实战教程</p>\r\n','1','1',1,2,NULL,NULL,1,53),(75,'MUI全接触','2021-01-10 23:07:55',35,3,7,'MUI全接触\n','<p>MUI全接触</p>\r\n','1','1',1,2,NULL,NULL,1,109),(76,'web打印分页设置','2021-01-10 23:12:11',34,3,5,'web打印分页设置\n','<p><a href=\"http://download.java1234.com/article/1845\" target=\"_blank\" title=\"web打印分页设置\">web打印分页设置</a></p>\r\n','1','1',0,2,'111','2021-01-18 14:27:55',1,2),(92,'Web前端技术','2021-01-17 22:49:01',34,3,4,'Web前端技术Web前端技术Web前端技术Web前端技术Web前端技术Web前端技术Web前端技术Web前端技术Web前端技术Web前端技术Web前端技术Web前端技术Web前端技术Web前端技术Web前端技术Web前端技术Web前端技术Web前端技术Web前端技术Web前端技术Web前端技术Web前端技术Web前端技术Web前端技术Web前端技术Web前端技术Web前端技术Web前端技术Web前端技术Web前端技术Web前端技术Web前端技术Web前端技术Web前端技术Web前端技术Web前端技术Web前端技术Web前端技术Web前端技术Web前端技术Web前端技术Web前端技术Web前端技术Web前端技术Web前端技术Web前端技术\n\n\n\n\n\n&nbsp;\n','<p>Web前端技术Web前端技术Web前端技术Web前端技术Web前端技术Web前端技术Web前端技术Web前端技术Web前端技术Web前端技术Web前端技术Web前端技术Web前端技术Web前端技术Web前端技术Web前端技术Web前端技术Web前端技术Web前端技术Web前端技术Web前端技术Web前端技术Web前端技术Web前端技术Web前端技术Web前端技术Web前端技术Web前端技术Web前端技术Web前端技术Web前端技术Web前端技术Web前端技术Web前端技术Web前端技术Web前端技术Web前端技术Web前端技术Web前端技术Web前端技术Web前端技术Web前端技术Web前端技术Web前端技术Web前端技术Web前端技术</p>\r\n\r\n<p><img alt=\"\" src=\"/static/images/articleImage/20210117224856.jpg\" style=\"height:457px; width:700px\" /></p>\r\n\r\n<p><img alt=\"\" src=\"/static/images/articleImage/20210118141517.jpg\" style=\"height:62px; width:700px\" /></p>\r\n\r\n<p>&nbsp;</p>\r\n','1','1',0,2,'无','2021-01-18 21:56:32',1,203);

/*Table structure for table `t_article_type` */

DROP TABLE IF EXISTS `t_article_type`;

CREATE TABLE `t_article_type` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '编号',
  `name` varchar(50) CHARACTER SET utf8mb4 DEFAULT NULL COMMENT '名称',
  `remark` varchar(50) CHARACTER SET utf8mb4 DEFAULT NULL COMMENT '描述',
  `sortNum` int(11) DEFAULT NULL COMMENT '排列顺序',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=29 DEFAULT CHARSET=utf8 COMMENT='t_article_type';

/*Data for the table `t_article_type` */

insert  into `t_article_type`(`id`,`name`,`remark`,`sortNum`) values (1,'Java技术','Java技术',1),(2,'数据库技术','数据库技术',3),(3,'Web前端技术','Web前端技术',4),(4,'J2EE技术','J2EE技术',2),(9,'分布式微服技术','分布式微服技术',5),(10,'移动APP开发技术','移动APP开发技术',6);

/*Table structure for table `t_comment` */

DROP TABLE IF EXISTS `t_comment`;

CREATE TABLE `t_comment` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '编号',
  `userId` int(11) DEFAULT NULL COMMENT '评论人id',
  `articleId` int(11) DEFAULT NULL COMMENT '被评论的文章id',
  `commentContent` varchar(1000) CHARACTER SET utf8mb4 DEFAULT NULL COMMENT '内容',
  `date` datetime DEFAULT NULL COMMENT '评论时间',
  `articleAuthorId` int(11) DEFAULT NULL COMMENT '资源作者id',
  `state` int(11) DEFAULT NULL COMMENT '状态',
  PRIMARY KEY (`id`),
  KEY `id` (`id`),
  KEY `userId` (`userId`),
  KEY `articleId` (`articleId`),
  CONSTRAINT `t_comment_ibfk_1` FOREIGN KEY (`userId`) REFERENCES `t_user` (`id`) ON DELETE CASCADE,
  CONSTRAINT `t_comment_ibfk_2` FOREIGN KEY (`articleId`) REFERENCES `t_article` (`id`) ON DELETE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=58 DEFAULT CHARSET=utf8 COMMENT='t_comment';

/*Data for the table `t_comment` */

insert  into `t_comment`(`id`,`userId`,`articleId`,`commentContent`,`date`,`articleAuthorId`,`state`) values (33,34,75,'11','2021-01-10 23:10:12',35,1),(34,34,75,'22','2021-01-10 23:10:14',35,1),(35,35,62,'111','2021-01-10 23:10:50',34,1),(39,34,67,'哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈','2021-01-11 22:34:39',35,1),(57,34,67,'666','2021-01-17 20:32:18',35,2);

/*Table structure for table `t_download_message` */

DROP TABLE IF EXISTS `t_download_message`;

CREATE TABLE `t_download_message` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '编号',
  `userId` int(11) DEFAULT NULL COMMENT '下载者id',
  `articleId` int(11) DEFAULT NULL COMMENT '资源id',
  `message` varchar(500) CHARACTER SET utf8mb4 DEFAULT NULL COMMENT '下载信息',
  `downloadDate` datetime DEFAULT NULL COMMENT '下载时间',
  PRIMARY KEY (`id`),
  KEY `id` (`id`),
  KEY `userId` (`userId`),
  KEY `articleId` (`articleId`),
  CONSTRAINT `t_download_message_ibfk_1` FOREIGN KEY (`userId`) REFERENCES `t_user` (`id`) ON DELETE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=78 DEFAULT CHARSET=utf8 COMMENT='t_download_message';

/*Data for the table `t_download_message` */

insert  into `t_download_message`(`id`,`userId`,`articleId`,`message`,`downloadDate`) values (48,35,68,'下载了：从零开始学java','2021-01-10 23:09:32'),(49,34,75,'下载了：MUI全接触','2021-01-10 23:10:20'),(50,34,66,'下载了：50份优秀Java求职者简历','2021-01-10 23:15:17'),(51,34,63,'下载了：Postgresql数据库优化视频教程 下载','2021-01-10 23:24:35'),(52,34,73,'下载了：Vue2.0+Node.js+MongoDB全栈打造商城系统视','2021-01-11 01:51:23'),(53,34,75,'下载了：MUI全接触','2021-01-11 01:58:11'),(54,34,72,'下载了：SSM整合案例【企业权限管理系统】视频教程','2021-01-11 02:10:30'),(55,34,72,'下载了：SSM整合案例【企业权限管理系统】视频教程','2021-01-11 02:10:35'),(56,34,71,'下载了：黑马JavaEE 57期','2021-01-11 02:10:54'),(57,34,70,'下载了：mybatis教程pdf合集','2021-01-11 02:11:03'),(76,34,73,'下载了：Vue2.0+Node.js+MongoDB全栈打造商城系统视','2021-01-17 13:01:21'),(77,34,75,'下载了：MUI全接触','2021-01-17 21:45:12');

/*Table structure for table `t_information` */

DROP TABLE IF EXISTS `t_information`;

CREATE TABLE `t_information` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '编号',
  `userId` int(11) DEFAULT NULL COMMENT '消息所属者id',
  `content` varchar(1000) CHARACTER SET utf8mb4 DEFAULT NULL COMMENT '内容',
  `date` datetime DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`),
  KEY `id` (`id`),
  KEY `userId` (`userId`),
  CONSTRAINT `t_information_ibfk_1` FOREIGN KEY (`userId`) REFERENCES `t_user` (`id`) ON DELETE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=20 DEFAULT CHARSET=utf8 COMMENT='int_formation';

/*Data for the table `t_information` */

insert  into `t_information`(`id`,`userId`,`content`,`date`) values (1,34,'你的资源(Java编程思想)的分享链接已经失效,请及时更新','2021-01-13 03:40:59'),(12,34,'qqqqqqqqq','2021-01-16 16:21:27'),(13,34,'wwwwww','2021-01-16 16:21:33');

/*Table structure for table `t_link` */

DROP TABLE IF EXISTS `t_link`;

CREATE TABLE `t_link` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '编号',
  `name` varchar(50) CHARACTER SET utf8mb4 DEFAULT NULL COMMENT '名称',
  `url` varchar(50) CHARACTER SET utf8mb4 DEFAULT NULL COMMENT '地址',
  `sortNum` int(11) DEFAULT NULL COMMENT '排列序号',
  PRIMARY KEY (`id`),
  KEY `id` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8 COMMENT='t_link';

/*Data for the table `t_link` */

insert  into `t_link`(`id`,`name`,`url`,`sortNum`) values (1,'宠物医院管理系统','http://pethospital.zoutl.cn',2),(2,'LeDao的博客','http://www.zoutl.cn',1),(3,'进销存管理系统','http://jxc.zoutl.cn',3);

/*Table structure for table `t_user` */

DROP TABLE IF EXISTS `t_user`;

CREATE TABLE `t_user` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '编号',
  `userName` varchar(50) DEFAULT NULL COMMENT '用户名',
  `password` varchar(50) DEFAULT NULL COMMENT '密码',
  `nickName` varchar(50) DEFAULT NULL COMMENT '昵称',
  `email` varchar(50) DEFAULT NULL COMMENT '邮箱地址',
  `imageName` varchar(50) DEFAULT NULL COMMENT '头像地址',
  `points` int(11) DEFAULT NULL,
  `isVip` int(11) DEFAULT NULL,
  `isOff` int(11) DEFAULT NULL,
  `roleName` varchar(50) DEFAULT NULL,
  `registerDate` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=55 DEFAULT CHARSET=utf8;

/*Data for the table `t_user` */

insert  into `t_user`(`id`,`userName`,`password`,`nickName`,`email`,`imageName`,`points`,`isVip`,`isOff`,`roleName`,`registerDate`) values (1,'admin','234567','乐道','1203007469@qq.com','20210114225430.jpg',10000,1,0,'管理员','2021-01-03 23:16:40'),(34,'111','222222','LeDao','3495197980@qq.com','20210108235216.jpg',100,0,0,'VIP用户','2021-01-07 02:06:22'),(35,'222','333333','222','12030074169@qq.com','20210107021707.PNG',102,0,0,'VIP用户','2021-01-07 02:17:07'),(36,'333','444444','乐道乐','12030074619@qq.com','20210109172637.jfif',100,0,0,'普通用户','2021-01-09 17:26:37'),(37,'444','555555','444','12031007469@qq.com','20210111025503.jpg',100,0,0,'普通用户','2021-01-11 02:55:03'),(38,'555','666666','555','f1203007469@gmail.com','20210111025655.jpg',100,0,0,'普通用户','2021-01-11 02:56:55'),(39,'666','777777','666','zou66786961@163.com','20210111025751.jpg',100,0,0,'普通用户','2021-01-11 02:57:51'),(40,'777','888888','777','f12030074691@gmail.com','20210113053514.png',100,0,0,'普通用户','2021-01-13 05:35:14'),(41,'888','999999','888','zou667869611@163.com',NULL,100,0,1,'普通用户','2021-01-14 04:35:24');

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
