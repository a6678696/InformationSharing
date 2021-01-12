-- auto Generated on 2021-01-13
-- DROP TABLE IF EXISTS int_formation;
CREATE TABLE int_formation(
	id INT (11) NOT NULL AUTO_INCREMENT COMMENT '编号',
	userId INT (11) NOT NULL COMMENT '消息所属者id',
	content VARCHAR (50) NOT NULL COMMENT '内容',
	`date` DATETIME NOT NULL COMMENT '创建时间',
	INDEX(id),
	PRIMARY KEY (id)
)ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT 'int_formation';
