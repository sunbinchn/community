package com.community.service;

import com.community.dao.ArticleDao;
import com.community.dao.UserArticleKeepDao;
import com.community.dao.UserArticleLoveDao;
import com.community.dao.UserArticleReadDao;
import com.community.entity.User;
import com.community.vo.ArticleCalculate;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.util.CollectionUtils;

import java.util.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:spring/spring-dao.xml")
public class ArticleServiceTest {
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
    @Test
    public void getInterestingUserList() {
        Map<Integer, Integer> map = new HashMap<>();
        List<ArticleCalculate> readList = userArticleReadDao.countGroupByArticleId();
        List<ArticleCalculate> loveList = userArticleLoveDao.countGroupByArticleId();
        List<ArticleCalculate> keepList = userArticleKeepDao.countGroupByArticleId();
        calculate(readList, map, READ_SCORE);
        calculate(loveList, map, LOVE_SCORE);
        calculate(keepList, map, KEEP_SCORE);
        List<Map.Entry<Integer,Integer>> list = new ArrayList(map.entrySet());
        Collections.sort(list,new Comparator<Map.Entry<Integer,Integer>>() {
            public int compare(Map.Entry<Integer, Integer> o1,
                               Map.Entry<Integer, Integer> o2) {
                return o2.getValue().compareTo(o1.getValue());
            }
        });
        List<Integer> articleIds = new ArrayList<>();
        for(Map.Entry<Integer,Integer> mapping:list){
            articleIds.add(mapping.getKey());
            System.out.println(mapping.getKey()+":"+mapping.getValue());
        }
        List<User> allUserByArticleIds = articleDao.findAllUserByArticleIds(articleIds);

//        if (userId == null) { //未登录
//        } else {
//        }
//        return null;
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
