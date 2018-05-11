package com.community.controller.manage;

import com.community.dao.ArticleTypeDao;
import com.community.entity.ArticleType;
import com.community.utils.PageContants;
import com.community.vo.ArticleTypeVo;
import com.community.vo.result.BaseResult;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

@RequestMapping("manage/articleType")
@Controller
public class ArticleTypeManageController {

    @Autowired
    private ArticleTypeDao articleTypeDao;

    @RequestMapping("index")
    public String index(@RequestParam(value = "pn", defaultValue = "1") Integer pn, HttpServletRequest request) {
        PageHelper.startPage(pn, PageContants.PAGE_SIZE_TEN);
        PageInfo<com.community.entity.ArticleType> pageInfo;
        pageInfo = new PageInfo<>(articleTypeDao.findAll(), PageContants.NAVIGATE_PAGES_FIVE);
        request.setAttribute("pageInfo", pageInfo);
        return "manage/articleTypeManage";
    }

    @RequestMapping("updateName")
    @ResponseBody
    public BaseResult updateName(@RequestBody ArticleTypeVo vo, HttpServletRequest request) {
        BaseResult result = new BaseResult();
        if (StringUtils.isEmpty(vo.getName())) {
            result.setSuccess(false);
            result.setMessage("类型名称不能为空");
            return result;
        }
        if (articleTypeDao.findByName(vo.getName()) != null) {
            result.setSuccess(false);
            result.setMessage("该名称已经存在！请重新输入");
            return result;
        }
        com.community.entity.ArticleType articleType = articleTypeDao.findById(vo.getId());
        if (articleType != null) {
            articleType.setName(vo.getName());
            if (articleTypeDao.update(articleType)) {
                result.setSuccess(true);
            }
        }
        return result;
    }

    @RequestMapping("updatePriority")
    @ResponseBody
    public BaseResult updatePriority(@RequestBody ArticleTypeVo vo, HttpServletRequest request) {
        BaseResult result = new BaseResult();
        com.community.entity.ArticleType articleType = articleTypeDao.findById(vo.getId());
        if (articleType != null) {
            articleType.setPriority(vo.getPriority());
            if (articleTypeDao.update(articleType)) {
                result.setSuccess(true);
            }
        }
        return result;
    }

    @RequestMapping("updateDescription")
    @ResponseBody
    public BaseResult updateDescription(@RequestBody ArticleTypeVo vo, HttpServletRequest request) {
        BaseResult result = new BaseResult();
        com.community.entity.ArticleType articleType = articleTypeDao.findById(vo.getId());
        if (articleType != null) {
            articleType.setDescription(vo.getDescription());
            if (articleTypeDao.update(articleType)) {
                result.setSuccess(true);
            }
        }
        return result;
    }
    @RequestMapping("findByName")
    @ResponseBody
    public BaseResult findByName(@RequestParam("name") String name, HttpServletRequest request) {
        BaseResult result = new BaseResult();
        if(articleTypeDao.findByName(name) != null) {
            result.setSuccess(true);
            result.setMessage("该名称已存在");
        }
        return result;
    }

    @RequestMapping("insert")
    @ResponseBody
    public BaseResult insert(@RequestBody ArticleType articleTypeVo, HttpServletRequest request) {
        BaseResult result = new BaseResult();
        if (StringUtils.isEmpty(articleTypeVo.getName())) {
            result.setSuccess(false);
            result.setMessage("类型名称不能为空");
            return result;
        }
        if (articleTypeDao.findByName(articleTypeVo.getName()) != null) {
            result.setSuccess(false);
            result.setMessage("该名称已经存在！请重新输入");
            return result;
        }
        if (articleTypeDao.insert(articleTypeVo)) {
            result.setSuccess(true);
        }
        return result;
    }
}
