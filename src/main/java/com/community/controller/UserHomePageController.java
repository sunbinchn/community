package com.community.controller;

import com.community.dao.UserDao;
import com.community.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/userHomePage")
public class UserHomePageController {
    @Autowired
    private UserDao userDao;


    @RequestMapping("myHome")
    public String index(HttpServletRequest request) {
        Integer userId = (Integer)request.getSession().getAttribute("userId");
        User user = userDao.findById(userId);
        request.setAttribute("userInfo", user);
        return "user_home_page";
    }

}
