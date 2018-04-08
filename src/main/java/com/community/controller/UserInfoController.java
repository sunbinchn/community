package com.community.controller;

import com.community.dao.UserDao;
import com.community.entity.Image;
import com.community.entity.User;
import com.community.utils.FileUploadUtil;
import com.community.vo.UserVo;
import com.community.vo.result.BaseResult;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

@Controller
@RequestMapping("/userInfo")
public class UserInfoController {
    @Autowired
    private UserDao userDao;


    @RequestMapping("profile")
    public String profile(HttpServletRequest request) {
//        Integer userId = (Integer)request.getSession().getAttribute("userId");
//        User user = userDao.findById(userId);
//        request.setAttribute("userInfo", user);
        return "user_info";
    }

    @RequestMapping("password")
    public String password() {
        return "user_password";
    }

    @RequestMapping(value = "update", method = RequestMethod.POST)
    public String update(@RequestParam("icon") MultipartFile iconFile, HttpServletRequest request) {
        Integer userId = (Integer) request.getSession().getAttribute("userId");
        if (userId == null) {
            return "redirect:/userInfo/index";
        }
        String phone = request.getParameter("phone");
        String email = request.getParameter("email");
        String nickname = request.getParameter("nickname");
        String company = request.getParameter("company");
        String signature = request.getParameter("signature");
        String jobTitle = request.getParameter("jobTitle");
        String iconFileName = null;
        if (iconFile != null && iconFile.getSize() != 0 && StringUtils.isNotEmpty(iconFile.getOriginalFilename())) {
            try {
                iconFileName = FileUploadUtil.uploadFile(iconFile, request);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        User user = userDao.findById(userId);
        if (StringUtils.isNotEmpty(iconFileName)) {
            Image image = new Image();
            image.setUrl(iconFileName);
            user.setIcon(image);
        }
        return "redirect:/userInfo/index";
    }

    @RequestMapping("nickNameQuery")
    @ResponseBody
    public BaseResult nickNameQuery(@RequestParam("nickName") String nickName) {
        BaseResult result = new BaseResult();
        //todo 给User.java加属性，数据库加字段
        Integer count = userDao.findNickName(nickName);
        if (count > 0) {
            result.setSuccess(false);
            result.setMessage("该昵称已被使用！");
        } else {
            result.setSuccess(true);
        }
        return result;
    }

    @RequestMapping(value = "updatePassword", method = RequestMethod.POST)
    @ResponseBody
    public BaseResult updatePassword(@RequestBody UserVo userVo, HttpServletRequest request) {
        BaseResult result = new BaseResult();
        Integer userId = (Integer) request.getSession().getAttribute("userId");
        if (userId == null) {
            result.setSuccess(false);
            result.setMessage("当前用户未登录");
            return result;
        }
        userVo.setUserId(userId);
        User user = userDao.findById(userId);
        if (user != null) {
            if (user.getPassword().equals(userVo.getOldPassword())) {
                boolean update = userDao.updatePassword(userVo);
                if (update) {
                    result.setSuccess(true);
                } else {
                    result.setSuccess(false);
                    result.setMessage("未知错误");
                }
            } else {
                result.setSuccess(false);
                result.setMessage("当前密码不正确");
            }
        } else {
            result.setSuccess(false);
            result.setMessage("当前用户不存在");
        }
        return result;
    }
}
