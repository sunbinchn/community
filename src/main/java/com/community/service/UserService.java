package com.community.service;

import com.community.dao.UserDao;
import com.community.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    UserDao userDao;

    public boolean saveUser(User user) {
        int insert = userDao.insert(user);
        if (insert == 1) {
            return true;
        }
        return false;
    }

    public User findUserByName(String userName) {
        return userDao.findUserByName(userName);
    }
}
