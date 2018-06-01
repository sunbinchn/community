package com.community.controller;

import com.community.dao.ArticleDao;
import com.community.entity.Article;
import com.community.entity.ArticleType;
import com.community.entity.User;
import com.community.service.ArticleTypeService;
import com.community.vo.ArticleVo;
import com.community.vo.result.BaseResult;
import org.jsoup.Jsoup;
import org.jsoup.safety.Whitelist;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
@RequestMapping("/writeArticle")
public class WriteArticleController {
    @Autowired
    private ArticleDao articleDao;
    @Autowired
    private ArticleTypeService articleTypeService;

    @RequestMapping("index")
    public String index(HttpServletRequest request) {
        List<ArticleType> articleTypeList = articleTypeService.findAll();
        request.setAttribute("articleTypeList", articleTypeList);
        return "writeArticle";
    }

    @RequestMapping(value = "get", method = RequestMethod.GET)
    @ResponseBody
    public ArticleVo getArticle(@RequestParam("articleId") Integer articleId, HttpServletRequest request) {
        Integer userId = (Integer)request.getSession().getAttribute("userId");
        Article article = articleDao.findForceById(articleId);
        if (!article.getUser().getUserId().equals(userId)) { //更新别人的文章，不允许
            return null;
        }
        ArticleVo articleVo = new ArticleVo();
        articleVo.setOriginal(article.getOriginal());
        articleVo.setTitle(article.getTitle());
        articleVo.setContent(article.getContent());
        articleVo.setArticleTypeId(article.getArticleType().getId());
        articleVo.setArticleId(article.getId());
        return articleVo;
    }

    @RequestMapping(value = "save", method = RequestMethod.POST)
    @ResponseBody
    public BaseResult save(@RequestBody ArticleVo articleVo, HttpServletRequest request) {
        BaseResult result = new BaseResult();
        Integer userId = (Integer) request.getSession().getAttribute("userId");
        if (userId == null) {
            result.setSuccess(false);
            result.setMessage("未登陆，请先登陆！");
            return result;
        }
        if (articleVo.getArticleId() != null) { //更新需要判断是否是自己的文章
            Article byId = articleDao.findById(articleVo.getArticleId());
            if (!byId.getUser().getUserId().equals(userId)) {
                result.setSuccess(false);
                result.setMessage("只能更新自己的文章");
                return result;
            }
        }
        Article article = new Article();
        User user = new User();
        user.setUserId(userId);
        article.setUser(user);
        article.setTitle(articleVo.getTitle());
        //防止XSS(跨站点脚本)攻击
        article.setContent(Jsoup.clean(articleVo.getContent(), Whitelist.basic()));
        article.setOriginal(articleVo.getOriginal());
        ArticleType articleType = new ArticleType();
        articleType.setId(articleVo.getArticleTypeId());
        article.setArticleType(articleType);
        boolean updateOrInsert;
        if (articleVo.getArticleId() != null) { //update
            article.setId(articleVo.getArticleId());
            updateOrInsert = articleDao.update(article);
        } else { //create
            updateOrInsert = articleDao.insert(article);
        }
        if (updateOrInsert) {
            result.setSuccess(updateOrInsert);
            result.setMessage(String.valueOf(article.getId()));
        }
        return result;
    }
}
