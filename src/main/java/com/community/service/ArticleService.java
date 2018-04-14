package com.community.service;

import com.community.dao.ArticleDao;
import com.community.dao.UserArticleKeepDao;
import com.community.dao.UserArticleLoveDao;
import com.community.dao.UserArticleReadDao;
import com.community.entity.Article;
import com.community.entity.User;
import com.community.enums.ArticleShowTypeConstant;
import com.community.utils.ArticleUtil;
import com.community.vo.ArticleCalculate;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import javax.servlet.http.HttpServletRequest;
import java.util.*;

import static com.community.utils.PageContants.NAVIGATE_PAGES_FIVE;
import static com.community.utils.PageContants.PAGE_SIZE_TEN;

@Service
public class ArticleService {
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

    public PageInfo<Article> findLoveArticleListByUserId(Integer pn, Integer userId, HttpServletRequest request) {
        PageHelper.startPage(pn, PAGE_SIZE_TEN);
        PageInfo<Article> pageInfo = new PageInfo<>(userArticleLoveDao.findLoveArticleListByUserId(userId), NAVIGATE_PAGES_FIVE);
        if (request.getSession().getAttribute("userId") != null) {
            ArticleUtil.setIsKeepCurrentUser(pageInfo, (Integer)request.getSession().getAttribute("userId"));
        }
        return pageInfo;
    }
    public PageInfo<Article> findKeepArticleListByUserId(Integer pn, Integer userId, HttpServletRequest request) {
        PageHelper.startPage(pn, PAGE_SIZE_TEN);
        PageInfo<Article> pageInfo = new PageInfo<>(userArticleKeepDao.findLoveArticleListByUserId(userId), NAVIGATE_PAGES_FIVE);
        if (request.getSession().getAttribute("userId") != null) {
            ArticleUtil.setIsLovedCurrentUser(pageInfo, (Integer)request.getSession().getAttribute("userId"));
        }
        return pageInfo;
    }
    public PageInfo<Article> findReadArticleListByUserId(Integer pn, Integer userId, HttpServletRequest request) {
        PageHelper.startPage(pn, PAGE_SIZE_TEN);
        PageInfo<Article> pageInfo = new PageInfo<>(userArticleReadDao.findLoveArticleListByUserId(userId), NAVIGATE_PAGES_FIVE);
        if (request.getSession().getAttribute("userId") != null) {
            ArticleUtil.setIsLovedAndkeepCurrentUser(pageInfo, (Integer)request.getSession().getAttribute("userId"));
        }
        return pageInfo;
    }

    public List<Article> findArticleListByArticleTypeAndShowType(Integer articleTypeId, ArticleShowTypeConstant articleShowTypeConstant, String query) {
        if (query != null) {
            query = query.trim();
        }
        List<Article> list;
        Integer showTypeId = articleShowTypeConstant.getId();
        if (articleTypeId != null) { //文章类型和最新/最热/推荐
            if (StringUtils.isEmpty(query)) {
                list = articleDao.findAllByArticleTypeIdAndShowType(articleTypeId, showTypeId);
            } else { //有过滤条件
                list = articleDao.findAllByArticleTypeIdAndShowTypeAndQuery(articleTypeId, showTypeId, query);
            }
        } else {//文章类型
            if (StringUtils.isEmpty(query)) {
                list = articleDao.findAllByShowType(showTypeId);
            } else { //有过滤条件
                list = articleDao.findAllByShowTypeAndQuery(showTypeId, query);
            }
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
        if (!CollectionUtils.isEmpty(articleIds)) {
            List<User> allUserByArticleIds = articleDao.findAllUserByArticleIds(articleIds);
            Integer currentUserId = (Integer)request.getSession().getAttribute("userId");
            for (User user : allUserByArticleIds) { //排除看到自己
                if (user.getUserId().equals(currentUserId)) {
                    allUserByArticleIds.remove(user);
                    break;
                }
            }
            allUserByArticleIds = allUserByArticleIds.size() > 5 ? allUserByArticleIds.subList(0, 5) : allUserByArticleIds;
            request.setAttribute("interestingUserList", allUserByArticleIds);
            request.setAttribute("interestingArticleList", articleDao.findAllArticleListByArticleIds(articleIds));
        }
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

    public PageInfo<Article> findAllByUserId(Integer pn, Integer userId, HttpServletRequest request) {
        PageHelper.startPage(pn, PAGE_SIZE_TEN);
        PageInfo<Article> pageInfo = new PageInfo<>(articleDao.findAllByUserId(userId), NAVIGATE_PAGES_FIVE);
        if (request.getSession().getAttribute("userId") != null) {
            ArticleUtil.setIsLovedAndkeepCurrentUser(pageInfo, (Integer)request.getSession().getAttribute("userId"));
        }
        return pageInfo;
    }

    public PageInfo<Article> findNotPassAllByUserId(Integer pn, Integer userId) {
        PageHelper.startPage(pn, PAGE_SIZE_TEN);
        PageInfo<Article> pageInfo = new PageInfo<>(articleDao.findNotPassAllByUserId(userId), NAVIGATE_PAGES_FIVE);
        return pageInfo;
    }
}
