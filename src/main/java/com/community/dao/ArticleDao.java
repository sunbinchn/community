package com.community.dao;

import com.community.entity.Article;
import com.community.entity.User;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ArticleDao {
    List<User> findAllUserByArticleIds(List<Integer> list);

    List<Article> findAll();

    List<Article> findAllByShowType(@Param("showTypeId") Integer showTypeId);

    List<Article> findAllByArticleTypeId(Integer id);

    List<Article> findAllByArticleTypeIdAndShowType(@Param("articleTypeId") Integer id, @Param("showTypeId") Integer showTypeId);

    Article findById(Integer id);

    /**
     * 没有审核的限制
     *
     * @param id
     * @return
     */
    Article findForceById(Integer id);

    boolean insert(Article article);

    boolean update(Article article);

    List<Article> findAllArticleListByArticleIds(List<Integer> articleIds);

    List<Article> findAllByIsPass(Integer isPass);

    List<Article> findAllByIsPassAndCriteria(@Param("isPass") Integer isPass, @Param("criteria") String criteria);

    List<Article> findAllByCriteria(String criteria);

    List<Article> findAllByUserId(Integer userId);

    Integer countByUserId(Integer userId);

    boolean updatePassById(@Param("isPassValue") Integer isPassValue, @Param("articleId") Integer articleId);

    List<Article> findAllByArticleTypeIdAndShowTypeAndQuery(@Param("articleTypeId") Integer articleTypeId, @Param("showTypeId") Integer showTypeId, @Param("query") String query);

    List<Article> findAllByShowTypeAndQuery(@Param("showTypeId") Integer showTypeId, @Param("query") String query);
}
