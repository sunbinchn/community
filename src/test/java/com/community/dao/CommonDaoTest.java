package com.community.dao;

import com.community.entity.ArticleType;
import com.community.entity.Comment;
import com.community.entity.Image;
import com.community.entity.Tag;
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
public class CommonDaoTest {
    private static Logger logger = LoggerFactory.getLogger(CommonDaoTest.class);
    @Autowired
    private ArticleTypeDao articleTypeDao;
    @Autowired
    private TagDao tagDao;
    @Autowired
    private ImageDao imageDao;
    @Autowired
    private CommentDao commentDao;

    @Test
    public void findCommentByArticleId() {
        List<Comment> allByArticleId = commentDao.findAllByArticleId(2);
        logger.error(allByArticleId.size()+""
        );
    }

    @Test
    public void findAll() {
        List<ArticleType> all = articleTypeDao.findAll();
        for (ArticleType type : all) {
            System.out.println(all);
        }
        List<Tag> tagList = tagDao.findAll();
        for (Tag tag : tagList) {
            logger.info(tag.getName());
        }
    }

    @Test
    public void findById() {
        Image image = imageDao.findById(1);
        logger.info(image.getUrl());
    }

}
