package com.community.vo;

import com.community.entity.User;
import com.github.pagehelper.PageInfo;

public class UserRelationVo {
    private Integer idolCount;
    private Integer fansCount;
    private PageInfo<User> idolUserPageInfo;
    private PageInfo<User> fansUserPageInfo;
    private boolean isIdolOfCurrentUser;

    public Integer getIdolCount() {
        return idolCount;
    }

    public void setIdolCount(Integer idolCount) {
        this.idolCount = idolCount;
    }

    public Integer getFansCount() {
        return fansCount;
    }

    public void setFansCount(Integer fansCount) {
        this.fansCount = fansCount;
    }

    public boolean getIsIdolOfCurrentUser() {
        return isIdolOfCurrentUser;
    }

    public void setIdolOfCurrentUser(boolean idolOfCurrentUser) {
        isIdolOfCurrentUser = idolOfCurrentUser;
    }

    public PageInfo<User> getIdolUserPageInfo() {
        return idolUserPageInfo;
    }

    public void setIdolUserPageInfo(PageInfo<User> idolUserPageInfo) {
        this.idolUserPageInfo = idolUserPageInfo;
    }

    public PageInfo<User> getFansUserPageInfo() {
        return fansUserPageInfo;
    }

    public void setFansUserPageInfo(PageInfo<User> fansUserPageInfo) {
        this.fansUserPageInfo = fansUserPageInfo;
    }
}
