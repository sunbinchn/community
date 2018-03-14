package com.community.entity;

import java.util.Date;
import java.util.List;

public class Article {
    private Integer id;
    private String title;
    private String content;
    private List<User> readUserList;
    private List<User> loveUserList;
    private List<User> keepUserList;//收藏的用户
    private Integer original;
    private Integer isRecommend;
    private Article fromArticle;
    private User user;
    private ArticleType articleType;
    private Date createTime;
    private Date updateTime;
    private List<Comment> commentList;
    private List<Tag> tagList;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public List<User> getReadUserList() {
        return readUserList;
    }

    public void setReadUserList(List<User> readUserList) {
        this.readUserList = readUserList;
    }

    public List<User> getLoveUserList() {
        return loveUserList;
    }

    public void setLoveUserList(List<User> loveUserList) {
        this.loveUserList = loveUserList;
    }

    public List<User> getKeepUserList() {
        return keepUserList;
    }

    public void setKeepUserList(List<User> keepUserList) {
        this.keepUserList = keepUserList;
    }

    public Integer getOriginal() {
        return original;
    }

    public void setOriginal(Integer original) {
        this.original = original;
    }

    public Integer getIsRecommend() {
        return isRecommend;
    }

    public void setIsRecommend(Integer isRecommend) {
        this.isRecommend = isRecommend;
    }

    public Article getFromArticle() {
        return fromArticle;
    }

    public void setFromArticle(Article fromArticle) {
        this.fromArticle = fromArticle;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public ArticleType getArticleType() {
        return articleType;
    }

    public void setArticleType(ArticleType articleType) {
        this.articleType = articleType;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public List<Comment> getCommentList() {
        return commentList;
    }

    public void setCommentList(List<Comment> commentList) {
        this.commentList = commentList;
    }

    public List<Tag> getTagList() {
        return tagList;
    }

    public void setTagList(List<Tag> tagList) {
        this.tagList = tagList;
    }
}
