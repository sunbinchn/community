package com.community.controller;

import com.community.dao.UserArticleKeepDao;
import com.community.dao.UserArticleLoveDao;
import com.community.dao.UserArticleReadDao;
import com.community.dao.UserDao;
import com.community.service.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/userHomePage")
public class UserHomePageController {
    @Autowired
    private ArticleService articleService;
    @Autowired
    private UserDao userDao;
    @Autowired
    private UserArticleReadDao userArticleReadDao;
    @Autowired
    private UserArticleLoveDao userArticleLoveDao;
    @Autowired
    private UserArticleKeepDao userArticleKeepDao;

    private void preSetCount(HttpServletRequest request, Integer userId) {
        request.setAttribute("readCount", userArticleReadDao.countByUserId(userId));
        request.setAttribute("loveCount", userArticleLoveDao.countByUserId(userId));
        request.setAttribute("keepCount", userArticleKeepDao.countByUserId(userId));
    }

    @RequestMapping("{userId}/read")
    public String read(@RequestParam(value = "pn", defaultValue = "1") Integer pn, @PathVariable Integer userId, HttpServletRequest request) {
        preSetCount(request, userId);
        request.setAttribute("userInfo", userDao.findById(userId));
        request.setAttribute("pageInfo", articleService.findReadArticleListByUserId(pn, userId));
        return "user_home_page";
    }

    @RequestMapping("{userId}/love")
    public String love(@RequestParam(value = "pn", defaultValue = "1") Integer pn, @PathVariable Integer userId, HttpServletRequest request) {
        preSetCount(request, userId);
        request.setAttribute("userInfo", userDao.findById(userId));
        request.setAttribute("pageInfo", articleService.findLoveArticleListByUserId(pn, userId));
        return "user_home_page";
    }

    @RequestMapping("{userId}/keep")
    public String keep(@RequestParam(value = "pn", defaultValue = "1") Integer pn, @PathVariable Integer userId, HttpServletRequest request) {
        preSetCount(request, userId);
        request.setAttribute("userInfo", userDao.findById(userId));
        request.setAttribute("pageInfo", articleService.findKeepArticleListByUserId(pn, userId));
        return "user_home_page";
    }

}
