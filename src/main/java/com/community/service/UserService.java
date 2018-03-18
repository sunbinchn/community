package com.community.service;

import com.community.dao.UserDao;
import com.community.entity.Image;
import com.community.entity.User;
import com.community.vo.result.BaseResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    UserDao userDao;

    public BaseResult saveUser(User user) {
        BaseResult result = new BaseResult();
        User userByEmail = userDao.findUserByEmail(user.getEmail());
        if (userByEmail != null) {
            result.setSuccess(false);
            result.setMessage("该邮箱已被注册");
            return result;
        }
        User userByName = userDao.findUserByName(user.getUserName());
        if (userByName != null) {
            result.setSuccess(false);
            result.setMessage("该用户名已存在");
            return result;
        }
        Image image = new Image();
        image.setId(2); //id 为2的图片是默认的头像
        user.setIcon(image);
        int insert = userDao.insert(user);
        if (insert == 1) {
            result.setSuccess(true);
        }
        return result;
    }

    public User findUserByName(String userName) {
        return userDao.findUserByName(userName);
    }

    public User findUserByEmail(String email) {
        return userDao.findUserByEmail(email);
    }
}
