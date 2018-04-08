package com.community.service;

import com.community.dao.*;
import com.community.entity.Article;
import com.community.enums.ArticleShowTypeConstant;
import com.community.utils.ArticleUtil;
import com.community.vo.ArticleCalculate;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.*;

@Service
public class ArticleService {
    private static final Integer PAGE_SIZE = 10;
    private static final Integer NAVIGATE_PAGES = 5;
    private final static int READ_SCORE = 1;
    private final static int LOVE_SCORE = 3;
    private final static int KEEP_SCORE = 5;
    @Autowired
    private ArticleDao articleDao;
    @Autowired
    private UserArticleReadDao userArticleReadDao;
    @Autowired
    private UserArticleLoveDao userArticleLoveDao;
    @Autowired
    private UserArticleKeepDao userArticleKeepDao;

    public PageInfo<Article> findLoveArticleListByUserId(Integer pn, Integer userId) {
        PageHelper.startPage(pn, PAGE_SIZE);
        PageInfo<Article> pageInfo = new PageInfo<>(userArticleLoveDao.findLoveArticleListByUserId(userId), NAVIGATE_PAGES);
        ArticleUtil.setIsKeepCurrentUser(pageInfo, userId);
        return pageInfo;
    }
    public PageInfo<Article> findKeepArticleListByUserId(Integer pn, Integer userId) {
        PageHelper.startPage(pn, PAGE_SIZE);
        PageInfo<Article> pageInfo = new PageInfo<>(userArticleKeepDao.findLoveArticleListByUserId(userId), NAVIGATE_PAGES);
        ArticleUtil.setIsLovedCurrentUser(pageInfo, userId);
        return pageInfo;
    }
    public PageInfo<Article> findReadArticleListByUserId(Integer pn, Integer userId) {
        PageHelper.startPage(pn, PAGE_SIZE);
        PageInfo<Article> pageInfo = new PageInfo<>(userArticleReadDao.findLoveArticleListByUserId(userId), NAVIGATE_PAGES);
        ArticleUtil.setIsLovedAndkeepCurrentUser(pageInfo, userId);
        return pageInfo;
    }

//    public List<Article> findArticleListByArticleType() {
//        return findArticleListByArticleType(null);
//    }
//
//    public List<Article> findArticleListByArticleType(Integer typeId) {
//        List<Article> list;
//        if (typeId != null) {
//            list = articleDao.findAllByArticleTypeId(typeId);
//        } else {
//            list = articleDao.findAll();
//        }
//        return list;
//    }

    public List<Article> findArticleListByArticleTypeAndShowType(Integer articleTypeId, ArticleShowTypeConstant articleShowTypeConstant) {
        List<Article> list;
        if (articleTypeId != null) {
            list = articleDao.findAllByArticleTypeIdAndShowType(articleTypeId, articleShowTypeConstant.getId());
        } else {
            list = articleDao.findAllByShowType(articleShowTypeConstant.getId());
        }
        return list;
    }

    /**
     * 根据一定算法推荐出用户感兴趣的五个人(未被关注的人)
     * 文章阅读1分，喜爱3分，收藏5分
     * 评论点赞1分
     *
     * @param request
     */
    public void setInterestingUserAndArticleList(HttpServletRequest request) {
        Map<Integer, Integer> map = new HashMap<>();
        List<ArticleCalculate> readList = userArticleReadDao.countGroupByArticleId();
        List<ArticleCalculate> loveList = userArticleLoveDao.countGroupByArticleId();
        List<ArticleCalculate> keepList = userArticleKeepDao.countGroupByArticleId();
        calculate(readList, map, READ_SCORE);
        calculate(loveList, map, LOVE_SCORE);
        calculate(keepList, map, KEEP_SCORE);
        List<Map.Entry<Integer, Integer>> list = new ArrayList(map.entrySet());
        Collections.sort(list, new Comparator<Map.Entry<Integer, Integer>>() {
            public int compare(Map.Entry<Integer, Integer> o1,
                               Map.Entry<Integer, Integer> o2) {
                return o2.getValue().compareTo(o1.getValue());
            }
        });
        List<Integer> articleIds = new ArrayList<>();
        for(Map.Entry<Integer,Integer> mapping:list){
            articleIds.add(mapping.getKey());
        }
        request.setAttribute("interestingUserList", articleDao.findAllUserByArticleIds(articleIds));
        request.setAttribute("interestingArticleList", articleDao.findAllArticleListByArticleIds(articleIds));
//        if (userId == null) { //未登录
//        } else {
//        }
    }

    private void calculate(List<ArticleCalculate> list, Map<Integer, Integer> map, int rate) {
        for (ArticleCalculate articleCalculate : list) {
            Integer articleId = articleCalculate.getArticleId();
            Integer amount = articleCalculate.getAmount();
            if (map.get(articleId) == null) {
                map.put(articleId, amount * rate);
            } else {
                map.put(articleId, map.get(articleId) + amount * rate);
            }
        }
    }
}
