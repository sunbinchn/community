package com.community.dao;

import com.community.entity.ArticleType;

import java.util.List;

public interface ArticleTypeDao {
    /**
     * 按照优先级排序
     * @return
     */
    List<ArticleType> findAll();

    ArticleType findById(Integer id);

    ArticleType findByName(String name);

    boolean update(ArticleType articleType);

    boolean insert(ArticleType articleType);
}
