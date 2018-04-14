package com.community.service;

import com.community.dao.ArticleDao;
import com.community.dao.CommentDao;
import com.community.dao.UserDao;
import com.community.entity.Article;
import com.community.entity.Comment;
import com.community.entity.User;
import com.community.enums.UserRoleConstant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.List;

@Service
public class ArticleDetailService {
    @Autowired
    private ArticleDao articleDao;
    @Autowired
    private UserDao userDao;
    @Autowired
    private CommentDao commentDao;

    public Article findById(Integer id, Integer currentUserId) {
        Article article;
        article = articleDao.findForceById(id);
        boolean currentUserIsAdmin = false;
        if (currentUserId != null) {
            User currentUser = userDao.findById(currentUserId);
            if (UserRoleConstant.ADMIN.getId().equals(currentUser.getRole())) { //管理员可以看到所有的文章
                currentUserIsAdmin = true;
            }
        }
        if (article.getIsPass() != 1 && !article.getUser().getUserId().equals(currentUserId) && !currentUserIsAdmin) { //审核未通过且不是自己的文章且不是管理员
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
