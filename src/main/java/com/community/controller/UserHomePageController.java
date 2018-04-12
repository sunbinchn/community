package com.community.controller;

import com.community.dao.*;
import com.community.entity.User;
import com.community.entity.UserRelation;
import com.community.service.ArticleService;
import com.community.vo.UserRelationVo;
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
    private UserRelationDao userRelationDao;
    @Autowired
    private UserArticleReadDao userArticleReadDao;
    @Autowired
    private UserArticleLoveDao userArticleLoveDao;
    @Autowired
    private UserArticleKeepDao userArticleKeepDao;

    private void commonSetting(HttpServletRequest request, Integer userId) {
        UserRelationVo userRelationVo = new UserRelationVo();
        userRelationVo.setIdolUserList(userRelationDao.findAllByUserId(userId));
        userRelationVo.setFansUserList(userRelationDao.findAllByTargetUserId(userId));
        userRelationVo.setIdolOfCurrentUser(isIdolOfCurrentUser(request, userId));
        request.setAttribute("userRelationVo",userRelationVo);
        request.setAttribute("readCount", userArticleReadDao.countByUserId(userId));
        request.setAttribute("loveCount", userArticleLoveDao.countByUserId(userId));
        request.setAttribute("keepCount", userArticleKeepDao.countByUserId(userId));
    }
    private boolean isIdolOfCurrentUser(HttpServletRequest request, Integer userId) {
        UserRelation userRelation = new UserRelation();
        User user = new User();
        user.setUserId((Integer) request.getSession().getAttribute("userId"));
        userRelation.setUser(user);
        User targetUser = new User();
        targetUser.setUserId(userId);
        userRelation.setTargetUser(targetUser);
        if (userRelationDao.find(userRelation) >= 1) {
            return true;
        }
        return false;
    }

    @RequestMapping("{userId}/read")
    public String read(@RequestParam(value = "pn", defaultValue = "1") Integer pn, @PathVariable Integer userId, HttpServletRequest request) {
        commonSetting(request, userId);
        request.setAttribute("userInfo", userDao.findById(userId));
        request.setAttribute("pageInfo", articleService.findReadArticleListByUserId(pn, userId));
        return "user_home_page";
    }

    @RequestMapping("{userId}/love")
    public String love(@RequestParam(value = "pn", defaultValue = "1") Integer pn, @PathVariable Integer userId, HttpServletRequest request) {
        commonSetting(request, userId);
        request.setAttribute("userInfo", userDao.findById(userId));
        request.setAttribute("pageInfo", articleService.findLoveArticleListByUserId(pn, userId));
        return "user_home_page";
    }

    @RequestMapping("{userId}/keep")
    public String keep(@RequestParam(value = "pn", defaultValue = "1") Integer pn, @PathVariable Integer userId, HttpServletRequest request) {
        commonSetting(request, userId);
        request.setAttribute("userInfo", userDao.findById(userId));
        request.setAttribute("pageInfo", articleService.findKeepArticleListByUserId(pn, userId));
        return "user_home_page";
    }

}
