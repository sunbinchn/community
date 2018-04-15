package com.community.controller;

import com.community.dao.UserArticleReadDao;
import com.community.entity.Article;
import com.community.service.ArticleDetailService;
import com.community.service.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Controller
@RequestMapping("detail")
public class ArticleDetailController {

    @Autowired
    private ArticleDetailService articleDetailService;
    @Autowired
    private ArticleService articleService;
    @Autowired
    private UserArticleReadDao userArticleReadDao;

    @RequestMapping("/get/{id}")
    public String detail(@PathVariable("id") Integer articleId, HttpServletRequest request, HttpServletResponse response) throws IOException {
        Article article = articleDetailService.findById(articleId, (Integer)request.getSession().getAttribute("userId"));
        if (article == null) {
            response.sendRedirect("/community/error.html");
            return null;
        }
        articleService.setInterestingUserAndArticleList(request);
        request.setAttribute("article", article);
        Integer userId = (Integer)request.getSession().getAttribute("userId");
        if (userId != null) {
            Integer count = userArticleReadDao.findReadCountByArticleIdAndUserId(articleId, userId);
            if (count == 0) {
                userArticleReadDao.insert(articleId, userId);
            }
        }
        return "article_detail";
    }
}
