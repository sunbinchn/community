package com.community.dao;

import com.community.entity.Article;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ArticleDao {

    List<Article> findAll();
    List<Article> findAllByShowType(@Param("showTypeId") Integer showTypeId);

    List<Article> findAllByArticleTypeId(Integer id);

    List<Article> findAllByArticleTypeIdAndShowType(@Param("articleTypeId") Integer id,@Param("showTypeId") Integer showTypeId);

    Article findById(Integer id);

    boolean save(Article article);
}
