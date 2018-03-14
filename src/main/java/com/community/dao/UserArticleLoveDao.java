package com.community.dao;

import com.community.entity.User;

import java.util.List;

public interface UserArticleLoveDao {
    List<User> findUserListByArticleId(Integer id);
}