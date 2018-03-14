package com.community.dao;

import com.community.entity.User;

import java.util.List;

public interface UserCommentLoveDao {
    List<User> findUserListByCommentId(Integer id);
}