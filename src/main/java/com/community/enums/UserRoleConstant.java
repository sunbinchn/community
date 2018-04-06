package com.community.enums;

public enum  UserRoleConstant {
    ADMIN(2, "管理员"), USER(0, "普通用户");
    private Integer id;
    private String name;

    UserRoleConstant(Integer id, String name) {
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
