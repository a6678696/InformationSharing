package com.ledao.entity;

import java.util.Date;

/**
 * 消息实体类
 *
 * @author LeDao
 * @company
 * @create 2021-01-13 2:20
 */
public class Information {

    /**
     * 编号
     */
    private Integer id;
    /**
     * 消息所属者id
     */
    private Integer userId;
    /**
     * 内容
     */
    private String content;
    /**
     * 创建时间
     */
    private Date date;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}
