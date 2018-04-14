package com.community.controller.manage;

import com.community.dao.ArticleDao;
import com.community.entity.Article;
import com.community.enums.ArticlePassConstant;
import com.community.utils.PageContants;
import com.community.vo.result.BaseResult;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

import java.util.List;

import static com.community.enums.ArticlePassConstant.*;

@RequestMapping("manage/article")
@Controller
public class ArticleManageController {
    @Autowired
    private ArticleDao articleDao;

    private void commonSetting(Integer pn, HttpServletRequest request, Integer isPassValue) {
        request.setAttribute("SERVER_REQUEST_URL", request.getRequestURL());
        PageHelper.startPage(pn, PageContants.PAGE_SIZE_TEN);
        List<Article> articleList;
        String criteria = request.getParameter("criteria");
        if (StringUtils.isEmpty(criteria)) {
            articleList = articleDao.findAllByIsPass(isPassValue);
        } else {
            articleList = articleDao.findAllByIsPassAndCriteria(isPassValue, criteria.trim());
        }
        request.setAttribute("pageInfo", new PageInfo<>(articleList, PageContants.NAVIGATE_PAGES_FIVE));
    }

    @RequestMapping("index")
    public String index(@RequestParam(value = "pn", defaultValue = "1") Integer pn, HttpServletRequest request) {
        commonSetting(pn, request, UNCHECKED.getValue());
        return "manage/articleManage";
    }
    @RequestMapping("deleted")
    public String deleted(@RequestParam(value = "pn", defaultValue = "1") Integer pn, HttpServletRequest request) {
        commonSetting(pn, request, DELETED.getValue());
        return "manage/articleManage";
    }
    @RequestMapping("checked")
    public String checked(@RequestParam(value = "pn", defaultValue = "1") Integer pn, HttpServletRequest request) {
        commonSetting(pn, request, CHECKED.getValue());
        return "manage/articleManage";
    }
    @RequestMapping("all")
    public String all(@RequestParam(value = "pn", defaultValue = "1") Integer pn, HttpServletRequest request) {
        request.setAttribute("SERVER_REQUEST_URL", request.getRequestURL());
        PageHelper.startPage(pn, PageContants.PAGE_SIZE_TEN);
        List<Article> articleList;
        String criteria = request.getParameter("criteria");
        if (StringUtils.isEmpty(criteria)) {
            articleList = articleDao.findAll();
        } else {
            articleList = articleDao.findAllByCriteria(criteria.trim());
        }
        request.setAttribute("pageInfo", new PageInfo<>(articleList, PageContants.NAVIGATE_PAGES_FIVE));
        return "manage/articleManage";
    }
    @RequestMapping("togglePass/{articleId}")
    @ResponseBody
    public BaseResult togglePass(@PathVariable Integer articleId) {
        BaseResult result = new BaseResult();
        Article article = articleDao.findById(articleId);
        boolean update;
        if (UNCHECKED.getValue().equals(article.getIsPass())) { //未审核
            update = articleDao.updatePassById(CHECKED.getValue(), articleId);
        } else { //已审核
            update = articleDao.updatePassById(UNCHECKED.getValue(), articleId);
        }
        if (update) {
            result.setSuccess(true);
        }
        return result;
    }
    @RequestMapping("delete/{articleId}")
    @ResponseBody
    public BaseResult delete(@PathVariable Integer articleId) {
        BaseResult result = new BaseResult();
        boolean delete = articleDao.updatePassById(DELETED.getValue(), articleId);
        if (delete) {
            result.setSuccess(true);
        }
        return result;
    }

}
