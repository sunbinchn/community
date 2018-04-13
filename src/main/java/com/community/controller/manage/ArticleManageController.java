package com.community.controller.manage;

import com.community.dao.ArticleDao;
import com.community.utils.PageContants;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;

@RequestMapping("manage/article")
@Controller
public class ArticleManageController {
    @Autowired
    private ArticleDao articleDao;

    @RequestMapping("index")
    public String index(@RequestParam(value = "pn", defaultValue = "1") Integer pn, HttpServletRequest request) {
        PageHelper.startPage(pn, PageContants.PAGE_SIZE_FIFTEEN);
        request.setAttribute("pageInfo", new PageInfo<>(articleDao.findNotIsPassAll(), PageContants.NAVIGATE_PAGES_FIVE));
        return "manage/articleManage";
    }

}
