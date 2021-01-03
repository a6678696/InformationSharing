-- auto Generated on 2021-01-03
-- DROP TABLE IF EXISTS t_article;
CREATE TABLE t_article(
	id INT (11) NOT NULL AUTO_INCREMENT COMMENT '编号',
	`name` VARCHAR (50) NOT NULL COMMENT '文章名称',
	publishDate DATETIME NOT NULL COMMENT '发布日期',
	userId INT (11) NOT NULL COMMENT '发布人id',
	articleTypeId INT (11) NOT NULL COMMENT '文章类型id',
	points INT (11) NOT NULL COMMENT '下载所需积分',
	content VARCHAR (50) NOT NULL COMMENT '内容',
	downloadLink VARCHAR (50) NOT NULL COMMENT '百度云链接',
	`password` VARCHAR (50) NOT NULL COMMENT '链接密码',
	isHot INT (11) NOT NULL COMMENT '是否热门 0代表不是 1代表是',
	`state` INT (11) NOT NULL COMMENT '状态 1代表未审核 2代表审核通过 3代表审核未通过',
	reason VARCHAR (50) NOT NULL COMMENT '审核未通过理由',
	checkDate DATETIME NOT NULL COMMENT '审核时间',
	isUseful INT (11) NOT NULL COMMENT '链接是否失效 0代表未失效 1代表已经失效',
	click INT (11) NOT NULL COMMENT '点击次数',
	INDEX(id),
	PRIMARY KEY (id)
)ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT 't_article';
