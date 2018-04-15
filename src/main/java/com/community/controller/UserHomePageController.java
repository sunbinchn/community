package com.community.controller;

import com.community.dao.*;
import com.community.entity.User;
import com.community.entity.UserRelation;
import com.community.service.ArticleService;
import com.community.utils.PageContants;
import com.community.vo.UserRelationVo;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

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
    private ArticleDao articleDao;
    @Autowired
    private UserArticleReadDao userArticleReadDao;
    @Autowired
    private UserArticleLoveDao userArticleLoveDao;
    @Autowired
    private UserArticleKeepDao userArticleKeepDao;

    private void commonSetting(HttpServletRequest request, Integer userId) {
        UserRelationVo userRelationVo = new UserRelationVo();
        userRelationVo.setIdolCount(userRelationDao.countAllByUserId(userId));
        userRelationVo.setFansCount(userRelationDao.countAllByTargetUserId(userId));
        userRelationVo.setIdolOfCurrentUser(isIdolOfCurrentUser(request, userId));
        request.setAttribute("userRelationVo", userRelationVo);
        request.setAttribute("userInfo", userDao.findById(userId));
        request.setAttribute("readCount", userArticleReadDao.countByUserId(userId));
        request.setAttribute("loveCount", userArticleLoveDao.countByUserId(userId));
        request.setAttribute("keepCount", userArticleKeepDao.countByUserId(userId));
        request.setAttribute("articleCount", articleDao.countByUserId(userId));
        request.setAttribute("SERVER_REQUEST_URL", request.getRequestURL());
        if (userId.equals(request.getSession().getAttribute("userId"))) {
            request.setAttribute("notPassArticleCount", articleDao.countNotPassByUserId(userId));
        }
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
        request.setAttribute("pageInfo", articleService.findReadArticleListByUserId(pn, userId, request));
        return "user_home_page";
    }

    @RequestMapping("{userId}/love")
    public String love(@RequestParam(value = "pn", defaultValue = "1") Integer pn, @PathVariable Integer userId, HttpServletRequest request) {
        commonSetting(request, userId);
        request.setAttribute("pageInfo", articleService.findLoveArticleListByUserId(pn, userId, request));
        return "user_home_page";
    }

    @RequestMapping("{userId}/keep")
    public String keep(@RequestParam(value = "pn", defaultValue = "1") Integer pn, @PathVariable Integer userId, HttpServletRequest request) {
        commonSetting(request, userId);
        request.setAttribute("pageInfo", articleService.findKeepArticleListByUserId(pn, userId, request));
        return "user_home_page";
    }

    @RequestMapping("{userId}/article")
    public String article(@RequestParam(value = "pn", defaultValue = "1") Integer pn, @PathVariable Integer userId, HttpServletRequest request) {
        commonSetting(request, userId);
        request.setAttribute("pageInfo", articleService.findAllByUserId(pn, userId, request));
        return "user_home_page";
    }
    @RequestMapping("{userId}/notPassOfArticle")
    public String notPassOfArticle(@RequestParam(value = "pn", defaultValue = "1") Integer pn, @PathVariable Integer userId, HttpServletRequest request) {
        commonSetting(request, userId);
        //todo pageInfo -> notPassPageInfo就可以共用一个页面了
        request.setAttribute("notPassPageInfo", articleService.findNotPassAllByUserId(pn, userId));
        return "user_home_page";
    }

    @RequestMapping("{userId}/idol")
    public String idol(@RequestParam(value = "pn", defaultValue = "1") Integer pn, @PathVariable Integer userId, HttpServletRequest request) {
        commonSetting(request, userId);
        UserRelationVo userRelationVo = (UserRelationVo) request.getAttribute("userRelationVo");
        PageHelper.startPage(pn, PageContants.PAGE_SIZE_TEN);
        PageInfo<User> userPageInfo = new PageInfo<>(userRelationDao.findAllByUserId(userId), PageContants.NAVIGATE_PAGES_FIVE);
        for (User user : userPageInfo.getList()) {
            for (User idolOfCurrentUser : findIdolListOfcurrentUser(request)) {
                if (user.getUserId().equals(idolOfCurrentUser.getUserId())) {
                    user.setIsIdolOfCurrentUser(true);
                    break;
                }
            }
        }
        userRelationVo.setIdolUserPageInfo(userPageInfo);
        return "user_home_page";
    }

    @RequestMapping("{userId}/fans")
    public String fans(@RequestParam(value = "pn", defaultValue = "1") Integer pn, @PathVariable Integer userId, HttpServletRequest request) {
        commonSetting(request, userId);
        UserRelationVo userRelationVo = (UserRelationVo) request.getAttribute("userRelationVo");
        PageHelper.startPage(pn, PageContants.PAGE_SIZE_TEN);
        PageInfo<User> userPageInfo = new PageInfo<>(userRelationDao.findAllByTargetUserId(userId), PageContants.NAVIGATE_PAGES_FIVE);
        for (User user : userPageInfo.getList()) {
            for (User idolOfCurrentUser : findIdolListOfcurrentUser(request)) {
                if (user.getUserId().equals(idolOfCurrentUser.getUserId())) {
                    user.setIsIdolOfCurrentUser(true);
                    break;
                }
            }
        }
        userRelationVo.setFansUserPageInfo(userPageInfo);
        return "user_home_page";
    }

    /**
     * 需要判断该主页用户的关注/粉丝集合是否是当前用户的关注对象
     *
     * @param request
     */
    private List<User> findIdolListOfcurrentUser(HttpServletRequest request) {
        Integer currentUserId = (Integer) request.getSession().getAttribute("userId");
        if (currentUserId == null) {
            return new ArrayList<>();
        } else {
            return userRelationDao.findAllByUserId(currentUserId);
        }
    }

}
