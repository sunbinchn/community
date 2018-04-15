package com.community.utils;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:spring/spring-dao.xml")
public class EmailUtilTest {

    @Test
    public void send() {
        EmailUtil.sendMail("411938182@qq.com", "邮件测试", "邮件内容");
    }
}
