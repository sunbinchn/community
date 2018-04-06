package com.community.controller.manage;

import com.community.dao.UserDao;
import com.community.entity.User;
import com.community.vo.result.BaseResult;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

@RequestMapping("manage/user")
@Controller
public class UserManageController {
    private static final Integer PAGE_SIZE = 15;
    private static final Integer NAVIGATE_PAGES = 5;
    @Autowired
    private UserDao userDao;

    @RequestMapping("index")
    public String index(@RequestParam(value = "pn", defaultValue = "1") Integer pn, HttpServletRequest request) {
        Integer role = 2;
        PageHelper.startPage(pn, PAGE_SIZE); // PageHelper 只对紧跟着的第一个 SQL 语句起作用
        PageInfo<User> pageInfo;
        pageInfo = new PageInfo<>(userDao.findAllByRole(role), NAVIGATE_PAGES);
        request.setAttribute("pageInfo", pageInfo);
        return "manage/userManage";
    }

    @RequestMapping("shutUp/{userId}")
    @ResponseBody
    public BaseResult shutUp(@PathVariable("userId") Integer userId) {
        BaseResult result = new BaseResult();
        if (userDao.shutUpByUserId(userId)) {
            result.setSuccess(true);
        } else {
            result.setSuccess(false);
            result.setMessage("操作失败");
        }
        return result;
    }

    @RequestMapping("ban/{userId}")
    @ResponseBody
    public BaseResult ban(@PathVariable("userId") Integer userId) {
        BaseResult result = new BaseResult();
        if (userDao.banByUserId(userId)) {
            result.setSuccess(true);
        } else {
            result.setSuccess(false);
            result.setMessage("操作失败");
        }
        return result;
    }

    @RequestMapping("cancel/shutUp/{userId}")
    @ResponseBody
    public BaseResult cancelShutUp(@PathVariable("userId") Integer userId) {
        BaseResult result = new BaseResult();
        if (userDao.cancelShutUpByUserId(userId)) {
            result.setSuccess(true);
        } else {
            result.setSuccess(false);
            result.setMessage("操作失败");
        }
        return result;
    }

    @RequestMapping("cancel/ban/{userId}")
    @ResponseBody
    public BaseResult cancelBan(@PathVariable("userId") Integer userId) {
        BaseResult result = new BaseResult();
        if (userDao.cancelBanByUserId(userId)) {
            result.setSuccess(true);
        } else {
            result.setSuccess(false);
            result.setMessage("操作失败");
        }
        return result;
    }
}
