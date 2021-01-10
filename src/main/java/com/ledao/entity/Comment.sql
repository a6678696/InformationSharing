-- auto Generated on 2021-01-10
-- DROP TABLE IF EXISTS t_comment;
CREATE TABLE t_comment(
	id INT (11) NOT NULL AUTO_INCREMENT COMMENT '编号',
	userId INT (11) NOT NULL COMMENT '评论人id',
	articleId INT (11) NOT NULL COMMENT '被评论的文章id',
	commentContent VARCHAR (50) NOT NULL COMMENT '内容',
	INDEX(id),
	PRIMARY KEY (id)
)ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT 't_comment';
