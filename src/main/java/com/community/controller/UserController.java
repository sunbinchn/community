package com.community.controller;

import com.community.entity.User;
import com.community.service.UserService;
import com.community.vo.result.BaseResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Controller
@RequestMapping("/login")
public class UserController {

    @Autowired
    UserService userService;

    @RequestMapping("/index.html")
    public String login() {
        return "login";
    }

    @RequestMapping(value = "/signin", method = RequestMethod.POST)
    @ResponseBody
    public BaseResult signIn(User user, HttpServletRequest request) {
        BaseResult result = new BaseResult();
        if (user == null) {
            result.setSuccess(false);
            result.setMessage("用户名或密码不能为空");
            return result;
        }
        User findUser = userService.findUserByName(user.getUserNameOrEmail());
        if (findUser == null) {
            findUser = userService.findUserByEmail(user.getUserNameOrEmail());
        }
        if (findUser != null) {
            if (findUser.getPassword().equals(user.getPassword())) {
                if (findUser.getIsBan() == 1) { //账号被封，将无法登陆
                    result.setSuccess(false);
                    result.setMessage("当前账号被查封，无法登陆");
                    return result;
                }
                result.setSuccess(true);
                request.getSession().setAttribute("username", findUser.getUserName());
                request.getSession().setAttribute("email", findUser.getEmail());
                request.getSession().setAttribute("userIcon", findUser.getIcon().getUrl());
                request.getSession().setAttribute("userId", findUser.getUserId());
                request.getSession().setAttribute("role", findUser.getRole());
                request.getSession().setAttribute("isShutUp", findUser.getIsShutUp());
            } else {
                result.setSuccess(false);
                result.setMessage("用户名或密码错误");
            }
        } else {
            result.setSuccess(false);
            result.setMessage("用户名或密码错误");
        }
        return result;
    }

    @RequestMapping(value = "/register", method = RequestMethod.POST)
    @ResponseBody
    public BaseResult register(User user) {
        BaseResult result = userService.saveUser(user);
        return result;
    }
    @RequestMapping(value = "/logOut")
    public String logOut(HttpServletRequest request) {
        request.getSession().invalidate();
        return "redirect:/index.html";
    }
}
