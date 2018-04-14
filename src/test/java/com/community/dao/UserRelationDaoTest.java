package com.community.dao;

import com.community.entity.User;
import com.community.entity.UserRelation;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:spring/spring-dao.xml")
public class UserRelationDaoTest {
    @Autowired
    private UserRelationDao userRelationDao;

    @Test
    public void insert() {
        UserRelation userRelation = new UserRelation();
        User user = new User();
        user.setUserId(61);
        userRelation.setUser(user);
        User targetUser = new User();
        targetUser.setUserId(60);
        userRelation.setTargetUser(targetUser);
        boolean insert = userRelationDao.insert(userRelation);
    }
    @Test
    public void findAll() {
        List<User> allByTargetUserId = userRelationDao.findAllByTargetUserId(55);
    }
}
