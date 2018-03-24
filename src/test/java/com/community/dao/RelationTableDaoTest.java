package com.community.dao;

import com.community.entity.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:spring/spring-dao.xml")
public class RelationTableDaoTest {

    private static Logger logger = LoggerFactory.getLogger(RelationTableDaoTest.class);
    @Autowired
    private UserArticleKeepDao userArticleKeepDao;
    @Autowired
    private UserCommentLoveDao userCommentLoveDao;

    @Test
    public void findUserListByArticleId() {
        List<User> userListByArticleId = userArticleKeepDao.findUserListByArticleId(1);
    }

    @Test
    public void findUserListByCommentId() {}

    @Test
    public void getUserCommentLoveDao() {
        List<User> userListByCommentId = userCommentLoveDao.findUserListByCommentId(1);
    }
    @Test
    public void testUserCommentLoveDao() {
        Integer commentId = 22;
        Integer userId= 1;
        boolean insert = userCommentLoveDao.insert(commentId, userId);
        logger.info(insert+"");
        Integer countByCommentIdAndUserId = userCommentLoveDao.getCountByCommentIdAndUserId(commentId, userId);
        logger.info(countByCommentIdAndUserId+"");
        boolean delete = userCommentLoveDao.delete(commentId, userId);
        logger.info(delete+"");
        Integer countByCommentIdAndUserId2 = userCommentLoveDao.getCountByCommentIdAndUserId(commentId, userId);
        logger.info(countByCommentIdAndUserId2+"");
    }
}