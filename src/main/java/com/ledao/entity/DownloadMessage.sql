-- auto Generated on 2021-01-10
-- DROP TABLE IF EXISTS t_download_message;
CREATE TABLE t_download_message(
	id INT (11) NOT NULL AUTO_INCREMENT COMMENT '编号',
	userId INT (11) NOT NULL COMMENT '下载者id',
	articleId INT (11) NOT NULL COMMENT '资源id',
	message VARCHAR (50) NOT NULL COMMENT '下载信息',
	downloadDate DATETIME NOT NULL COMMENT '下载时间',
	INDEX(id),
	PRIMARY KEY (id)
)ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT 't_download_message';
