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

    Integer findNickName(String nickName);

    boolean updatePassword(UserVo userVo);
}
