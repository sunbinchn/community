package com.community.dao;

import com.community.entity.Article;
import com.community.entity.User;
import com.community.vo.ArticleCalculate;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface UserArticleLoveDao {
    List<User> findUserListByArticleId(Integer id);

    Integer findLoveCountByArticleIdAndUserId(@Param("articleId") Integer articleId, @Param("userId") Integer userId);

    boolean insert(@Param("articleId") Integer articleId, @Param("userId") Integer userId);

    boolean delete(@Param("articleId") Integer articleId, @Param("userId") Integer userId);


    List<ArticleCalculate> countGroupByArticleId();

    List<Article> findLoveArticleListByUserId(Integer userId);

    Integer countByUserId(Integer userId);
}