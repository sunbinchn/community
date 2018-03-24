package com.community.vo;

public class NestCommentVo {
    private Integer userId;
    private Integer targetCommentId;
    private String content;

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getTargetCommentId() {
        return targetCommentId;
    }

    public void setTargetCommentId(Integer targetCommentId) {
        this.targetCommentId = targetCommentId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
