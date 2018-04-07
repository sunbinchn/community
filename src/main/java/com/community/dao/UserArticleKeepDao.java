package com.community.dao;

import com.community.entity.User;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface UserArticleKeepDao {

    List<User> findUserListByArticleId(Integer id);

    Integer findKeepCountByArticleIdAndUserId(@Param("articleId") Integer articleId,@Param("userId") Integer userId);

    void insert(@Param("articleId") Integer articleId,@Param("userId") Integer userId);

    void delete(@Param("articleId") Integer articleId,@Param("userId") Integer userId);
}