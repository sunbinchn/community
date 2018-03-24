package com.community.dao;

import com.community.entity.Comment;

import java.util.List;

public interface CommentDao {

    /**
     * 只会找文章的直接评论，而不会找评论中的评论
     *
     * @param id
     * @return
     */
    List<Comment> findAllByArticleId(Integer id);

    Comment findById(Integer id);

    boolean insert(Comment comment);

    List<Comment> findAllByOwnerCommentId(Integer id);

    Integer countAllByOwnerCommentId(Integer id);
}
