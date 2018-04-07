package com.community.controller;


import com.community.dao.UserArticleKeepDao;
import com.community.vo.result.BaseResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/userArticleKeep")
public class UserArticleKeepController {
    @Autowired
    private UserArticleKeepDao userArticleKeepDao;

    @RequestMapping(value = "/toggle")
    @ResponseBody
    public BaseResult toggle(@RequestParam("articleId") Integer articleId, HttpServletRequest request) {
        BaseResult result = new BaseResult();
        Integer userId = (Integer) request.getSession().getAttribute("userId");
        if (userId != null) {
            Integer count = userArticleKeepDao.findKeepCountByArticleIdAndUserId(articleId, userId);
            if (count == 0) {
                userArticleKeepDao.insert(articleId, userId);
                result.setMessage("insert");
            } else {
                userArticleKeepDao.delete(articleId, userId);
                result.setMessage("delete");
            }
        }
        result.setSuccess(true);
        return result;
    }
}
