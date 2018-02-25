package com.community.dao;

import com.community.entity.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * 配置spring和junit整合,为了junit启动时加载springIOC容器(spring-test.jar.junit.jar)
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:spring/spring-dao.xml")
public class UserDaoTest {
	private static Logger logger = LoggerFactory.getLogger(UserDaoTest.class);
	@Autowired
	private UserDao userDao;

	@Test
	public void testSave(){
		User user = new User();
		user.setUserId(1);
		user.setUserName("test");
		user.setPassword("123456");
		int insert = userDao.insert(user);
	}

	@Test
	public void findUserByName(){
		User user = userDao.findUserByName("test");
		logger.info("user", user);
	}
}
