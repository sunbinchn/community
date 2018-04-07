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
                            <div style="margin-top: 10px;">
                                <span class="color-999"><fmt:formatDate value="${article.createTime}" pattern="yyyy年MM月dd日"/></span>
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
                                <div class="comment-item" data-id="${comment.id}">
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
                                            <button type="button" class="btn btn-default btn-xs comment-love-btn">
                                                <span class="glyphicon glyphicon-triangle-top" style="color: #777573;"></span>
                                                <span style="color: #1b1a19;">${comment.loveCount}</span>
                                            </button>
                                            <span class="comment-nest-comment">
                                                <span class="glyphicon glyphicon-comment" style="color: #737270;"></span>
                                                <span id="nest-comment-span" style="color:#737171;">${comment.nestCommentSize}条评论</span>
                                            </span>

                                            <span class="pull-right text-color-999"><fmt:formatDate value="${comment.createTime}" pattern="yyyy-MM-dd HH:mm:ss"/></span>
                                        </div>
                                    </div>
                                    <div class="nest-comment-box">
                                        <div class="inner-box">
                                            <div class="inner-comment-list">
                                                <div class="inner-comment-item">
                                                    <div class="comment-head">
                                                        <a class="comment-img" href="">
                                                            <img src="${PATH}static/images/user/user_icon_boy.png" alt="">
                                                        </a>
                                                        <p><a href=""></a></p>
                                                    </div>
                                                    <div class="comment-body">
                                                        <div class="markitup-box"></div>
                                                    </div>
                                                </div>
                                                <div class="inner-comment-item">
                                                    <div class="comment-head">
                                                        <a class="comment-img" href="">
                                                            <img src="${PATH}static/images/user/user_icon_boy.png" alt="">
                                                        </a>
                                                        <p><a href=""></a></p>
                                                    </div>
                                                    <div class="comment-body">
                                                        <div class="markitup-box"></div>
                                                    </div>
                                                </div>
                                            </div>
                                            <textarea cols="2" rows="1" placeholder="你想对ta说的话" class="comment-inner-content" data-target-id=""></textarea>
                                            <button type="button" class="btn btn-success inner-submit-button" disabled="disabled">评论</button>
                                        </div>

                                    </div>
                                </div>
                            </c:forEach>
                            <c:choose>
                                <c:when test="${empty  username}">
                                    <p align="center" id="no-comment-auth-warning">要评论请先<a id="detail_login_a">登录</a>或<a id="detail_register_a">注册</a></p>
                                </c:when>
                                <c:otherwise>
                                    <div class="comment-form">
                                        <div class="comment-form-icon">
                                            <img src="${PATH}static/images/${userIcon}">
                                        </div>
                                        <textarea placeholder="发表你的看法" class="comment-input"></textarea>
                                        <div class="submit-div">
                                            <button id="comment-sumbit-button" type="button" class="btn btn-success submit-button" disabled="disabled">评论</button>
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
