package com.community.dao;

import com.community.entity.Article;
import com.community.entity.User;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface ArticleDao {
    List<User> findAllUserByArticleIds(List<Integer> list);

    List<Article> findAll();

    List<Article> findAllByShowType(@Param("showTypeId") Integer showTypeId);

    List<Article> findAllByArticleTypeId(Integer id);

    List<Article> findAllByArticleTypeIdAndShowType(@Param("articleTypeId") Integer id, @Param("showTypeId") Integer showTypeId);

    Article findById(Integer id);

    boolean insert(Article article);

    boolean update(Article article);

    List<Article> findAllArticleListByArticleIds(List<Integer> articleIds);
}
