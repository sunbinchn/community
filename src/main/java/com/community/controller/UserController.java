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
    public BaseResult signIn(User user, HttpServletRequest request, HttpServletResponse response) {
        BaseResult result = new BaseResult();
        if (user == null) {
            result.setSuccess(false);
            result.setMessage("用户名或密码不能为空");
            return result;
        }
        User findUser = userService.findUserByName(user.getUserName());
        if (findUser != null) {
            if (findUser.getPassword().equals(user.getPassword())) {
                result.setSuccess(true);
                request.getSession().setAttribute("username", findUser.getUserName());
            } else {
                result.setSuccess(false);
                result.setMessage("用户名或密码错误");
            }
        }
        return result;
    }

    @RequestMapping(value = "/register", method = RequestMethod.POST)
    @ResponseBody
    public BaseResult register(User user) {
        BaseResult result = new BaseResult();
        boolean saveUser = userService.saveUser(user);
        if (saveUser) {
            result.setSuccess(true);
        }
        return result;
    }

}
