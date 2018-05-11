package com.community.service;

import com.community.dao.ArticleTypeDao;
import com.community.entity.ArticleType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ArticleTypeService {
    @Autowired
    private ArticleTypeDao articleTypeDao;

    public List<ArticleType> findAll() {
        return articleTypeDao.findAll();
    }
}
