package com.community.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;

@Controller
public class HomePageController {

    @RequestMapping("/index.html")
    public String index(HttpServletRequest request) {
        return "redirect:/explore/all/latest";
    }
    @RequestMapping("/error.html")
    public String error(HttpServletRequest request) {
        return "error";
    }
}
