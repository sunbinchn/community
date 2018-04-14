package com.community.controller.manage;

import com.community.dao.ArticleDao;
import com.community.entity.Article;
import com.community.enums.ArticlePassConstant;
import com.community.utils.PageContants;
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

import static com.community.enums.ArticlePassConstant.*;

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
    @RequestMapping("togglePass/{articleId}")
    @ResponseBody
    public BaseResult togglePass(@PathVariable Integer articleId) {
        BaseResult result = new BaseResult();
        Article article = articleDao.findForceById(articleId);
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
