package com.ledao.entity;

import java.util.Date;

/**
 * 用户实体类
 *
 * @author LeDao
 * @company
 * @create 2021-01-02 0:28
 */
public class User {

    /**
     * 编号
     */
    private Integer id;
    /**
     * 用户名
     */
    private String userName;
    /**
     * 密码
     */
    private String password;
    /**
     * 昵称
     */
    private String nickName;
    /**
     * 邮箱
     */
    private String email;
    /**
     * 用户头像存储地址
     */
    private String imageName;
    /**
     * 积分
     */
    private Integer points;
    /**
     * 是否是VIP 0代表不是,1代表是
     */
    private Integer isVip;
    /**
     * 是否被封禁 0代表没被封禁,1代表被封禁
     */
    private Integer isOff;
    /**
     * 角色名称(1.普通用户 2.会员 3.管理员)
     */
    private String roleName;
    /**
     * 注册日期
     */
    private Date registerDate;
    /**
     * 验证码（修改密码时使用）
     */
    private String checkCode;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getImageName() {
        return imageName;
    }

    public void setImageName(String imageName) {
        this.imageName = imageName;
    }

    public Integer getPoints() {
        return points;
    }

    public void setPoints(Integer points) {
        this.points = points;
    }

    public Integer getIsVip() {
        return isVip;
    }

    public void setIsVip(Integer isVip) {
        this.isVip = isVip;
    }

    public Integer getIsOff() {
        return isOff;
    }

    public void setIsOff(Integer isOff) {
        this.isOff = isOff;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public Date getRegisterDate() {
        return registerDate;
    }

    public void setRegisterDate(Date registerDate) {
        this.registerDate = registerDate;
    }

    public String getCheckCode() {
        return checkCode;
    }

    public void setCheckCode(String checkCode) {
        this.checkCode = checkCode;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", userName='" + userName + '\'' +
                ", password='" + password + '\'' +
                ", nickName='" + nickName + '\'' +
                ", email='" + email + '\'' +
                ", imageName='" + imageName + '\'' +
                ", points=" + points +
                ", isVip=" + isVip +
                ", isOff=" + isOff +
                ", roleName='" + roleName + '\'' +
                ", registerDate=" + registerDate +
                ", checkCode='" + checkCode + '\'' +
                '}';
    }
}
