package com.ledao.entity;

import java.util.Date;

/**
 * 文章实体
 *
 * @author LeDao
 * @company
 * @create 2021-01-03 23:26
 */
public class Article {

    /**
     * 编号
     */
    private Integer id;
    /**
     * 文章名称
     */
    private String name;
    /**
     * 发布日期
     */
    private Date publishDate;
    /**
     * 发布日期字符串
     */
    private String publishDateStr;
    /**
     * 发布人id
     */
    private Integer userId;
    /**
     * 发布人实体(用于获取发布人信息)
     */
    private User user;
    /**
     * 文章类型id
     */
    private Integer articleTypeId;
    /**
     * 文章类型实体
     */
    private ArticleType articleType;
    /**
     * 下载所需积分
     */
    private Integer points;
    /**
     * 内容
     */
    private String content;
    /**
     *
     */
    private String summary;
    /**
     * 百度云链接
     */
    private String downloadLink;
    /**
     * 链接密码
     */
    private String password;
    /**
     * 是否热门 0代表不是 1代表是
     */
    private Integer isHot;
    /**
     * 状态 1代表未审核 2代表审核通过 3代表审核未通过
     */
    private Integer state;
    /**
     * 审核未通过理由
     */
    private String reason;
    /**
     * 审核时间
     */
    private Date checkDate;
    /**
     * 链接是否有效 0代表已经失效 1代表有效
     */
    private Integer isUseful;
    /**
     * 点击次数
     */
    private Integer click;

    public Article() {
    }

    public Article(String name, Integer state) {
        this.name = name;
        this.state = state;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getPublishDate() {
        return publishDate;
    }

    public void setPublishDate(Date publishDate) {
        this.publishDate = publishDate;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Integer getArticleTypeId() {
        return articleTypeId;
    }

    public void setArticleTypeId(Integer articleTypeId) {
        this.articleTypeId = articleTypeId;
    }

    public Integer getPoints() {
        return points;
    }

    public void setPoints(Integer points) {
        this.points = points;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getDownloadLink() {
        return downloadLink;
    }

    public void setDownloadLink(String downloadLink) {
        this.downloadLink = downloadLink;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Integer getIsHot() {
        return isHot;
    }

    public void setIsHot(Integer isHot) {
        this.isHot = isHot;
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public Date getCheckDate() {
        return checkDate;
    }

    public void setCheckDate(Date checkDate) {
        this.checkDate = checkDate;
    }

    public Integer getIsUseful() {
        return isUseful;
    }

    public void setIsUseful(Integer isUseful) {
        this.isUseful = isUseful;
    }

    public Integer getClick() {
        return click;
    }

    public void setClick(Integer click) {
        this.click = click;
    }

    public ArticleType getArticleType() {
        return articleType;
    }

    public void setArticleType(ArticleType articleType) {
        this.articleType = articleType;
    }

    public String getPublishDateStr() {
        return publishDateStr;
    }

    public void setPublishDateStr(String publishDateStr) {
        this.publishDateStr = publishDateStr;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }
}
