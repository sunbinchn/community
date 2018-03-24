package com.community.dao;

import com.community.entity.User;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface UserCommentLoveDao {
    List<User> findUserListByCommentId(Integer id);

    Integer findUserSizeByCommentId(Integer id);

    boolean insert(@Param("commentId") Integer commentId, @Param("userId") Integer userId);

    Integer getCountByCommentIdAndUserId(@Param("commentId") Integer commentId, @Param("userId") Integer userId);

    boolean delete(@Param("commentId") Integer commentId, @Param("userId") Integer userId);
}