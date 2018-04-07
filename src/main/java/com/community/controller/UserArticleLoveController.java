package com.community.controller;


import com.community.dao.UserArticleLoveDao;
import com.community.vo.result.BaseResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/userArticleLove")
public class UserArticleLoveController {
    @Autowired
    private UserArticleLoveDao userArticleLoveDao;

    @RequestMapping(value = "/toggle")
    @ResponseBody
    public BaseResult toggle(@RequestParam("articleId")Integer articleId, HttpServletRequest request) {
        BaseResult result = new BaseResult();
        Integer userId = (Integer) request.getSession().getAttribute("userId");
        if (userId != null) {
            Integer count = userArticleLoveDao.findLoveCountByArticleIdAndUserId(articleId, userId);
            if (count == 0) {
                userArticleLoveDao.insert(articleId, userId);
                result.setMessage("insert");
                //todo 爱心变红
            } else {
                userArticleLoveDao.delete(articleId, userId);
                result.setMessage("delete");
                //todo 取消变红
            }
        }
        result.setSuccess(true);
        return result;
    }
}
