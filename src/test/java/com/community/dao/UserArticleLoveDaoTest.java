package com.community.dao;

import com.community.entity.Article;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:spring/spring-dao.xml")
public class UserArticleLoveDaoTest {
    @Autowired
    private UserArticleLoveDao userArticleLoveDao;
    @Test
    public void findLoveArticleListByUserId() {
        List<Article> loveArticleListByUserId = userArticleLoveDao.findLoveArticleListByUserId(62);
    }
}
