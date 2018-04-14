package com.community.dao;

import com.community.entity.Article;
import com.community.entity.ArticleType;
import com.community.entity.User;
import com.community.enums.ArticleTypeConstant;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import sun.nio.cs.US_ASCII;

import java.util.List;

/**
 * 配置spring和junit整合,为了junit启动时加载springIOC容器(spring-test.jar.junit.jar)
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:spring/spring-dao.xml")
public class ArticleDaoTest {
	private static Logger logger = LoggerFactory.getLogger(ArticleDaoTest.class);
	@Autowired
	private ArticleDao articleDao;

	@Test
	public void insert() {
		Article article = new Article();
		User user =new User();
		user.setUserId(1);
		article.setUser(user);
		article.setTitle("title");
		article.setContent("content");
		articleDao.insert(article);
	}
	@Test
	public void queryTest() {
		articleDao.findAllByArticleTypeIdAndShowTypeAndQuery(2, 1, "admin");
//		articleDao.findAllByShowTypeAndQuery(1, "Mysql");
	}
	@Test
	public void findById() {
		Article article = articleDao.findById(1);
		logger.info(article.getTitle());
		logger.error("user", article.getUser());
		logger.error("fromArticle", article.getFromArticle());
	}
	@Test
	public void findAllByArticleTypeId() {
		List<Article> allByShowType = articleDao.findAllByShowType(1);
	}
	@Test
	public void update() {
		// [0,1)

		List<Article> all = articleDao.findAll();
//		for (Article article : all) {
//			ArticleType articleType = new ArticleType();
//			articleType.setId((int)(Math.random() * 8 +1));
//			article.setArticleType(articleType);
//			articleDao.update(article);
//		}
	}
}
