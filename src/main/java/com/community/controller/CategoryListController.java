package com.community.controller;

import com.community.entity.Article;
import com.community.enums.ArticleShowTypeConstant;
import com.community.enums.ArticleTypeConstant;
import com.community.service.ArticleService;
import com.community.service.ArticleTypeService;
import com.community.utils.PageUtils;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
@RequestMapping("explore")
public class CategoryListController {

    private static final Integer PAGE_SIZE = 10;
    private static final Integer NAVIGATE_PAGES = 5;
    @Autowired
    private ArticleService articleService;

    @Autowired
    private ArticleTypeService articleTypeService;

    private void commonSetting(HttpServletRequest request, Integer pn, Integer articleTypeId, String showType) {
        ArticleShowTypeConstant articleShowTypeConstant = ArticleShowTypeConstant.LATEST;
        for (ArticleShowTypeConstant typeConstant : ArticleShowTypeConstant.values()) {
            if (typeConstant.getName().equals(showType)) {
                articleShowTypeConstant = typeConstant;
            }
        }
        request.setAttribute("SERVER_REQUEST_URL", request.getRequestURL()); //在jsp中由于服务器内部转发--request.getRequestURL()http://localhost:8888/community/WEB-INF/jsp/category/category_all.jsp?pn=2
        request.setAttribute("articleTypeList", articleTypeService.findAll());
        PageHelper.startPage(pn, PAGE_SIZE); // PageHelper 只对紧跟着的第一个 SQL 语句起作用
        PageInfo<Article> pageInfo;
//        pageInfo = new PageInfo<>(articleService.findArticleListByArticleType(articleTypeId), NAVIGATE_PAGES);
        pageInfo = new PageInfo<>(articleService.findArticleListByArticleTypeAndShowType(articleTypeId, articleShowTypeConstant), NAVIGATE_PAGES);
        request.setAttribute("pageInfo", pageInfo);
    }

    @RequestMapping("/all/{showType}")
    public String category_all(@RequestParam(value = "pn", defaultValue = "1") Integer pn, @PathVariable String showType, HttpServletRequest request) {
        commonSetting(request, pn, null, showType);
        return "category/category_all";
    }

    @RequestMapping("/category-1/{showType}")
    public String category_1(@RequestParam(value = "pn", defaultValue = "1") Integer pn, @PathVariable String showType, HttpServletRequest request) {
        commonSetting(request, pn, ArticleTypeConstant.OPEN_SOURCE.getId(), showType);
        return "category/category_1";
    }

    @RequestMapping("/category-2/{showType}")
    public String category_2(@RequestParam(value = "pn", defaultValue = "1") Integer pn, @PathVariable String showType, HttpServletRequest request) {
        commonSetting(request, pn, ArticleTypeConstant.DATABSE.getId(), showType);
        return "category/category_2";
    }

    @RequestMapping("/category-3/{showType}")
    public String category_3(@RequestParam(value = "pn", defaultValue = "1") Integer pn, @PathVariable String showType, HttpServletRequest request) {
        commonSetting(request, pn, ArticleTypeConstant.CLOUD_COMPUTING.getId(), showType);
        return "category/category_3";
    }

    @RequestMapping("/category-4/{showType}")
    public String category_4(@RequestParam(value = "pn", defaultValue = "1") Integer pn, @PathVariable String showType, HttpServletRequest request) {
        commonSetting(request, pn, ArticleTypeConstant.ARTIFICIA_SELF_ENERGY.getId(), showType);
        return "category/category_4";
    }

    @RequestMapping("/category-5/{showType}")
    public String category_5(@RequestParam(value = "pn", defaultValue = "1") Integer pn, @PathVariable String showType, HttpServletRequest request) {
        commonSetting(request, pn, ArticleTypeConstant.FRONT.getId(), showType);
        return "category/category_5";
    }

    @RequestMapping("/category-6/{showType}")
    public String category_6(@RequestParam(value = "pn", defaultValue = "1") Integer pn, @PathVariable String showType, HttpServletRequest request) {
        commonSetting(request, pn, ArticleTypeConstant.BACK.getId(), showType);
        return "category/category_6";
    }

    @RequestMapping("/category-7/{showType}")
    public String category_7(@RequestParam(value = "pn", defaultValue = "1") Integer pn, @PathVariable String showType, HttpServletRequest request) {
        commonSetting(request, pn, ArticleTypeConstant.RESOURCES.getId(), showType);
        return "category/category_7";
    }

    @RequestMapping("/category-8/{showType}")
    public String category_8(@RequestParam(value = "pn", defaultValue = "1") Integer pn, @PathVariable String showType, HttpServletRequest request) {
        commonSetting(request, pn, ArticleTypeConstant.JOB.getId(), showType);
        return "category/category_8";
    }

}
