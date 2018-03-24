package com.community.service;

import com.community.dao.ArticleDao;
import com.community.dao.CommentDao;
import com.community.entity.Article;
import com.community.entity.Comment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.List;

@Service
public class ArticleDetailService {
    @Autowired
    private ArticleDao articleDao;
    @Autowired
    private CommentDao commentDao;

    public Article findById(Integer id) {
        Article article = articleDao.findById(id);
        List<Comment> commentList = article.getCommentList();
        if (!CollectionUtils.isEmpty(commentList)) {
            for (Comment comment : commentList) {
                Integer integer = commentDao.countAllByOwnerCommentId(comment.getId());
                comment.setNestCommentSize(integer);
            }
        }
        return article;
    }
}
