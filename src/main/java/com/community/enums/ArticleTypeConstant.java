package com.community.enums;

public enum ArticleTypeConstant {
    OPEN_SOURCE(1, "开源项目"),
    DATABSE(2, "数据库"),
    CLOUD_COMPUTING(3, "云计算"),
    ARTIFICIA_SELF_ENERGY(4, "人工自能"),
    FRONT(5, "前端"),
    BACK(6, "后端"),
    RESOURCES(7, "工具资源"),
    JOB(8, "求职资讯");
    private Integer id;
    private String name;
    private String description;

    ArticleTypeConstant(Integer id, String name) {
        this.id = id;
        this.name = name;
    }

    ArticleTypeConstant(Integer id, String name, String description) {
        this.id = id;
        this.name = name;
        this.description = description;
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}