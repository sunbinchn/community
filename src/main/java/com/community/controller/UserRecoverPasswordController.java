package com.community.controller;

import com.community.dao.UserDao;
import com.community.entity.User;
import com.community.utils.EmailUtil;
import com.community.utils.SecurityUtil;
import com.community.vo.UserVo;
import com.community.vo.result.BaseResult;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;

@Controller
@RequestMapping("/userRecoverPassword")
public class UserRecoverPasswordController {
    public static final int TIMELIMIT = 1000 * 60 * 60 * 1; //激活邮件过期时间1小时
    @Autowired
    private UserDao userDao;

    @RequestMapping("index")
    public String index() {
        return "user_recover_password";
    }

    @RequestMapping(value = "perRequest", method = RequestMethod.POST)
    @ResponseBody
    public BaseResult perRequest(@RequestBody User user, HttpServletRequest request) {
        BaseResult result = new BaseResult();
        User byName = userDao.findUserByName(user.getUserName());
        if (byName == null) {
            result.setSuccess(false);
            result.setMessage("用户名或邮箱错误");
            return result;
        }
        final String email = byName.getEmail();
        if (!email.equals(user.getEmail())) {
            result.setSuccess(false);
            result.setMessage("用户名或邮箱错误");
            return result;
        }
        //发送邮件，重置密码
        long curTime = System.currentTimeMillis();
        //过期时间
        long activateTime = curTime + TIMELIMIT;
        String url = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+ request.getContextPath()+"/";
        //激活码
        final String token = SecurityUtil.md5(email + curTime);//邮箱加时间戳
        byName.setToken(token);
        byName.setActivateTime(new Date(activateTime));
        boolean update = userDao.updateTokenAndActivateTime(byName);
        if (update) {
            final String content = "<p>您好：<br>如不是本人操作，请忽略本封邮件。<br>本人操作请在一小时之内点击下列链接进行更改密码操作："
                    + "<br><a href='" + url + "userRecoverPassword/index?token=" + token + "'>"
                    + url + "userRecoverPassword/index?token=" + token + "</a></p>";
            new Thread(new Runnable() {
                @Override
                public void run() {
                    EmailUtil.sendMail(email, "技术分享社区更改密码", content);
                }
            }).start();

            result.setSuccess(true);
        }
        return result;
    }

    @RequestMapping(value = "update", method = RequestMethod.POST)
    @ResponseBody
    public BaseResult update(@RequestBody User user, HttpServletRequest request) {
        BaseResult result = new BaseResult();
        String newPassword = user.getPassword();
        if (StringUtils.isEmpty(newPassword)) {
            result.setSuccess(false);
            result.setMessage("密码不能为空");
            return result;
        }
        String token = user.getToken();
        if (StringUtils.isEmpty(token)) {
            result.setSuccess(false);
            result.setMessage("非法访问！");
            return result;
        }
        User findByToken = userDao.findByToken(token);
        if (findByToken == null) {
            result.setSuccess(false);
            result.setMessage("错误的链接地址");
            return result;
        }
        if (findByToken.getActivateTime().getTime() < new Date().getTime()) {
            result.setSuccess(false);
            result.setMessage("链接已过期请重新开始!");
            return result;
        }
        UserVo userVo = new UserVo();
        userVo.setUserId(findByToken.getUserId());
        userVo.setNewPassword(newPassword);
        if (userDao.updatePassword(userVo)) {
            findByToken.setToken(null);
            findByToken.setActivateTime(null);
            userDao.updateTokenAndActivateTime(findByToken); //清楚token和activateTime
            result.setSuccess(true);
        }
        return result;
    }
}
