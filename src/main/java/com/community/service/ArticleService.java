package com.community.service;

import com.community.dao.ArticleDao;
import com.community.dao.UserDao;
import com.community.entity.Article;
import com.community.entity.User;
import com.community.enums.ArticleShowTypeConstant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ArticleService {
    @Autowired
    private ArticleDao articleDao;
    @Autowired
    private UserDao userDao;

    public List<Article> findArticleListByArticleType() {
        return findArticleListByArticleType(null);
    }

    public List<Article> findArticleListByArticleType(Integer typeId) {
        List<Article> list;
        if (typeId != null) {
            list = articleDao.findAllByArticleTypeId(typeId);
        } else {
            list = articleDao.findAll();
        }
        return list;
    }

    public List<Article> findArticleListByArticleTypeAndShowType(Integer articleTypeId, ArticleShowTypeConstant articleShowTypeConstant) {
        List<Article> list;
        if (articleTypeId != null) {
            list = articleDao.findAllByArticleTypeIdAndShowType(articleTypeId, articleShowTypeConstant.getId());
        } else {
            list = articleDao.findAllByShowType(articleShowTypeConstant.getId());
        }
        return list;
    }

    /**
     * 根据一定算法推荐出用户感兴趣的五个人(未被关注的人)
     * 文章阅读1分，喜爱3分，收藏5分
     * 评论点赞1分
     * @param userId
     * @return
     */
    public List<User> getInterestingUserList(Integer userId) {
        if (userId == null) { //未登录

        } else {

        }
        return null;
    }
}
