package com.community.dao;

import com.community.entity.Comment;

import java.util.List;

public interface CommentDao {

    List<Comment> findAllByArticleId(Integer id);
}
