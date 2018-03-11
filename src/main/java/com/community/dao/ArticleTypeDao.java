package com.community.dao;

import com.community.entity.ArticleType;

import java.util.List;

public interface ArticleTypeDao {
    List<ArticleType> findAll();

    ArticleType findById(Integer id);
}
