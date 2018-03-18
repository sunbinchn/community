package com.community.controller;

import com.community.dao.ArticleDao;
import com.community.entity.Article;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("detail")
public class ArticleDetailController {

    @Autowired
    private ArticleDao articleDao;

    @RequestMapping("/get/{id}")
    public String detail(@PathVariable("id") Integer id, HttpServletRequest request) {
        Article article = articleDao.findById(id);
        request.setAttribute("article", article);
        return "article_detail";
    }
}
