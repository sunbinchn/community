package com.community.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;

@Controller
public class HomePageController {

    @RequestMapping("/index.html")
    public String index(HttpServletRequest request) {
        return "forward:/explore/all/latest";
    }
}
