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

    public Article findById(Integer id, Integer currentUserId) {
        Article article;
        article = articleDao.findForceById(id);
        if (article.getIsPass() != 1 && !article.getUser().getUserId().equals(currentUserId)) { //审核未通过且不是自己的文章
            return null;
        }
        if (article != null) {
            List<Comment> commentList = article.getCommentList();
            if (!CollectionUtils.isEmpty(commentList)) {
                for (Comment comment : commentList) {
                    Integer integer = commentDao.countAllByOwnerCommentId(comment.getId());
                    comment.setNestCommentSize(integer);
                }
            }
        }
        return article;
    }
}
