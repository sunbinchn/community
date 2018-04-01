package com.community.vo;

public class ArticleVo {
    private Integer original;
    private String title;
    private String content;
    private Integer ArticleTypeId;
    private Integer articleId;

    public Integer getOriginal() {
        return original;
    }

    public void setOriginal(Integer original) {
        this.original = original;
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

    public Integer getArticleTypeId() {
        return ArticleTypeId;
    }

    public void setArticleTypeId(Integer articleTypeId) {
        ArticleTypeId = articleTypeId;
    }

    public Integer getArticleId() {
        return articleId;
    }

    public void setArticleId(Integer articleId) {
        this.articleId = articleId;
    }
}
