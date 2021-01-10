package com.ledao.entity;

import java.util.Date;

/**
 * 下载信息
 *
 * @author LeDao
 * @company
 * @create 2021-01-10 20:28
 */
public class DownloadMessage {

    /**
     * 编号
     */
    private Integer id;
    /**
     * 下载者id
     */
    private Integer userId;
    /**
     * 资源id
     */
    private Integer articleId;
    /**
     * 下载信息
     */
    private String message;
    /**
     * 下载时间
     */
    private Date downloadDate;

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

    public Integer getArticleId() {
        return articleId;
    }

    public void setArticleId(Integer articleId) {
        this.articleId = articleId;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Date getDownloadDate() {
        return downloadDate;
    }

    public void setDownloadDate(Date downloadDate) {
        this.downloadDate = downloadDate;
    }
}
