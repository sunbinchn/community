package com.community.service;

import com.community.dao.ArticleDao;
import com.community.entity.Article;
import com.community.enums.ArticleShowTypeConstant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ArticleService {
    @Autowired
    private ArticleDao articleDao;

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
}
