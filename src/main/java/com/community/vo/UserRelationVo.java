package com.community.vo;

import com.community.entity.User;

import java.util.List;

public class UserRelationVo {
    private List<User> idolUserList;
    private List<User> fansUserList;
    private boolean isIdolOfCurrentUser;

    public boolean getIsIdolOfCurrentUser() {
        return isIdolOfCurrentUser;
    }

    public void setIdolOfCurrentUser(boolean idolOfCurrentUser) {
        isIdolOfCurrentUser = idolOfCurrentUser;
    }

    public List<User> getIdolUserList() {
        return idolUserList;
    }

    public void setIdolUserList(List<User> idolUserList) {
        this.idolUserList = idolUserList;
    }

    public List<User> getFansUserList() {
        return fansUserList;
    }

    public void setFansUserList(List<User> fansUserList) {
        this.fansUserList = fansUserList;
    }
}
