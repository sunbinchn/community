package com.community.enums;

public enum ArticlePassConstant {
    UNCHECKED(0),CHECKED(1),DELETED(-1);

    private Integer value;

    ArticlePassConstant(Integer value) {
        this.value = value;
    }

    public Integer getValue() {
        return value;
    }

    public void setValue(Integer value) {
        this.value = value;
    }
}
