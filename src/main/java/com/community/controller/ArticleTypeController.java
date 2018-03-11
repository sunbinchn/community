package com.community.controller;

import com.community.entity.Article;
import com.community.entity.ArticleType;
import com.community.service.ArticleService;
import com.community.service.ArticleTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping("/articleType")
public class ArticleTypeController {

    @Autowired
    private ArticleTypeService articleTypeService;

    @RequestMapping("/getAll")
    @ResponseBody
    public List<ArticleType> getAll() {
        return articleTypeService.findAll();
    }
}
