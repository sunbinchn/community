package com.community.dao;

import com.community.entity.User;
import com.community.vo.UserVo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface UserDao {

    int insert(User user);

    User findUserByName(String userName);

    User findUserByEmail(String email);

    User findById(Integer id);

    List<User> findAllByRole(@Param("role") Integer role);

    boolean shutUpByUserId(Integer id);

    boolean banByUserId(Integer id);

    boolean cancelShutUpByUserId(Integer userId);

    boolean cancelBanByUserId(Integer userId);

    /**
     * 不包括自己已经用的昵称
     * @param nickname
     * @param userId
     * @return
     */
    Integer findNickName(@Param("nickname") String nickname,@Param("userId") Integer userId);

    boolean updatePassword(UserVo userVo);

    boolean update(User user);

    boolean updateTokenAndActivateTime(User byName);

    User findByToken(String token);
}
