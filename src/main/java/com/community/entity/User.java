package com.community.entity;


import org.hibernate.validator.constraints.Email;

public class User {
    private Integer userId;
    private String userNameOrEmail;
    private String userName;
    private String nickname;
    private String password;
    @Email
    private String email;
    private String phone;
    private String company;
    private String jobTitle;
    private Image icon;
    private String signature;
    private Integer visits;
    private Integer role;
    private Integer isShutUp;
    private Integer isBan;
    //供显示使用
    private boolean isIdolOfCurrentUser;

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserNameOrEmail() {
        return userNameOrEmail;
    }

    public void setUserNameOrEmail(String userNameOrEmail) {
        this.userNameOrEmail = userNameOrEmail;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Image getIcon() {
        return icon;
    }

    public void setIcon(Image icon) {
        this.icon = icon;
    }

    public String getSignature() {
        return signature;
    }

    public void setSignature(String signature) {
        this.signature = signature;
    }

    public Integer getVisits() {
        return visits;
    }

    public void setVisits(Integer visits) {
        this.visits = visits;
    }

    public Integer getRole() {
        return role;
    }

    public void setRole(Integer role) {
        this.role = role;
    }

    public Integer getIsShutUp() {
        return isShutUp;
    }

    public void setIsShutUp(Integer isShutUp) {
        this.isShutUp = isShutUp;
    }

    public Integer getIsBan() {
        return isBan;
    }

    public void setIsBan(Integer isBan) {
        this.isBan = isBan;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public String getJobTitle() {
        return jobTitle;
    }

    public void setJobTitle(String jobTitle) {
        this.jobTitle = jobTitle;
    }

    public boolean getIsIdolOfCurrentUser() {
        return isIdolOfCurrentUser;
    }

    public void setIsIdolOfCurrentUser(boolean idolOfCurrentUser) {
        isIdolOfCurrentUser = idolOfCurrentUser;
    }
}
