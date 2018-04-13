package com.community.dao;

import com.community.entity.User;
import com.community.entity.UserRelation;

import java.util.List;

public interface UserRelationDao {

    boolean insert(UserRelation userRelation);

    List<User> findAllByUserId(Integer userId);

    Integer countAllByUserId(Integer userId);

    List<User> findAllByTargetUserId(Integer userId);

    Integer countAllByTargetUserId(Integer userId);

    Integer find(UserRelation userRelation);

    boolean delete(UserRelation userRelation);
}
