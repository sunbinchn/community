package com.community.initData;

import com.community.dao.ArticleDao;
import com.community.dao.ArticleDaoTest;
import com.community.entity.Article;
import com.community.entity.ArticleType;
import com.community.entity.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:spring/spring-dao.xml")
public class InsertArticleTest {
    private static Logger logger = LoggerFactory.getLogger(ArticleDaoTest.class);
    @Autowired
    private ArticleDao articleDao;

    @Test
    public void save() {
        Article article = new Article();
        for (int i = 2; i < 100; i++) {
            ArticleType articleType = new ArticleType();
            //[0,1)
            articleType.setId((int)(Math.random() * 8) +1);
            article.setArticleType(articleType);
            article.setTitle("我的第" + i + "篇文章");
            article.setContent("第" + i + "篇文章的内容");
            article.setOriginal(1);
            User user = new User();
            user.setUserId(1);
            article.setUser(user);
            articleDao.save(article);
        }
    }
}