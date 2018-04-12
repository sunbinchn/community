package com.community.controller;

import com.community.dao.UserRelationDao;
import com.community.entity.User;
import com.community.entity.UserRelation;
import com.community.vo.result.BaseResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/userRelation")
public class UserRelationController {
    @Autowired
    private UserRelationDao userRelationDao;

    @RequestMapping("/toggle/{targetUserId}")
    @ResponseBody
    public BaseResult toggleRelation(@PathVariable Integer targetUserId, HttpServletRequest request) {
        BaseResult result = new BaseResult();
        Integer userId = (Integer) request.getSession().getAttribute("userId");
        if (userId != null) {
            UserRelation userRelation = new UserRelation();
            User user = new User();
            user.setUserId(userId);
            userRelation.setUser(user);
            User targetUser = new User();
            targetUser.setUserId(targetUserId);
            userRelation.setTargetUser(targetUser);
            if (userRelationDao.find(userRelation) >= 1) { //取消关注
                result.setSuccess(userRelationDao.delete(userRelation));
                result.setMessage("cancel");
            } else { //关注
                result.setSuccess(userRelationDao.insert(userRelation));
            }
        }
        return result;
    }
}
