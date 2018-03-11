package com.community.controller;

import com.community.entity.Article;
import com.community.service.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping("/article")
public class ArticleController {

    @Autowired
    private ArticleService articleService;

    @RequestMapping("/getAll")
    @ResponseBody
    public List<Article> getAll() {
        return  articleService.findArticleListByArticleType();
    }

    @RequestMapping("/getAll/{typeId}")
    @ResponseBody
    public List<Article> getAll(@PathVariable Integer typeId) {
        return articleService.findArticleListByArticleType(typeId);
    }

}
