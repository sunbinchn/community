package com.community.enums;

public enum ArticleShowTypeConstant {
    LATEST(1,"latest"),RECOMMEND(2, "recommend"),HOT(3, "hot");
    private Integer id;
    private String name;

    ArticleShowTypeConstant(Integer id, String name) {
        this.id = id;
        this.name = name;
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
}