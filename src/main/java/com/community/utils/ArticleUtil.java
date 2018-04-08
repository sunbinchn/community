package com.community.utils;

import com.community.entity.Article;
import com.community.entity.User;
import com.github.pagehelper.PageInfo;
import org.springframework.util.CollectionUtils;

/**
 * 计算当前用户是否喜爱或收藏该文章
 */
public class ArticleUtil {


    @SuppressWarnings("all")
    public static void setIsLovedAndkeepCurrentUser(PageInfo<Article> pageInfo, Integer userId) {
        if (!CollectionUtils.isEmpty(pageInfo.getList()) && userId != null) {
            for (Article article : pageInfo.getList()) {
                if (!CollectionUtils.isEmpty(article.getLoveUserList())) { //判断文章是否被当前用户喜爱
                    for (User user : article.getLoveUserList()) {
                        if (user.getUserId().equals(userId)) {
                            article.setIsLovedCurrentUser(true);
                            break;
                        }
                    }
                }
                if (!CollectionUtils.isEmpty(article.getKeepUserList())) { //判断文章是否被当前用户收藏
                    for (User user : article.getKeepUserList()) {
                        if (user.getUserId().equals(userId)) {
                            article.setIsKeepCurrentUser(true);
                            break;
                        }
                    }
                }
            }
        }
    }
    public static void setIsLovedCurrentUser(PageInfo<Article> pageInfo, Integer userId) {
        if (!CollectionUtils.isEmpty(pageInfo.getList()) && userId != null) {
            for (Article article : pageInfo.getList()) {
                article.setIsKeepCurrentUser(true);//仅需要计算喜爱的必然是已经收藏的
                if (!CollectionUtils.isEmpty(article.getLoveUserList())) { //判断文章是否被当前用户喜爱
                    for (User user : article.getLoveUserList()) {
                        if (user.getUserId().equals(userId)) {
                            article.setIsLovedCurrentUser(true);
                            break;
                        }
                    }
                }
            }
        }
    }
    public static void setIsKeepCurrentUser(PageInfo<Article> pageInfo, Integer userId) {
        if (!CollectionUtils.isEmpty(pageInfo.getList()) && userId != null) {
            for (Article article : pageInfo.getList()) {
                article.setIsLovedCurrentUser(true); //仅需要计算收藏的必然是已经喜爱的
                if (!CollectionUtils.isEmpty(article.getKeepUserList())) { //判断文章是否被当前用户收藏
                    for (User user : article.getKeepUserList()) {
                        if (user.getUserId().equals(userId)) {
                            article.setIsKeepCurrentUser(true);
                            break;
                        }
                    }
                }
            }
        }
    }
}
