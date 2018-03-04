package com.community.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("explore")
public class CategoryListController {


    @RequestMapping("/all")
    public String category_all(HttpServletRequest request) {
        return "category/category_all";
    }

    @RequestMapping("/category-1")
    public String category_1(HttpServletRequest request) {
        return "category/category_1";
    }

    @RequestMapping("/category-2")
    public String category_(HttpServletRequest request) {
        return "category/category_2";
    }

    @RequestMapping("/category-3")
    public String category_3(HttpServletRequest request) {
        return "category/category_3";
    }

    @RequestMapping("/category-4")
    public String category_4(HttpServletRequest request) {
        return "category/category_4";
    }

    @RequestMapping("/category-5")
    public String category_5(HttpServletRequest request) {
        return "category/category_5";
    }

    @RequestMapping("/category-6")
    public String category_6(HttpServletRequest request) {
        return "category/category_6";
    }

    @RequestMapping("/category-7")
    public String category_7(HttpServletRequest request) {
        return "category/category_7";
    }
}
