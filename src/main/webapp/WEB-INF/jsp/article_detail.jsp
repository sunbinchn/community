<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ include file="taglib.jsp" %>
<html>
<head>
    <title>开源技术社区</title>
    <%@ include file="common.jsp" %>
    <link href="${PATH}static/css/article_detail.css" rel="stylesheet">
    <script src="${PATH}static/js/article_detail.js"></script>

</head>
<body>
<%@ include file="header.jsp" %>
<div class="container">
    <div class="row">
        <div class="aw-content-wrap clearfix">
            <div class="article">
                <div class="col-sm-12 col-md-9 aw-main-content">
                    <div class="container">
                        <div class="content-user-icon-div">
                            <a href="#" class="content-user-icon">
                                <img src="${PATH}static/images/${article.user.icon.url}" alt="">
                            </a>
                        </div>
                        <div class="user-and-time-div">
                            <a href="#" class="content-user-name">
                                ${article.user.userName}
                            </a>
                            <div>
                                <span class="color-999">2018 年 03 月 17 日</span>
                            </div>
                        </div>
                    </div>
                    <div class="article-content ">
                        <h3>${article.title}</h3>
                        <div class="content-body">
                            ${article.content}
                        </div>
                    </div>
                    <div class="article-comment">
                        <div class="article-comment-head">
                            <h3>${fn:length(article.commentList)}&nbsp;个&nbsp;评&nbsp;论</h3>
                        </div>
                        <div class="article-comment-list">

                            <c:forEach items="${article.commentList}" var="comment">
                                <div class="comment-item">
                                    <div class="comment-head">
                                        <a class="comment-img" href="">
                                            <img src="${PATH}static/images/${comment.user.icon.url}" alt="${comment.user.userName}">
                                        </a>
                                        <p><a href="">${comment.user.userName}</a></p>
                                    </div>
                                    <div class="comment-body">
                                        <div class="markitup-box">${comment.content}</div>
                                    </div>
                                    <div class="comment-footer">
                                        <div class="meta">
                                            <span class="pull-right text-color-999"><fmt:formatDate value="${comment.createTime}" pattern="yyyy-MM-dd HH:mm:ss"/></span>
                                        </div>
                                    </div>
                                </div>
                            </c:forEach>
                            <c:choose>
                                <c:when test="${empty  username}">
                                    <p align="center" id="no-comment-auth-warning">要评论请先<a>登录</a>或<a>注册</a></p>
                                </c:when>
                                <c:otherwise>
                                    <div class="comment-form">
                                        <div class="comment-form-icon">
                                            <img src="${PATH}static/images/${userIcon}">
                                        </div>
                                        <textarea placeholder="发表你的看法" class="comment-input"></textarea>
                                        <div class="submit-div">
                                            <button id="comment-sumbit-button" type="button" class="btn btn-success submit-button">评论</button>
                                        </div>
                                    </div>
                                </c:otherwise>
                            </c:choose>
                        </div>
                    </div>


                </div>
                <!-- 右侧栏 -->
                <div class="col-sm-12 col-md-3 aw-side-bar">

                </div>
            </div>
        </div>
    </div>
</div>
</body>
</html>
