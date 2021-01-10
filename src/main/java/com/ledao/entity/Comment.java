package com.ledao.entity;

import java.util.Date;

/**
 * 评论实体类
 *
 * @author LeDao
 * @company
 * @create 2021-01-10 15:14
 */
public class Comment {

    /**
     * 编号
     */
    private Integer id;
    /**
     * 评论人id
     */
    private Integer userId;
    /**
     * 评论人实体
     */
    private User user;
    /**
     * 被评论的文章id
     */
    private Integer articleId;
    /**
     * 文章实体
     */
    private Article article;
    /**
     * 内容
     */
    private String commentContent;
    /**
     * 评论时间
     */
    private Date date;
    /**
     * 资源作者id
     */
    private Integer articleAuthorId;
    /**
     * 资源作者实体
     */
    private User articleAuthor;
    /**
     * 状态 0表示未审核 1代表审核通过 2.代表审核不通过
     */
    private Integer state;

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

    public String getCommentContent() {
        return commentContent;
    }

    public void setCommentContent(String commentContent) {
        this.commentContent = commentContent;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Article getArticle() {
        return article;
    }

    public void setArticle(Article article) {
        this.article = article;
    }

    public Integer getArticleAuthorId() {
        return articleAuthorId;
    }

    public void setArticleAuthorId(Integer articleAuthorId) {
        this.articleAuthorId = articleAuthorId;
    }

    public User getArticleAuthor() {
        return articleAuthor;
    }

    public void setArticleAuthor(User articleAuthor) {
        this.articleAuthor = articleAuthor;
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }
}
