package com.community.controller;

import com.community.dao.ImageDao;
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
    @Autowired
    private ImageDao imageDao;


    @RequestMapping("profile")
    public String profile(HttpServletRequest request) {
        Integer userId = (Integer) request.getSession().getAttribute("userId");
        User user = userDao.findById(userId);
        request.setAttribute("userInfo", user);
        return "user_info";
    }

    @RequestMapping("password")
    public String password() {
        return "user_password";
    }

    @RequestMapping(value = "update", method = RequestMethod.POST)
    public String update(@RequestParam("icon") MultipartFile iconFile, HttpServletRequest request) {
        Integer userId = (Integer) request.getSession().getAttribute("userId");
        User user = userDao.findById(userId);
        String phone = request.getParameter("phone");
        String email = request.getParameter("email");
        String nickname = request.getParameter("nickname");
        String company = request.getParameter("company");
        String signature = request.getParameter("signature");
        String jobTitle = request.getParameter("jobTitle");
        if (iconFile != null && iconFile.getSize() != 0 && StringUtils.isNotEmpty(iconFile.getOriginalFilename())) {
            try {
                String iconFileName = FileUploadUtil.uploadFile(iconFile, request);
                Image image = new Image();
                image.setUrl(iconFileName);
                boolean insert = imageDao.insert(image);
                if (insert) {
                    user.setIcon(image);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        user.setPhone(phone);
        user.setEmail(email);
        user.setNickname(nickname);
        user.setCompany(company);
        user.setSignature(signature);
        user.setJobTitle(jobTitle);
        boolean update = userDao.update(user);
        return "redirect:/userInfo/profile";
    }

    @RequestMapping("nickNameQuery")
    @ResponseBody
    public BaseResult nickNameQuery(@RequestParam("nickName") String nickName, HttpServletRequest request) {
        Integer userId = (Integer) request.getSession().getAttribute("userId");
        BaseResult result = new BaseResult();
        Integer count = userDao.findNickName(nickName, userId);
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
