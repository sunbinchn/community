package com.community.dao;

import com.community.entity.Article;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

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
}
